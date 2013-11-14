package VisibilityChecker.Errors;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/11/13
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class VisibilityError implements SemanticError {
    protected String id;

    public VisibilityError(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisibilityError)) return false;

        VisibilityError that = (VisibilityError) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
