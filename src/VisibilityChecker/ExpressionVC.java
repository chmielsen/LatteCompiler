package VisibilityChecker;

import Latte.Absyn.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Class implementing Visitor desing pattern for expressions.
 * It checks whether all identifiers used in an expression are
 * visible within the scope.
 * Each visit method should not modify set of visible ids.
 */
public class ExpressionVC implements Expr.Visitor<Set<VisibilityError>, Set<String>> {
    @Override
    public Set<VisibilityError> visit(EVar p, Set<String> visibleIds) {
        if (visibleIds.contains(p.ident_)) {
            return null;
        } else {
            Set<VisibilityError> errorSet = new HashSet<VisibilityError>();
            errorSet.add(new IdentifierNotVisible(p.ident_));
            return errorSet;
        }
    }

    @Override
    public Set<VisibilityError> visit(ELitInt p, Set<String> visibleIds) {
        return null;
    }

    @Override
    public Set<VisibilityError> visit(ELitTrue p, Set<String> visibleIds) {
        return null;
    }

    @Override
    public Set<VisibilityError> visit(ELitFalse p, Set<String> visibleIds) {
        return null;
    }

    @Override
    public Set<VisibilityError> visit(EApp p, Set<String> visibleIds) {
        if (visibleIds.contains(p.ident_)) {
             return null;
        } else {
            Set<VisibilityError> errorSet = new HashSet<VisibilityError>();
            errorSet.add(new IdentifierNotVisible(p.ident_));
            return errorSet;
        }
    }

    @Override
    public Set<VisibilityError> visit(EString p, Set<String> visibleIds) {
        return null;
    }

    @Override
    public Set<VisibilityError> visit(Neg p, Set<String> visibleIds) {
        return p.expr_.accept(this, visibleIds);
    }

    @Override
    public Set<VisibilityError> visit(Not p, Set<String> visibleIds) {
        return p.expr_.accept(this, visibleIds);
    }

    @Override
    public Set<VisibilityError> visit(EMul p, Set<String> visibleIds) {
        Set<VisibilityError> leftErrors = p.expr_1.accept(this, visibleIds);
        if (leftErrors != null) {
            Set<VisibilityError> rightErrors = p.expr_2.accept(this, visibleIds);
            if (rightErrors != null)
                leftErrors.addAll(rightErrors);
            return leftErrors;
        } else {
            return p.expr_2.accept(this, visibleIds);
        }
    }

    @Override
    public Set<VisibilityError> visit(EAdd p, Set<String> visibleIds) {
        Set<VisibilityError> leftErrors = p.expr_1.accept(this, visibleIds);
        if (leftErrors != null) {
            Set<VisibilityError> rightErrors = p.expr_2.accept(this, visibleIds);
            if (rightErrors != null)
                leftErrors.addAll(rightErrors);
            return leftErrors;
        } else {
            return p.expr_2.accept(this, visibleIds);
        }
    }

    @Override
    public Set<VisibilityError> visit(ERel p, Set<String> visibleIds) {
        Set<VisibilityError> leftErrors = p.expr_1.accept(this, visibleIds);
        if (leftErrors != null) {
            Set<VisibilityError> rightErrors = p.expr_2.accept(this, visibleIds);
            if (rightErrors != null)
                leftErrors.addAll(rightErrors);
            return leftErrors;
        } else {
            return p.expr_2.accept(this, visibleIds);
        }
    }

    @Override
    public Set<VisibilityError> visit(EAnd p, Set<String> visibleIds) {
        Set<VisibilityError> leftErrors = p.expr_1.accept(this, visibleIds);
        if (leftErrors != null) {
            Set<VisibilityError> rightErrors = p.expr_2.accept(this, visibleIds);
            if (rightErrors != null)
                leftErrors.addAll(rightErrors);
            return leftErrors;
        } else {
            return p.expr_2.accept(this, visibleIds);
        }
    }

    @Override
    public Set<VisibilityError> visit(EOr p, Set<String> visibleIds) {
        Set<VisibilityError> leftErrors = p.expr_1.accept(this, visibleIds);
        if (leftErrors != null) {
            Set<VisibilityError> rightErrors = p.expr_2.accept(this, visibleIds);
            if (rightErrors != null)
                leftErrors.addAll(rightErrors);
            return leftErrors;
        } else {
            return p.expr_2.accept(this, visibleIds);
        }    }
}
