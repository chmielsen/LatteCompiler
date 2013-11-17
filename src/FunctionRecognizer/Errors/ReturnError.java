package FunctionRecognizer.Errors;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/13/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
abstract public class ReturnError {
    String functionId;

    protected ReturnError(String functionId) {
        this.functionId = functionId;
    }
}
