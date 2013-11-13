package FunctionRecognizer;

import Latte.Absyn.ListType;
import Latte.Absyn.Type;
import Latte.PrettyPrinter;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/11/13
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class FunctionSignature {
    public Type returnType;
    public ListType argumentTypes;

    public FunctionSignature(Type returnType, ListType argumentTypes) {
        this.returnType = returnType;
        this.argumentTypes = argumentTypes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(PrettyPrinter.print(returnType));
        builder.append(" (");
        for (Type t : argumentTypes) {
            builder.append(PrettyPrinter.print(t));
            builder.append(", ");
        }
        if (argumentTypes.size() > 0) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FunctionSignature signature = (FunctionSignature) o;

        if (argumentTypes != null ? !argumentTypes.equals(signature.argumentTypes) : signature.argumentTypes != null)
            return false;
        if (returnType != null ? !returnType.equals(signature.returnType) : signature.returnType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = returnType != null ? returnType.hashCode() : 0;
        result = 31 * result + (argumentTypes != null ? argumentTypes.hashCode() : 0);
        return result;
    }


}
