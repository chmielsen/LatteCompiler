package Checkers;

import Checkers.Errors.*;
import Latte.Absyn.*;
import Utils.SemanticAnalysis;
import Utils.State;
import Utils.TypeConstants;

import java.util.ListIterator;

/**
 * Class implementing semantic analysis of expression. On successful analysis
 * the type of the expression will be returned, otherwise the {@link SemanticAnalysis}
 * object contains list of {@link SemanticError}
 */
public class ExprCorrectnessChecker implements Expr.Visitor<SemanticAnalysis<Type>, State> {

    @Override
    public SemanticAnalysis<Type> visit(EVar expr, State state) {
        if (!state.hasId(expr.ident_)) {
            return SemanticAnalysis.createSemanticAnalysis(new IdentifierNotVisible(expr.ident_));
        }
        return SemanticAnalysis.createSemanticAnalysis(state.getIdentifierType(expr.ident_));
    }

    @Override
    public SemanticAnalysis<Type> visit(ELitInt expr, State state) {
        return SemanticAnalysis.createSemanticAnalysis(TypeConstants.INT);
    }

    @Override
    public SemanticAnalysis<Type> visit(ELitTrue expr, State state) {
        return SemanticAnalysis.createSemanticAnalysis(TypeConstants.BOOL);
    }

    @Override
    public SemanticAnalysis<Type> visit(ELitFalse expr, State state) {
        return SemanticAnalysis.createSemanticAnalysis(TypeConstants.BOOL);
    }

    @Override
    public SemanticAnalysis<Type> visit(EApp expr, State state) {
        if (!state.hasId(expr.ident_)) {
            return SemanticAnalysis.createSemanticAnalysis(new IdentifierNotVisible(expr.ident_));
        } else if (!state.isFunction(expr.ident_)) {
            return SemanticAnalysis.createSemanticAnalysis(new VariableApplicationError(expr.ident_));
        } else {
            // get signature we have in state
            TFun functionType = (TFun)state.getIdentifierType(expr.ident_);
            // compare number of arguments
            if (functionType.listtype_.size() != expr.listexpr_.size()) {
                // stop comapring here, maybe user choosed bad function
                return SemanticAnalysis.createSemanticAnalysis(new WrongNumberOfArguments(expr.ident_));
            }

            // compare argument types
            ListType argumentTypes = new ListType();
            SemanticAnalysis<Type> semanticAnalysis = new SemanticAnalysis<Type>();
            for (Expr argument : expr.listexpr_ ) {
                SemanticAnalysis<Type> argumentAnalysis =
                        argument.accept(new ExprCorrectnessChecker(), state);
                if (argumentAnalysis.hasErrors()) {
                    semanticAnalysis = semanticAnalysis.merge(argumentAnalysis);
                }
                argumentTypes.add(argumentAnalysis.getT());
            }
            if (semanticAnalysis.hasErrors()) {
                // no need to add more errors...
                return semanticAnalysis;
            }

            ListIterator<Type> signatureIt = functionType.listtype_.listIterator(),
                                argumentIt = argumentTypes.listIterator();
            // compare types of arguments and signature
            while (signatureIt.hasNext()) {
                Type signatureType = signatureIt.next(),
                      argumentType = argumentIt.next();
                if (!signatureType.equals(argumentType)) {
                    semanticAnalysis.getErrors().add(new TypeError(signatureType, argumentType));
                }
            }
            // set function return type, because it passes the analysis
            semanticAnalysis.setT(functionType.type_);
            return semanticAnalysis;
        }
    }

    @Override
    public SemanticAnalysis<Type> visit(EString expr, State state) {
        return SemanticAnalysis.createSemanticAnalysis(TypeConstants.STRING);
    }

    @Override
    public SemanticAnalysis<Type> visit(Neg expr, State state) {
        SemanticAnalysis<Type> analysis = expr.expr_.accept(new ExprCorrectnessChecker(), state);
        if (analysis.hasErrors()) {
            return analysis;
        } else {
            if (!analysis.getT().equals(TypeConstants.INT)) {
                // Only ints can have '-' beore them
                return SemanticAnalysis.createSemanticAnalysis(new TypeError(TypeConstants.INT, analysis.getT()));
            } else {
                return analysis;
            }
        }
    }

    @Override
    public SemanticAnalysis<Type> visit(Not expr, State state) {
        SemanticAnalysis<Type> analysis = expr.expr_.accept(new ExprCorrectnessChecker(), state);
        if (analysis.hasErrors()) {
            return analysis;
        } else {
            if (!analysis.getT().equals(TypeConstants.BOOL)) {
                // Only bools can have '!' beore them
                return SemanticAnalysis.createSemanticAnalysis(new TypeError(TypeConstants.BOOL, analysis.getT()));
            } else {
                return analysis;
            }
        }
    }

    @Override
    public SemanticAnalysis<Type> visit(EMul expr, State state) {
        return checkExprsTypes(state, expr.expr_1, expr.expr_2, TypeConstants.INT);
    }

    @Override
    public SemanticAnalysis<Type> visit(EAdd expr, State state) {
        SemanticAnalysis<Type> analysis = checkExprsSameType(state, expr.expr_1, expr.expr_2);
        if (analysis.hasErrors()) {
            return analysis;
        } else if (analysis.getT().equals(TypeConstants.INT) || analysis.getT().equals(TypeConstants.STRING)){
            return analysis;
        } else {
            // TODO popraw msg
            return SemanticAnalysis.createSemanticAnalysis(new TypeError(TypeConstants.INT, analysis.getT()));
        }
    }

    @Override
    public SemanticAnalysis<Type> visit(ERel expr, State state) {
        SemanticAnalysis<Type> analysis;
        if (expr.relop_ instanceof EQU || expr.relop_ instanceof NE) {
            // can compare any type
            analysis = checkExprsSameType(state, expr.expr_1, expr.expr_2);
        } else {
            // can check which one greater with INT
            analysis = checkExprsTypes(state, expr.expr_1, expr.expr_2, TypeConstants.INT);
        }
        if (analysis.hasErrors()) {
            return analysis;
        }
        return SemanticAnalysis.createSemanticAnalysis(TypeConstants.BOOL);
    }

    @Override
    public SemanticAnalysis<Type> visit(EAnd expr, State state) {
        return checkExprsTypes(state, expr.expr_1, expr.expr_2, TypeConstants.BOOL);
    }

    @Override
    public SemanticAnalysis<Type> visit(EOr expr, State state) {
        return checkExprsTypes(state, expr.expr_1, expr.expr_2, TypeConstants.BOOL);
    }

    private SemanticAnalysis<Type> checkExprsSameType(State state, Expr expr1, Expr expr2) {
        SemanticAnalysis<Type> analysisExpr1 = expr1.accept(this, state);
        SemanticAnalysis<Type> analysisExpr2 = expr2.accept(this, state);

        if (analysisExpr1.hasErrors()) {
            return analysisExpr1;
        } else if (analysisExpr2.hasErrors()) {
            return analysisExpr2;
        } else if (!analysisExpr1.getT().equals(analysisExpr2.getT())) {
            return SemanticAnalysis.createSemanticAnalysis(new TypeError(analysisExpr1.getT(), analysisExpr2.getT()));
        } else {
            // no errors, just passing type
            return analysisExpr1;
        }
    }

    private SemanticAnalysis<Type> checkExprsTypes(State state, Expr expr1, Expr expr2, Type expected) {
        SemanticAnalysis<Type> analysis = checkExprsSameType(state, expr1, expr2);
        if (analysis.hasErrors()) {
            return analysis;
        } else if (!expected.equals(analysis.getT())) {
            return SemanticAnalysis.createSemanticAnalysis(new TypeError(expected, analysis.getT()));
        } else {
            return analysis;
        }
    }
}
