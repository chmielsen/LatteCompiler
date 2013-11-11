package Latte;
import Latte.Absyn.*;
/** BNFC-Generated Abstract Visitor */
public class AbstractVisitor<R,A> implements AllVisitor<R,A> {
/* Program */
    public R visit(Latte.Absyn.Program p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Latte.Absyn.Program p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* TopDef */
    public R visit(Latte.Absyn.FnDef p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Latte.Absyn.TopDef p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Arg */
    public R visit(Latte.Absyn.Arg p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Latte.Absyn.Arg p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Block */
    public R visit(Latte.Absyn.Block p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Latte.Absyn.Block p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Stmt */
    public R visit(Latte.Absyn.SEmpty p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SBStmt p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SDecl p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SAss p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SIncr p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SDecr p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SRet p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SVRet p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SCond p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SCondElse p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SWhile p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SExp p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Latte.Absyn.Stmt p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Item */
    public R visit(Latte.Absyn.SNoInit p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.SInit p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Latte.Absyn.Item p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Type */
    public R visit(Latte.Absyn.TInt p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.TStr p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.TBool p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.TVoid p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.TFun p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Latte.Absyn.Type p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* Expr */
    public R visit(Latte.Absyn.EVar p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.ELitInt p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.ELitTrue p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.ELitFalse p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.EApp p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.EString p, A arg) { return visitDefault(p, arg); }

    public R visit(Latte.Absyn.Neg p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.Not p, A arg) { return visitDefault(p, arg); }

    public R visit(Latte.Absyn.EMul p, A arg) { return visitDefault(p, arg); }

    public R visit(Latte.Absyn.EAdd p, A arg) { return visitDefault(p, arg); }

    public R visit(Latte.Absyn.ERel p, A arg) { return visitDefault(p, arg); }

    public R visit(Latte.Absyn.EAnd p, A arg) { return visitDefault(p, arg); }

    public R visit(Latte.Absyn.EOr p, A arg) { return visitDefault(p, arg); }

    public R visitDefault(Latte.Absyn.Expr p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* AddOp */
    public R visit(Latte.Absyn.Plus p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.Minus p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Latte.Absyn.AddOp p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* MulOp */
    public R visit(Latte.Absyn.Times p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.Div p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.Mod p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Latte.Absyn.MulOp p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
/* RelOp */
    public R visit(Latte.Absyn.LTH p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.LE p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.GTH p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.GE p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.EQU p, A arg) { return visitDefault(p, arg); }
    public R visit(Latte.Absyn.NE p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(Latte.Absyn.RelOp p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }

}
