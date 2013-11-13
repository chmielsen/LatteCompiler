package VisibilityChecker;

import Latte.Absyn.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/12/13
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class StatementVC implements Stmt.Visitor<Set<VisibilityError>, Set<String>> {

    @Override
    public Set<VisibilityError> visit(SBStmt p, Set<String> visibleIds) {
        // we have to create a copy of visibleIds, so the identifiers
        // visible within the block won't be visible outside
        Set<String> visibleIdsCopy = new HashSet<String>();
        visibleIdsCopy.addAll(visibleIds);

        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.block_.accept(new BlockVC(), visibleIdsCopy));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SEmpty p, Set<String> visibleIds) {
        return null;
    }

    @Override
    public Set<VisibilityError> visit(SDecl p, Set<String> visibleIds) {
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

            if (visibleIds.contains(identifier)) {
                errors.add(new DuplicatedIdentifier(identifier));
            } else {
                visibleIds.add(identifier);
            }
        }
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SAss p, Set<String> visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        if (!visibleIds.contains(p.ident_)) {
            errors.add(new IdentifierNotVisible(p.ident_));
        }
        // check for visibility errors in assigned expression
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SIncr p, Set<String> visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        if (!visibleIds.contains(p.ident_)) {
            errors.add(new IdentifierNotVisible(p.ident_));
        }
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SDecr p, Set<String> visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        if (!visibleIds.contains(p.ident_)) {
            errors.add(new IdentifierNotVisible(p.ident_));
        }
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SRet p, Set<String> visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SVRet p, Set<String> visibleIds) {
        return null;
    }

    @Override
    public Set<VisibilityError> visit(SCond p, Set<String> visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        errors.addAll(p.stmt_.accept(this, visibleIds));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SCondElse p, Set<String> visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        errors.addAll(p.stmt_1.accept(this, visibleIds));
        errors.addAll(p.stmt_2.accept(this, visibleIds));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SWhile p, Set<String> visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        errors.addAll(p.stmt_.accept(this, visibleIds));
        return errors;
    }

    @Override
    public Set<VisibilityError> visit(SExp p, Set<String> visibleIds) {
        Set<VisibilityError> errors = new HashSet<VisibilityError>();
        errors.addAll(p.expr_.accept(new ExpressionVC(), visibleIds));
        return errors;
    }

}
