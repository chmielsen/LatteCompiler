package FunctionRecognizer.Errors;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/13/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class MissingReturn extends ReturnError {

    public MissingReturn(String functionId) {
        super(functionId);
    }

    @Override
    public String toString() {
        return "Function '" + functionId + "\' is missing a return statement";
    }
}
