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

    protected Type expected;
    protected Type was;

    public TypeError(Type expected, Type was) {
        this.was = was;
        this.expected = expected;
    }

    @Override
    public String toString() {
        return "Expected type: \'" + PrettyPrinter.print(expected) + "\', but was \'" +
                PrettyPrinter.print(was) + "\'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeError)) return false;

        TypeError typeError = (TypeError) o;

        if (expected != null ? !expected.equals(typeError.expected) : typeError.expected != null) return false;
        if (was != null ? !was.equals(typeError.was) : typeError.was != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expected != null ? expected.hashCode() : 0;
        result = 31 * result + (was != null ? was.hashCode() : 0);
        return result;
    }
}
