package VisibilityChecker;

import Latte.Absyn.Block;
import Latte.Absyn.Type;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/13/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class VariableDefinition {

    private Block declaredIn;
    private Type type;

    public VariableDefinition(Block declaredIn, Type type) {
        this.declaredIn = declaredIn;
        this.type = type;
    }

    public Block getDeclaredIn() {
        return declaredIn;
    }

    public void setDeclaredIn(Block declaredIn) {
        this.declaredIn = declaredIn;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VariableDefinition)) return false;

        VariableDefinition that = (VariableDefinition) o;

        if (declaredIn != null ? !declaredIn.equals(that.declaredIn) : that.declaredIn != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = declaredIn != null ? declaredIn.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VariableDefinition{" +
                "declaredIn=" + declaredIn +
                ", type=" + type +
                '}';
    }
}
