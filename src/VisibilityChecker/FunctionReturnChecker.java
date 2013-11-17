package VisibilityChecker;

import Latte.Absyn.*;
import Utils.SemanticAnalysis;
import Utils.ExpectedType;
import Utils.State;
import VisibilityChecker.Errors.FunctionReturnTypeError;
import VisibilityChecker.Errors.IfElseTypeMismatch;

import static VisibilityChecker.StatementVC.declareVariable;

/**
 * Checks whether function has a return statement and returns the returned Type on
 * no errrors
 * This visitor is called after first analysis, but we still need to add variables
 * to current state.
 */
public class FunctionReturnChecker implements Stmt.Visitor<SemanticAnalysis<Type>, ExpectedType> {


    @Override
    public SemanticAnalysis<Type> visit(SEmpty p, ExpectedType expectedType) {
        return new SemanticAnalysis<Type>();
    }

    @Override
    public SemanticAnalysis<Type> visit(SBStmt p, ExpectedType expectedType) {
        try {
            State visibleIdsCopy = (State)expectedType.getState().clone();
            ExpectedType expectedTypeCopy = new ExpectedType(visibleIdsCopy, expectedType.getType());
            return p.block_.accept(new BlockReturnChecker(), expectedTypeCopy);
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    // Have to do this
    @Override
    public SemanticAnalysis<Type> visit(SDecl p, ExpectedType expectedType) {
        return declareVariable(p, expectedType.getState());
    }

    @Override
    public SemanticAnalysis<Type> visit(SAss p, ExpectedType expectedType) {
        return new SemanticAnalysis<Type>();
    }

    @Override
    public SemanticAnalysis<Type> visit(SIncr p, ExpectedType expectedType) {
        return new SemanticAnalysis<Type>();
    }

    @Override
    public SemanticAnalysis<Type> visit(SDecr p, ExpectedType expectedType) {
        return new SemanticAnalysis<Type>();
    }

    // Most important in this bitch
    @Override
    public SemanticAnalysis<Type> visit(SRet p, ExpectedType expectedType) {
        SemanticAnalysis<Type> analysis = p.expr_.accept(new ExprCorrectnessChecker(), expectedType.getState());
        if (analysis.hasErrors()) {
            return analysis;
        } else if (!expectedType.getType().equals(analysis.getT())) {
            return SemanticAnalysis.createSemanticAnalysis(new FunctionReturnTypeError(expectedType.getType(), analysis.getT()));
        }
        return analysis;
    }

    @Override
    public SemanticAnalysis<Type> visit(SVRet p, ExpectedType expectedType) {
        if (!ExprCorrectnessChecker.VOID.equals(expectedType.getType())) {
            return SemanticAnalysis.createSemanticAnalysis(new FunctionReturnTypeError(expectedType.getType(), ExprCorrectnessChecker.VOID));
        }
        return SemanticAnalysis.createSemanticAnalysis(ExprCorrectnessChecker.VOID);
    }

    // Don't need to parse, because it's conditional, we can't
    // be sure if the programs go into the body of if
    @Override
    public SemanticAnalysis<Type> visit(SCond p, ExpectedType expectedType) {
        SemanticAnalysis<Type> analysis = p.stmt_.accept(this, expectedType);
        if (analysis.hasErrors()) {
            // SRet is throwing errors
            return analysis;
        }
        // DOdaj wyprowadzenie satlych
        if (p.expr_ instanceof ELitTrue) {
            return SemanticAnalysis.createSemanticAnalysis(ExprCorrectnessChecker.BOOL);
        }
        return new SemanticAnalysis<Type>();
    }

    // only if two statements have return then it's ok
    @Override
    public SemanticAnalysis<Type> visit(SCondElse p, ExpectedType expectedType) {
        // we are sure that expression is correct, because it
        // was analysed before
        SemanticAnalysis<Type> analysisIfTrue = p.stmt_1.accept(this, expectedType);
        SemanticAnalysis<Type> analysisIfFalse = p.stmt_2.accept(this, expectedType);
        if (p.expr_ instanceof )
        if (analysisIfFalse.getT() == null || analysisIfTrue.getT() == null) {
            return new SemanticAnalysis<Type>();
        } else if (analysisIfFalse.getT() == analysisIfFalse.getT()) {
            return SemanticAnalysis.createSemanticAnalysis(analysisIfFalse.getT());
        } else {
            return SemanticAnalysis.createSemanticAnalysis(
                    new IfElseTypeMismatch(analysisIfFalse.getT(),
                            analysisIfTrue.getT()));
        }

    }

    // Don't need to parse, because it's conditional, we can't
    // be sure if the programs go into the body of while
    @Override
    public SemanticAnalysis<Type> visit(SWhile p, ExpectedType expectedType) {
        SemanticAnalysis<Type> analysis = p.stmt_.accept(this, expectedType);
        if (analysis.hasErrors()) {
            // SRet is throwing errors
            return analysis;
        }
        return new SemanticAnalysis<Type>();
    }

    @Override
    public SemanticAnalysis<Type> visit(SExp p, ExpectedType expectedType) {
        return new SemanticAnalysis<Type>();
    }
}
