package Checkers.Errors;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/12/13
 * Time: 12:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class IdentifierNotVisible extends VisibilityError {

    public IdentifierNotVisible(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Identifier \'" + id + "\' is not visible in that scope.";
    }
}
