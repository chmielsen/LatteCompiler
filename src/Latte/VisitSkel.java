package Latte;
import Latte.Absyn.*;
/*** BNFC-Generated Visitor Design Pattern Skeleton. ***/
/* This implements the common visitor design pattern.
   Tests show it to be slightly less efficient than the
   instanceof method, but easier to use. 
   Replace the R and A parameters with the desired return
   and context types.*/

public class VisitSkel
{
  public class ProgramVisitor<R,A> implements Program.Visitor<R,A>
  {
    public R visit(Latte.Absyn.Program p, A arg)
    {
      /* Code For Program Goes Here */

      for (TopDef x : p.listtopdef_) {
      }

      return null;
    }

  }
  public class TopDefVisitor<R,A> implements TopDef.Visitor<R,A>
  {
    public R visit(Latte.Absyn.FnDef p, A arg)
    {
      /* Code For FnDef Goes Here */

      p.type_.accept(new TypeVisitor<R,A>(), arg);
      //p.ident_;
      for (Arg x : p.listarg_) {
      }
      p.block_.accept(new BlockVisitor<R,A>(), arg);

      return null;
    }

  }
  public class ArgVisitor<R,A> implements Arg.Visitor<R,A>
  {
    public R visit(Latte.Absyn.Arg p, A arg)
    {
      /* Code For Arg Goes Here */

      p.type_.accept(new TypeVisitor<R,A>(), arg);
      //p.ident_;

      return null;
    }

  }
  public class BlockVisitor<R,A> implements Block.Visitor<R,A>
  {
    public R visit(Latte.Absyn.Block p, A arg)
    {
      /* Code For Block Goes Here */

      for (Stmt x : p.liststmt_) {
      }

      return null;
    }

  }
  public class StmtVisitor<R,A> implements Stmt.Visitor<R,A>
  {
    public R visit(Latte.Absyn.SEmpty p, A arg)
    {
      /* Code For SEmpty Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.SBStmt p, A arg)
    {
      /* Code For SBStmt Goes Here */

      p.block_.accept(new BlockVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.SDecl p, A arg)
    {
      /* Code For SDecl Goes Here */

      p.type_.accept(new TypeVisitor<R,A>(), arg);
      for (Item x : p.listitem_) {
      }

      return null;
    }
    public R visit(Latte.Absyn.SAss p, A arg)
    {
      /* Code For SAss Goes Here */

      //p.ident_;
      p.expr_.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.SIncr p, A arg)
    {
      /* Code For SIncr Goes Here */

      //p.ident_;

      return null;
    }
    public R visit(Latte.Absyn.SDecr p, A arg)
    {
      /* Code For SDecr Goes Here */

      //p.ident_;

      return null;
    }
    public R visit(Latte.Absyn.SRet p, A arg)
    {
      /* Code For SRet Goes Here */

      p.expr_.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.SVRet p, A arg)
    {
      /* Code For SVRet Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.SCond p, A arg)
    {
      /* Code For SCond Goes Here */

      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      p.stmt_.accept(new StmtVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.SCondElse p, A arg)
    {
      /* Code For SCondElse Goes Here */

      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      p.stmt_1.accept(new StmtVisitor<R,A>(), arg);
      p.stmt_2.accept(new StmtVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.SWhile p, A arg)
    {
      /* Code For SWhile Goes Here */

      p.expr_.accept(new ExprVisitor<R,A>(), arg);
      p.stmt_.accept(new StmtVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.SExp p, A arg)
    {
      /* Code For SExp Goes Here */

      p.expr_.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }

  }
  public class ItemVisitor<R,A> implements Item.Visitor<R,A>
  {
    public R visit(Latte.Absyn.SNoInit p, A arg)
    {
      /* Code For SNoInit Goes Here */

      //p.ident_;

      return null;
    }
    public R visit(Latte.Absyn.SInit p, A arg)
    {
      /* Code For SInit Goes Here */

      //p.ident_;
      p.expr_.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }

  }
  public class TypeVisitor<R,A> implements Type.Visitor<R,A>
  {
    public R visit(Latte.Absyn.TInt p, A arg)
    {
      /* Code For TInt Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.TStr p, A arg)
    {
      /* Code For TStr Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.TBool p, A arg)
    {
      /* Code For TBool Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.TVoid p, A arg)
    {
      /* Code For TVoid Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.TFun p, A arg)
    {
      /* Code For TFun Goes Here */

      p.type_.accept(new TypeVisitor<R,A>(), arg);
      for (Type x : p.listtype_) {
      }

      return null;
    }

  }
  public class ExprVisitor<R,A> implements Expr.Visitor<R,A>
  {
    public R visit(Latte.Absyn.EVar p, A arg)
    {
      /* Code For EVar Goes Here */

      //p.ident_;

      return null;
    }
    public R visit(Latte.Absyn.ELitInt p, A arg)
    {
      /* Code For ELitInt Goes Here */

      //p.integer_;

      return null;
    }
    public R visit(Latte.Absyn.ELitTrue p, A arg)
    {
      /* Code For ELitTrue Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.ELitFalse p, A arg)
    {
      /* Code For ELitFalse Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.EApp p, A arg)
    {
      /* Code For EApp Goes Here */

      //p.ident_;
      for (Expr x : p.listexpr_) {
      }

      return null;
    }
    public R visit(Latte.Absyn.EString p, A arg)
    {
      /* Code For EString Goes Here */

      //p.string_;

      return null;
    }
    public R visit(Latte.Absyn.Neg p, A arg)
    {
      /* Code For Neg Goes Here */

      p.expr_.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.Not p, A arg)
    {
      /* Code For Not Goes Here */

      p.expr_.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.EMul p, A arg)
    {
      /* Code For EMul Goes Here */

      p.expr_1.accept(new ExprVisitor<R,A>(), arg);
      p.mulop_.accept(new MulOpVisitor<R,A>(), arg);
      p.expr_2.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.EAdd p, A arg)
    {
      /* Code For EAdd Goes Here */

      p.expr_1.accept(new ExprVisitor<R,A>(), arg);
      p.addop_.accept(new AddOpVisitor<R,A>(), arg);
      p.expr_2.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.ERel p, A arg)
    {
      /* Code For ERel Goes Here */

      p.expr_1.accept(new ExprVisitor<R,A>(), arg);
      p.relop_.accept(new RelOpVisitor<R,A>(), arg);
      p.expr_2.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.EAnd p, A arg)
    {
      /* Code For EAnd Goes Here */

      p.expr_1.accept(new ExprVisitor<R,A>(), arg);
      p.expr_2.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }
    public R visit(Latte.Absyn.EOr p, A arg)
    {
      /* Code For EOr Goes Here */

      p.expr_1.accept(new ExprVisitor<R,A>(), arg);
      p.expr_2.accept(new ExprVisitor<R,A>(), arg);

      return null;
    }

  }
  public class AddOpVisitor<R,A> implements AddOp.Visitor<R,A>
  {
    public R visit(Latte.Absyn.Plus p, A arg)
    {
      /* Code For Plus Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.Minus p, A arg)
    {
      /* Code For Minus Goes Here */


      return null;
    }

  }
  public class MulOpVisitor<R,A> implements MulOp.Visitor<R,A>
  {
    public R visit(Latte.Absyn.Times p, A arg)
    {
      /* Code For Times Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.Div p, A arg)
    {
      /* Code For Div Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.Mod p, A arg)
    {
      /* Code For Mod Goes Here */


      return null;
    }

  }
  public class RelOpVisitor<R,A> implements RelOp.Visitor<R,A>
  {
    public R visit(Latte.Absyn.LTH p, A arg)
    {
      /* Code For LTH Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.LE p, A arg)
    {
      /* Code For LE Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.GTH p, A arg)
    {
      /* Code For GTH Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.GE p, A arg)
    {
      /* Code For GE Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.EQU p, A arg)
    {
      /* Code For EQU Goes Here */


      return null;
    }
    public R visit(Latte.Absyn.NE p, A arg)
    {
      /* Code For NE Goes Here */


      return null;
    }

  }
}