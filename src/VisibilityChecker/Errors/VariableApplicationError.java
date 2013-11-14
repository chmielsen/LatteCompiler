package VisibilityChecker.Errors;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/14/13
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class VariableApplicationError extends VisibilityError {

    public VariableApplicationError(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "\'"  + id + "\' is a variable, not a function";
    }
}
