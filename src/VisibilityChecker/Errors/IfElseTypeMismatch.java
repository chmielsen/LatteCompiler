package VisibilityChecker.Errors;

import Latte.Absyn.Type;
import Latte.PrettyPrinter;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/16/13
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class IfElseTypeMismatch extends TypeError {
    public IfElseTypeMismatch(Type expected, Type was) {
        super(expected, was);
    }

    @Override
    public String toString() {
        return "If-else condition returns different types: \'"
                + PrettyPrinter.show(expected) + "\' and \'" +
                PrettyPrinter.show(was) + "\'";
    }
}
