package Latte.Absyn; // Java Package generated by the BNF Converter.

public class TStr extends Type {

  public TStr() { }

  public <R,A> R accept(Latte.Absyn.Type.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Latte.Absyn.TStr) {
      return true;
    }
    return false;
  }

  public int hashCode() {
    return 37;
  }


}
