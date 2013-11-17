package VisibilityChecker.Errors;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/15/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class DuplicatedWithinBlock extends VisibilityError {
    public DuplicatedWithinBlock(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Id \'" + id + "\' was previously declared in the same block";
    }
}
