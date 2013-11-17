package VisibilityChecker;

import Latte.Absyn.*;
import Utils.ExpectedType;
import Utils.SemanticAnalysis;
import Utils.State;
import Utils.VariableDefinition;
import VisibilityChecker.Errors.DuplicatedIdentifier;
import VisibilityChecker.Errors.FunctionReturnTypeError;
import VisibilityChecker.Errors.MissingReturn;

import java.util.ArrayList;
import java.util.List;

import static Utils.SemanticAnalysis.createSemanticAnalysis;

public class TopDefVC implements TopDef.Visitor<SemanticAnalysis, State> {

    @Override
    public SemanticAnalysis visit(FnDef p, State state) {

        for (Arg arg : p.listarg_) {
            if (state.hasId(arg.ident_)) {
                return createSemanticAnalysis(new DuplicatedIdentifier(arg.ident_));
            }
            // We are assuming that arguments are defined in the block of the function
            // This results in ability to cover argument in only in nested block, not
            // "the first" one.
            state.addVarDefinition(arg.ident_, arg.type_);
            /*
            VariableDefinition argumentDef = new VariableDefinition(p.block_, arg.type_);
            List<VariableDefinition> argumentDefList = new ArrayList<VariableDefinition>();
            argumentDefList.add(argumentDef);
            state.getDeclaredIds().put(arg.ident_, argumentDefList);
            */
        }

        State stateCopy;
        try {
            stateCopy = (State)state.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedClassVersionError(e.toString());
        }

        SemanticAnalysis firstanalysis = p.block_.accept(new BlockVC(), state);
        if (firstanalysis.hasErrors()) {
            return firstanalysis;
        }

        // Check for function returns
        // TODO: add expectedType to the state, so we can check in the
        // if statement
        ExpectedType expectedType = new ExpectedType(stateCopy, p.type_);
        SemanticAnalysis<Type> returnAnalysis = new SemanticAnalysis<Type>();
        for (Stmt stmt : p.block_.liststmt_) {
            SemanticAnalysis<Type> analysis = stmt.accept(new FunctionReturnChecker(), expectedType);
            if (analysis.hasErrors()) {
                return analysis;
            }
            returnAnalysis = returnAnalysis.merge(analysis);
        }
        // No returns, fuck that
        if (returnAnalysis.getT() == null) {
            if (p.type_.equals(ExprCorrectnessChecker.VOID)) {
                return returnAnalysis;
            } else {
                return createSemanticAnalysis(new MissingReturn(p.ident_));
            }
        } else {
            // we don't have to check return type here, because it is already checkec
            // in FunctionReturnChecker SRet statements
            return returnAnalysis;
        }
    }

}
