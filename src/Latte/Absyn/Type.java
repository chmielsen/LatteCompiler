package Latte.Absyn; // Java Package generated by the BNF Converter.

public abstract class Type implements java.io.Serializable {
  public abstract <R,A> R accept(Type.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(Latte.Absyn.TInt p, A arg);
    public R visit(Latte.Absyn.TStr p, A arg);
    public R visit(Latte.Absyn.TBool p, A arg);
    public R visit(Latte.Absyn.TVoid p, A arg);
    public R visit(Latte.Absyn.TFun p, A arg);

  }

}
