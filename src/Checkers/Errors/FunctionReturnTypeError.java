package Checkers.Errors;

import Latte.Absyn.Type;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/16/13
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class FunctionReturnTypeError extends TypeError {
    public FunctionReturnTypeError(Type expected, Type was) {
        super(expected, was);
    }

    @Override
    public String toString() {
        return "Function return type error: " + super.toString();
    }
}
