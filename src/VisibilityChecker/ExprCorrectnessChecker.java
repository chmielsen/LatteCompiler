package VisibilityChecker;

import Latte.Absyn.*;
import VisibilityChecker.Errors.*;

import java.util.ListIterator;

/**
 * Class implementing semantic analysis of expression. On successful analysis
 * the type of the expression will be returned.
 */
public class ExprCorrectnessChecker implements Expr.Visitor<SemanticAnalysis<Type>, State> {
    @Override
    public SemanticAnalysis<Type> visit(EVar expr, State state) {
        if (!state.hasId(expr.ident_)) {
            createSemanticAnalysis(new IdentifierNotVisible(expr.ident_));
        }
        return createSemanticAnalysis(state.getIdentifierType(expr.ident_));
    }

    @Override
    public SemanticAnalysis<Type> visit(ELitInt expr, State state) {
        return null;
    }

    @Override
    public SemanticAnalysis<Type> visit(ELitTrue expr, State state) {
        return null;
    }

    @Override
    public SemanticAnalysis<Type> visit(ELitFalse expr, State state) {
        return null;
    }

    @Override
    public SemanticAnalysis<Type> visit(EApp expr, State state) {
        if (!state.hasId(expr.ident_)) {
            return createSemanticAnalysis(new IdentifierNotVisible(expr.ident_));
        } else if (!state.isFunction(expr.ident_)) {
            return createSemanticAnalysis(new VariableApplicationError(expr.ident_));
        } else {
            // get signature we have in state
            TFun functionType = (TFun)state.getIdentifierType(expr.ident_);
            // compare number of arguments
            if (functionType.listtype_.size() != expr.listexpr_.size()) {
                // stop comapring here, maybe user choosed bad function
                return createSemanticAnalysis(new WrongNumberOfArguments(expr.ident_));
            }

            // compare argument types
            ListType argumentTypes = new ListType();
            SemanticAnalysis<Type> semanticAnalysis = new SemanticAnalysis<Type>();
            for (Expr argument : expr.listexpr_ ) {
                SemanticAnalysis<Type> argumentAnalysis =
                        argument.accept(new ExprCorrectnessChecker(), state);
                if (argumentAnalysis.hasErrors()) {
                    semanticAnalysis.merge(argumentAnalysis);
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
            return semanticAnalysis;
        }
    }

    @Override
    public SemanticAnalysis<Type> visit(EString expr, State state) {
        return null;
    }

    @Override
    public SemanticAnalysis<Type> visit(Neg expr, State state) {
        // Check here for returned type and if Neg is applicalbe
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SemanticAnalysis<Type> visit(Not expr, State state) {
        // Check here for returned type and if Not is applicalbe
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SemanticAnalysis<Type> visit(EMul expr, State state) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SemanticAnalysis<Type> visit(EAdd expr, State state) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SemanticAnalysis<Type> visit(ERel expr, State state) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SemanticAnalysis<Type> visit(EAnd expr, State state) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SemanticAnalysis<Type> visit(EOr expr, State state) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private SemanticAnalysis<Type> createSemanticAnalysis(Type type) {
        SemanticAnalysis<Type> semanticAnalysis = new SemanticAnalysis<Type>();
        semanticAnalysis.setT(type);
        return semanticAnalysis;
    }

    private SemanticAnalysis<Type> createSemanticAnalysis(SemanticError error) {
        SemanticAnalysis<Type> semanticAnalysis = new SemanticAnalysis<Type>();
        semanticAnalysis.getErrors().add(error);
        return semanticAnalysis;
    }
}
