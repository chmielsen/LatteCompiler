package VisibilityChecker.Errors;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/14/13
 * Time: 1:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class WrongNumberOfArguments extends VisibilityError {

    public WrongNumberOfArguments(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Wrong number of arguments when applying function: \'"  + id + "\'";
    }
}
