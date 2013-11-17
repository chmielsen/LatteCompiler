package FunctionRecognizer.Errors;

/**
 * Class representing error occuring on non empty return
 * statements in functions returning type void
 */
public class NonEmptyReturnOnVoid extends ReturnError {

    NonEmptyReturnOnVoid (String functionId) {
        super(functionId);
    }

    @Override
    public String toString() {
       return "Function \'" + functionId + "/' is type void, but has a non empty return";
    }
}
