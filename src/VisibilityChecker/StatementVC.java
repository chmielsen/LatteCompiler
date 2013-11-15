package VisibilityChecker;

import Latte.Absyn.*;
import VisibilityChecker.Errors.DuplicatedIdentifier;
import VisibilityChecker.Errors.IdentifierNotVisible;
import VisibilityChecker.Errors.TypeError;
import VisibilityChecker.Errors.VisibilityError;

import javax.print.DocFlavor;
import java.util.HashSet;
import java.util.Set;

import static VisibilityChecker.ExprCorrectnessChecker.*;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/12/13
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class StatementVC implements Stmt.Visitor<SemanticAnalysis, State> {

    @Override
    public SemanticAnalysis visit(SBStmt p, State state) {
        try {
            State visibleIdsCopy = (State)state.clone();
            return p.block_.accept(new BlockVC(), visibleIdsCopy);
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public SemanticAnalysis visit(SEmpty p, State state) {
        return null;
    }

    @Override
    public SemanticAnalysis visit(SDecl p, State state) {
        SemanticAnalysis analysis = new SemanticAnalysis();
        for(Item item : p.listitem_) {
            String identifier;
            // Cast to get the identifier
            if (item instanceof SInit) {
                identifier = ((SInit) item).ident_;
                SemanticAnalysis exprAnalysis = ((SInit) item).expr_.accept(new ExprCorrectnessChecker(), state);
                analysis.merge(exprAnalysis);
            } else if (item instanceof SNoInit) {
                identifier = ((SNoInit) item).ident_;
            } else {
                throw new UnsupportedClassVersionError(item.getClass().toString());
            }

            if (state.hasId(identifier)) {
                analysis.getErrors().add(new DuplicatedIdentifier(identifier));
            } else {
                // TODO
            }
        }
        return analysis;
    }

    @Override
    public SemanticAnalysis visit(SAss p, State state) {
        // bloki
        return null;
    }

    @Override
    public SemanticAnalysis visit(SIncr p, State state) {
        if (!state.hasId(p.ident_)) {
            return createSemanticAnalysis(new IdentifierNotVisible(p.ident_));
        } else if (INT.equals(state.getIdentifierType(p.ident_))) {
            return createSemanticAnalysis(new TypeError(INT, state.getIdentifierType(p.ident_)));
        } else {
            return new SemanticAnalysis();
        }
    }

    @Override
    public SemanticAnalysis visit(SDecr p, State state) {
        if (!state.hasId(p.ident_)) {
            return createSemanticAnalysis(new IdentifierNotVisible(p.ident_));
        } else if (INT.equals(state.getIdentifierType(p.ident_))) {
            return createSemanticAnalysis(new TypeError(INT, state.getIdentifierType(p.ident_)));
        } else {
            return new SemanticAnalysis();
        }
    }

    @Override
    public SemanticAnalysis visit(SRet p, State state) {
        SemanticAnalysis<Type> exprAnalysis = p.expr_.accept(new ExprCorrectnessChecker(), state);
        SemanticAnalysis analysis = new SemanticAnalysis();
        analysis.setErrors(exprAnalysis.getErrors());
        return analysis;
    }

    @Override
    public SemanticAnalysis visit(SVRet p, State state) {
        return new SemanticAnalysis();
    }

    @Override
    public SemanticAnalysis visit(SCond p, State state) {
        return null;
        /*
        SemanticAnalysis errors = new HashSemanticAnalysis();
        errors.addAll(p.expr_.accept(new ExpressionVC(), state));
        errors.addAll(p.stmt_.accept(this, state));
        return errors;
        */
    }

    @Override
    public SemanticAnalysis visit(SCondElse p, State state) {
        /*
        SemanticAnalysis errors = new HashSemanticAnalysis();
        errors.addAll(p.expr_.accept(new ExpressionVC(), state));
        errors.addAll(p.stmt_1.accept(this, state));
        errors.addAll(p.stmt_2.accept(this, state));
        return errors;
        */
        return null;
    }

    @Override
    public SemanticAnalysis visit(SWhile p, State state) {
        return null;
    }

    @Override
    public SemanticAnalysis visit(SExp p, State state) {
        return null;
    }

}
