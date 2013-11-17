package Utils;

import Latte.Absyn.Type;

/**
 * Class represeting expected type with current state
 */
public class ExpectedType {

    private State state;

    private Type type;

    public ExpectedType(State state, Type type) {
        this.state = state;
        this.type = type;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ExpectedType{" +
                "state=" + state +
                ", type=" + type +
                '}';
    }

    public void setType(Type type) {
        this.type = type;
    }
}
