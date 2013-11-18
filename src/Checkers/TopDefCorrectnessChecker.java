package Checkers;

import Checkers.Errors.DuplicatedIdentifier;
import Checkers.Errors.MissingReturn;
import Latte.Absyn.*;
import Utils.ExpectedType;
import Utils.SemanticAnalysis;
import Utils.State;
import Utils.TypeConstants;

import static Utils.SemanticAnalysis.createSemanticAnalysis;


/**
 * Implements Visitor pattern for {@link TopDef} (functions in other words) in
 * order to check it's semantical correctness of functions.
 */
public class TopDefCorrectnessChecker implements TopDef.Visitor<SemanticAnalysis, State> {

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
        }

        State stateCopy;
        try {
            stateCopy = (State)state.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedClassVersionError(e.toString());
        }

        // Semantical analysis without checking returned type
        SemanticAnalysis firstanalysis = p.block_.accept(new BlockCorrectnessChecker(), state);
        if (firstanalysis.hasErrors()) {
            return firstanalysis;
        }

        // Check function returned type
        ExpectedType expectedType = new ExpectedType(stateCopy, p.type_);
        SemanticAnalysis<Type> returnAnalysis = p.block_.accept(new BlockReturnChecker(), expectedType);
        if (returnAnalysis.hasErrors()) {
            return returnAnalysis;
        } else if (returnAnalysis.getT() == null) {
            // No returns
            if (p.type_.equals(TypeConstants.VOID)) {
                return returnAnalysis;
            } else {
                return createSemanticAnalysis(new MissingReturn(p.ident_));
            }
        } else {
            // we don't have to check return type here, because it is already checked
            // in FunctionReturnChecker SRet statements
            return returnAnalysis;
        }
    }

}
