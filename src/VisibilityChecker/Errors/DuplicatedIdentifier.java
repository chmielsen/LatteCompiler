package VisibilityChecker.Errors;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/12/13
 * Time: 12:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class DuplicatedIdentifier extends VisibilityError {

    public DuplicatedIdentifier(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Identifier \'" + id + "\' was previously declared.";
    }
}
