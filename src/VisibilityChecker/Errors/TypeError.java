package VisibilityChecker.Errors;

import Latte.Absyn.Type;
import Latte.PrettyPrinter;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/13/13
 * Time: 11:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class TypeError implements SemanticError {

    private Type expected;
    private Type was;

    public TypeError(Type expected, Type was) {
        this.was = was;
        this.expected = expected;
    }

    @Override
    public String toString() {
        return "Expected type: \'" + PrettyPrinter.print(expected) + "\', but was \'" +
                PrettyPrinter.print(was) + "\'";
    }
}
