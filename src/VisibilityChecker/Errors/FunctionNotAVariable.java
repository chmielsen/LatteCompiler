package VisibilityChecker.Errors;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/15/13
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class FunctionNotAVariable extends VisibilityError {
    public FunctionNotAVariable(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Function \'" + id + "\' is not a variable, you can\'t assign to it";
    }
}
