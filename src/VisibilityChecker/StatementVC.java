package VisibilityChecker;

import Latte.Absyn.*;
import VisibilityChecker.Errors.DuplicatedIdentifier;
import VisibilityChecker.Errors.IdentifierNotVisible;
import VisibilityChecker.Errors.VisibilityError;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/12/13
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class StatementVC implements Stmt.Visitor<Set<VisibilityError>, State> {

    @Override
    public Set<VisibilityError> visit(SBStmt p, State visibleIds) {
        // we have to create a copy of visibleIds, so the identifiers
        // visible within the block won't be visible outside
        State visibleIdsCopy = new State();
        visibleIdsCopy.putAll(visibleIds);

        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.block_.accept(new BlockVC(), visibleIdsCopy));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SEmpty p, State visibleIds) {
        return null;
    }

    @Override
    public Set<VisibilityError> visit(SDecl p, State visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        for(Item item : p.listitem_) {
            String identifier;
            // Cast to get the identifier
            if (item instanceof SInit) {
                identifier = ((SInit) item).ident_;
                Set<VisibilityError> exprErrors = ((SInit) item).expr_.accept(new ExpressionVC(), visibleIds);
                errors.addAll(exprErrors);
            } else if (item instanceof SNoInit) {
                identifier = ((SNoInit) item).ident_;
            } else {
                throw new UnsupportedClassVersionError(item.getClass().toString());
            }

            if (visibleIds.containsKey(identifier)) {
                errors.add(new DuplicatedIdentifier(identifier));
            } else {
                visibleIds.get(identifier).add()
                visibleIds.add(identifier);
            }
        }
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SAss p, State visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        if (!visibleIds.contains(p.ident_)) {
            errors.add(new IdentifierNotVisible(p.ident_));
        }
        // check for visibility errors in assigned expression
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SIncr p, State visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        if (!visibleIds.contains(p.ident_)) {
            errors.add(new IdentifierNotVisible(p.ident_));
        }
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SDecr p, State visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        if (!visibleIds.contains(p.ident_)) {
            errors.add(new IdentifierNotVisible(p.ident_));
        }
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SRet p, State visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SVRet p, State visibleIds) {
        return null;
    }

    @Override
    public Set<VisibilityError> visit(SCond p, State visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        errors.addAll(p.stmt_.accept(this, visibleIds));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SCondElse p, State visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        errors.addAll(p.stmt_1.accept(this, visibleIds));
        errors.addAll(p.stmt_2.accept(this, visibleIds));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SWhile p, State visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        errors.addAll(p.stmt_.accept(this, visibleIds));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SExp p, State visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        return errors;
    }

}
