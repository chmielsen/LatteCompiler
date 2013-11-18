package Checkers;

import Checkers.Errors.FunctionReturnTypeError;
import Checkers.Errors.IfElseTypeMismatch;
import Latte.Absyn.*;
import Utils.ExpectedType;
import Utils.SemanticAnalysis;
import Utils.State;
import Utils.TypeConstants;

import static Checkers.StatementCorrectnessChecker.declareVariable;

/**
 * Implements Visitor pattenr for {@link Stmt} in order to check whether
 * statement has a return statement.
 *
 * Each method from {@link Stmt.Visitor} returns the returned {@link Type} on
 * success, list of {@link Checkers.Errors.SemanticError} otherwise
 *
 * These methods shouble be called after first analysis, because they assume
 * semantical correctness (without correct type on returns) of statements.
 */
public class StatementReturnChecker implements Stmt.Visitor<SemanticAnalysis<Type>, ExpectedType> {

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

    // The 'meat' of this checker
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

    // The 'meat' of this checker
    @Override
    public SemanticAnalysis<Type> visit(SVRet p, ExpectedType expectedType) {
        if (!TypeConstants.VOID.equals(expectedType.getType())) {
            return SemanticAnalysis.createSemanticAnalysis(new FunctionReturnTypeError(expectedType.getType(), TypeConstants.VOID));
        }
        return SemanticAnalysis.createSemanticAnalysis(TypeConstants.VOID);
    }

    @Override
    public SemanticAnalysis<Type> visit(SCond p, ExpectedType expectedType) {
        SemanticAnalysis<Type> analysis = p.stmt_.accept(this, expectedType);
        if (analysis.hasErrors()) {
            // SRet is throwing errors
            return analysis;
        }
        // Simple checking if expression is always True.
        // If so the function will always return the value from if's
        // statement.
        if (p.expr_ instanceof ELitTrue) {
            return analysis;
        }
        return new SemanticAnalysis<Type>();
    }

    @Override
    public SemanticAnalysis<Type> visit(SCondElse p, ExpectedType expectedType) {
        // We are sure that expression is correct, because it
        // was analysed before
        SemanticAnalysis<Type> analysisIfTrue = p.stmt_1.accept(this, expectedType);
        SemanticAnalysis<Type> analysisIfFalse = p.stmt_2.accept(this, expectedType);

        if (analysisIfFalse.hasErrors() || analysisIfTrue.hasErrors()) {
            return analysisIfFalse.merge(analysisIfTrue);
        } else if (p.expr_ instanceof ELitTrue) {
            return analysisIfTrue;
        } else if (p.expr_ instanceof ELitFalse) {
            return analysisIfFalse;
        } else if (analysisIfFalse.getT() == null || analysisIfTrue.getT() == null) {
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
        // Simple checking if expression is always True.
        // If so the function will always return the value from while's
        // statement.
        if (p.expr_ instanceof ELitTrue) {
            return analysis;
        }
        return new SemanticAnalysis<Type>();
    }

    @Override
    public SemanticAnalysis<Type> visit(SExp p, ExpectedType expectedType) {
        return new SemanticAnalysis<Type>();
    }
}
