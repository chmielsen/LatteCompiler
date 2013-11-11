package Latte;
import Latte.Absyn.*;
/** BNFC-Generated Composition Visitor
*/

public class ComposVisitor<A> implements
  Latte.Absyn.Program.Visitor<Latte.Absyn.Program,A>,
  Latte.Absyn.TopDef.Visitor<Latte.Absyn.TopDef,A>,
  Latte.Absyn.Arg.Visitor<Latte.Absyn.Arg,A>,
  Latte.Absyn.Block.Visitor<Latte.Absyn.Block,A>,
  Latte.Absyn.Stmt.Visitor<Latte.Absyn.Stmt,A>,
  Latte.Absyn.Item.Visitor<Latte.Absyn.Item,A>,
  Latte.Absyn.Type.Visitor<Latte.Absyn.Type,A>,
  Latte.Absyn.Expr.Visitor<Latte.Absyn.Expr,A>,
  Latte.Absyn.AddOp.Visitor<Latte.Absyn.AddOp,A>,
  Latte.Absyn.MulOp.Visitor<Latte.Absyn.MulOp,A>,
  Latte.Absyn.RelOp.Visitor<Latte.Absyn.RelOp,A>
{
/* Program */
    public Program visit(Latte.Absyn.Program p, A arg)
    {
      ListTopDef listtopdef_ = new ListTopDef();
      for (TopDef x : p.listtopdef_) {
        listtopdef_.add(x.accept(this,arg));
      }

      return new Latte.Absyn.Program(listtopdef_);
    }

/* TopDef */
    public TopDef visit(Latte.Absyn.FnDef p, A arg)
    {
      Type type_ = p.type_.accept(this, arg);
      String ident_ = p.ident_;
      ListArg listarg_ = new ListArg();
      for (Arg x : p.listarg_) {
        listarg_.add(x.accept(this,arg));
      }
      Block block_ = p.block_.accept(this, arg);

      return new Latte.Absyn.FnDef(type_, ident_, listarg_, block_);
    }

/* Arg */
    public Arg visit(Latte.Absyn.Arg p, A arg)
    {
      Type type_ = p.type_.accept(this, arg);
      String ident_ = p.ident_;

      return new Latte.Absyn.Arg(type_, ident_);
    }

/* Block */
    public Block visit(Latte.Absyn.Block p, A arg)
    {
      ListStmt liststmt_ = new ListStmt();
      for (Stmt x : p.liststmt_) {
        liststmt_.add(x.accept(this,arg));
      }

      return new Latte.Absyn.Block(liststmt_);
    }

/* Stmt */
    public Stmt visit(Latte.Absyn.SEmpty p, A arg)
    {

      return new Latte.Absyn.SEmpty();
    }
    public Stmt visit(Latte.Absyn.SBStmt p, A arg)
    {
      Block block_ = p.block_.accept(this, arg);

      return new Latte.Absyn.SBStmt(block_);
    }
    public Stmt visit(Latte.Absyn.SDecl p, A arg)
    {
      Type type_ = p.type_.accept(this, arg);
      ListItem listitem_ = new ListItem();
      for (Item x : p.listitem_) {
        listitem_.add(x.accept(this,arg));
      }

      return new Latte.Absyn.SDecl(type_, listitem_);
    }
    public Stmt visit(Latte.Absyn.SAss p, A arg)
    {
      String ident_ = p.ident_;
      Expr expr_ = p.expr_.accept(this, arg);

      return new Latte.Absyn.SAss(ident_, expr_);
    }
    public Stmt visit(Latte.Absyn.SIncr p, A arg)
    {
      String ident_ = p.ident_;

      return new Latte.Absyn.SIncr(ident_);
    }
    public Stmt visit(Latte.Absyn.SDecr p, A arg)
    {
      String ident_ = p.ident_;

      return new Latte.Absyn.SDecr(ident_);
    }
    public Stmt visit(Latte.Absyn.SRet p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);

      return new Latte.Absyn.SRet(expr_);
    }
    public Stmt visit(Latte.Absyn.SVRet p, A arg)
    {

      return new Latte.Absyn.SVRet();
    }
    public Stmt visit(Latte.Absyn.SCond p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);
      Stmt stmt_ = p.stmt_.accept(this, arg);

      return new Latte.Absyn.SCond(expr_, stmt_);
    }
    public Stmt visit(Latte.Absyn.SCondElse p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);
      Stmt stmt_1 = p.stmt_1.accept(this, arg);
      Stmt stmt_2 = p.stmt_2.accept(this, arg);

      return new Latte.Absyn.SCondElse(expr_, stmt_1, stmt_2);
    }
    public Stmt visit(Latte.Absyn.SWhile p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);
      Stmt stmt_ = p.stmt_.accept(this, arg);

      return new Latte.Absyn.SWhile(expr_, stmt_);
    }
    public Stmt visit(Latte.Absyn.SExp p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);

      return new Latte.Absyn.SExp(expr_);
    }

/* Item */
    public Item visit(Latte.Absyn.SNoInit p, A arg)
    {
      String ident_ = p.ident_;

      return new Latte.Absyn.SNoInit(ident_);
    }
    public Item visit(Latte.Absyn.SInit p, A arg)
    {
      String ident_ = p.ident_;
      Expr expr_ = p.expr_.accept(this, arg);

      return new Latte.Absyn.SInit(ident_, expr_);
    }

/* Type */
    public Type visit(Latte.Absyn.TInt p, A arg)
    {

      return new Latte.Absyn.TInt();
    }
    public Type visit(Latte.Absyn.TStr p, A arg)
    {

      return new Latte.Absyn.TStr();
    }
    public Type visit(Latte.Absyn.TBool p, A arg)
    {

      return new Latte.Absyn.TBool();
    }
    public Type visit(Latte.Absyn.TVoid p, A arg)
    {

      return new Latte.Absyn.TVoid();
    }
    public Type visit(Latte.Absyn.TFun p, A arg)
    {
      Type type_ = p.type_.accept(this, arg);
      ListType listtype_ = new ListType();
      for (Type x : p.listtype_) {
        listtype_.add(x.accept(this,arg));
      }

      return new Latte.Absyn.TFun(type_, listtype_);
    }

/* Expr */
    public Expr visit(Latte.Absyn.EVar p, A arg)
    {
      String ident_ = p.ident_;

      return new Latte.Absyn.EVar(ident_);
    }
    public Expr visit(Latte.Absyn.ELitInt p, A arg)
    {
      Integer integer_ = p.integer_;

      return new Latte.Absyn.ELitInt(integer_);
    }
    public Expr visit(Latte.Absyn.ELitTrue p, A arg)
    {

      return new Latte.Absyn.ELitTrue();
    }
    public Expr visit(Latte.Absyn.ELitFalse p, A arg)
    {

      return new Latte.Absyn.ELitFalse();
    }
    public Expr visit(Latte.Absyn.EApp p, A arg)
    {
      String ident_ = p.ident_;
      ListExpr listexpr_ = new ListExpr();
      for (Expr x : p.listexpr_) {
        listexpr_.add(x.accept(this,arg));
      }

      return new Latte.Absyn.EApp(ident_, listexpr_);
    }
    public Expr visit(Latte.Absyn.EString p, A arg)
    {
      String string_ = p.string_;

      return new Latte.Absyn.EString(string_);
    }
    public Expr visit(Latte.Absyn.Neg p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);

      return new Latte.Absyn.Neg(expr_);
    }
    public Expr visit(Latte.Absyn.Not p, A arg)
    {
      Expr expr_ = p.expr_.accept(this, arg);

      return new Latte.Absyn.Not(expr_);
    }
    public Expr visit(Latte.Absyn.EMul p, A arg)
    {
      Expr expr_1 = p.expr_1.accept(this, arg);
      MulOp mulop_ = p.mulop_.accept(this, arg);
      Expr expr_2 = p.expr_2.accept(this, arg);

      return new Latte.Absyn.EMul(expr_1, mulop_, expr_2);
    }
    public Expr visit(Latte.Absyn.EAdd p, A arg)
    {
      Expr expr_1 = p.expr_1.accept(this, arg);
      AddOp addop_ = p.addop_.accept(this, arg);
      Expr expr_2 = p.expr_2.accept(this, arg);

      return new Latte.Absyn.EAdd(expr_1, addop_, expr_2);
    }
    public Expr visit(Latte.Absyn.ERel p, A arg)
    {
      Expr expr_1 = p.expr_1.accept(this, arg);
      RelOp relop_ = p.relop_.accept(this, arg);
      Expr expr_2 = p.expr_2.accept(this, arg);

      return new Latte.Absyn.ERel(expr_1, relop_, expr_2);
    }
    public Expr visit(Latte.Absyn.EAnd p, A arg)
    {
      Expr expr_1 = p.expr_1.accept(this, arg);
      Expr expr_2 = p.expr_2.accept(this, arg);

      return new Latte.Absyn.EAnd(expr_1, expr_2);
    }
    public Expr visit(Latte.Absyn.EOr p, A arg)
    {
      Expr expr_1 = p.expr_1.accept(this, arg);
      Expr expr_2 = p.expr_2.accept(this, arg);

      return new Latte.Absyn.EOr(expr_1, expr_2);
    }

/* AddOp */
    public AddOp visit(Latte.Absyn.Plus p, A arg)
    {

      return new Latte.Absyn.Plus();
    }
    public AddOp visit(Latte.Absyn.Minus p, A arg)
    {

      return new Latte.Absyn.Minus();
    }

/* MulOp */
    public MulOp visit(Latte.Absyn.Times p, A arg)
    {

      return new Latte.Absyn.Times();
    }
    public MulOp visit(Latte.Absyn.Div p, A arg)
    {

      return new Latte.Absyn.Div();
    }
    public MulOp visit(Latte.Absyn.Mod p, A arg)
    {

      return new Latte.Absyn.Mod();
    }

/* RelOp */
    public RelOp visit(Latte.Absyn.LTH p, A arg)
    {

      return new Latte.Absyn.LTH();
    }
    public RelOp visit(Latte.Absyn.LE p, A arg)
    {

      return new Latte.Absyn.LE();
    }
    public RelOp visit(Latte.Absyn.GTH p, A arg)
    {

      return new Latte.Absyn.GTH();
    }
    public RelOp visit(Latte.Absyn.GE p, A arg)
    {

      return new Latte.Absyn.GE();
    }
    public RelOp visit(Latte.Absyn.EQU p, A arg)
    {

      return new Latte.Absyn.EQU();
    }
    public RelOp visit(Latte.Absyn.NE p, A arg)
    {

      return new Latte.Absyn.NE();
    }

}