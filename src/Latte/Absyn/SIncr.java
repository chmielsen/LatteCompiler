package Latte.Absyn; // Java Package generated by the BNF Converter.

public class SIncr extends Stmt {
  public final String ident_;

  public SIncr(String p1) { ident_ = p1; }

  public <R,A> R accept(Latte.Absyn.Stmt.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Latte.Absyn.SIncr) {
      Latte.Absyn.SIncr x = (Latte.Absyn.SIncr)o;
      return this.ident_.equals(x.ident_);
    }
    return false;
  }

  public int hashCode() {
    return this.ident_.hashCode();
  }


}
