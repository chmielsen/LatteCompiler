package Latte.Absyn; // Java Package generated by the BNF Converter.

public class SInit extends Item {
  public final String ident_;
  public final Expr expr_;

  public SInit(String p1, Expr p2) { ident_ = p1; expr_ = p2; }

  public <R,A> R accept(Latte.Absyn.Item.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Latte.Absyn.SInit) {
      Latte.Absyn.SInit x = (Latte.Absyn.SInit)o;
      return this.ident_.equals(x.ident_) && this.expr_.equals(x.expr_);
    }
    return false;
  }

  public int hashCode() {
    return 37*(this.ident_.hashCode())+this.expr_.hashCode();
  }


}