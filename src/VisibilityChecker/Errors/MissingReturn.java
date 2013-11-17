package VisibilityChecker.Errors;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/16/13
 * Time: 10:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class MissingReturn extends VisibilityError {

    public MissingReturn(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Return is missing from function \'" + id + "\'";
    }
}
