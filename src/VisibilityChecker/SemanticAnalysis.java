package VisibilityChecker;

import Latte.Absyn.Type;
import VisibilityChecker.Errors.SemanticError;
import VisibilityChecker.Errors.TypeError;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for clumsy way of representing return type and errors.
 * Before checking for value of type T one should check whether
 * there are any SemanticError's present.
 */
public class SemanticAnalysis<T> {
    private T t;
    private List<SemanticError> errors;

    public SemanticAnalysis(T t, List<SemanticError> errors) {
        this.t = t;
        this.errors = errors;
    }

    public SemanticAnalysis() {
       errors = new ArrayList<SemanticError>();
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public SemanticAnalysis<T> merge(SemanticAnalysis<T> semanticAnalysis) {
        if (semanticAnalysis == null) {
            return this;
        } else if (hasErrors() || semanticAnalysis.hasErrors()) {
            errors.addAll(semanticAnalysis.getErrors());
            return this;
        } else if (semanticAnalysis.getT().equals(this.t)) {
            return this;
        } else {
            if (t instanceof Type && semanticAnalysis.getT() instanceof Type) {
                errors.add(new TypeError((Type)semanticAnalysis.getT(), (Type)t));
            } else {
                errors.add(new SemanticError() {
                    @Override
                    public String toString() {
                        return "Something is fucked up.";
                    }
                });
            }
            return this;
        }
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<SemanticError> getErrors() {
        return errors;
    }

    public void setErrors(List<SemanticError> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SemanticAnalysis)) return false;

        SemanticAnalysis that = (SemanticAnalysis) o;

        if (errors != null ? !errors.equals(that.errors) : that.errors != null) return false;
        if (t != null ? !t.equals(that.t) : that.t != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = t != null ? t.hashCode() : 0;
        result = 31 * result + (errors != null ? errors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SemanticAnalysis{" +
                "t=" + t +
                ", errors=" + errors +
                '}';
    }
}
