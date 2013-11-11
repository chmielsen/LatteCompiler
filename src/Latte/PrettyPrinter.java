package Latte;
import Latte.Absyn.*;

public class PrettyPrinter
{
  //For certain applications increasing the initial size of the buffer may improve performance.
  private static final int INITIAL_BUFFER_SIZE = 128;
  //You may wish to change the parentheses used in precedence.
  private static final String _L_PAREN = new String("(");
  private static final String _R_PAREN = new String(")");
  //You may wish to change render
  private static void render(String s)
  {
    if (s.equals("{"))
    {
       buf_.append("\n");
       indent();
       buf_.append(s);
       _n_ = _n_ + 2;
       buf_.append("\n");
       indent();
    }
    else if (s.equals("(") || s.equals("["))
       buf_.append(s);
    else if (s.equals(")") || s.equals("]"))
    {
       backup();
       buf_.append(s);
       buf_.append(" ");
    }
    else if (s.equals("}"))
    {
       _n_ = _n_ - 2;
       backup();
       backup();
       buf_.append(s);
       buf_.append("\n");
       indent();
    }
    else if (s.equals(","))
    {
       backup();
       buf_.append(s);
       buf_.append(" ");
    }
    else if (s.equals(";"))
    {
       backup();
       buf_.append(s);
       buf_.append("\n");
       indent();
    }
    else if (s.equals("")) return;
    else
    {
       buf_.append(s);
       buf_.append(" ");
    }
  }


  //  print and show methods are defined for each category.
  public static String print(Latte.Absyn.Program foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.Program foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.TopDef foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.TopDef foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.ListTopDef foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.ListTopDef foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.Arg foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.Arg foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.ListArg foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.ListArg foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.Block foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.Block foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.ListStmt foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.ListStmt foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.Stmt foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.Stmt foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.Item foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.Item foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.ListItem foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.ListItem foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.Type foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.Type foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.ListType foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.ListType foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.Expr foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.Expr foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.ListExpr foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.ListExpr foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.AddOp foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.AddOp foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.MulOp foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.MulOp foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(Latte.Absyn.RelOp foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(Latte.Absyn.RelOp foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  /***   You shouldn't need to change anything beyond this point.   ***/

  private static void pp(Latte.Absyn.Program foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.Program)
    {
       Latte.Absyn.Program _program = (Latte.Absyn.Program) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_program.listtopdef_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(Latte.Absyn.TopDef foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.FnDef)
    {
       Latte.Absyn.FnDef _fndef = (Latte.Absyn.FnDef) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_fndef.type_, 0);
       pp(_fndef.ident_, 0);
       render("(");
       pp(_fndef.listarg_, 0);
       render(")");
       pp(_fndef.block_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(Latte.Absyn.ListTopDef foo, int _i_)
  {
     for (java.util.Iterator<TopDef> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render("");
       } else {
         render("");
       }
     }
  }

  private static void pp(Latte.Absyn.Arg foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.Arg)
    {
       Latte.Absyn.Arg _arg = (Latte.Absyn.Arg) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_arg.type_, 0);
       pp(_arg.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(Latte.Absyn.ListArg foo, int _i_)
  {
     for (java.util.Iterator<Arg> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }
  }

  private static void pp(Latte.Absyn.Block foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.Block)
    {
       Latte.Absyn.Block _block = (Latte.Absyn.Block) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("{");
       pp(_block.liststmt_, 0);
       render("}");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(Latte.Absyn.ListStmt foo, int _i_)
  {
     for (java.util.Iterator<Stmt> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render("");
       } else {
         render("");
       }
     }
  }

  private static void pp(Latte.Absyn.Stmt foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.SEmpty)
    {
       Latte.Absyn.SEmpty _sempty = (Latte.Absyn.SEmpty) foo;
       if (_i_ > 0) render(_L_PAREN);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SBStmt)
    {
       Latte.Absyn.SBStmt _sbstmt = (Latte.Absyn.SBStmt) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sbstmt.block_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SDecl)
    {
       Latte.Absyn.SDecl _sdecl = (Latte.Absyn.SDecl) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sdecl.type_, 0);
       pp(_sdecl.listitem_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SAss)
    {
       Latte.Absyn.SAss _sass = (Latte.Absyn.SAss) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sass.ident_, 0);
       render("=");
       pp(_sass.expr_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SIncr)
    {
       Latte.Absyn.SIncr _sincr = (Latte.Absyn.SIncr) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sincr.ident_, 0);
       render("++");
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SDecr)
    {
       Latte.Absyn.SDecr _sdecr = (Latte.Absyn.SDecr) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sdecr.ident_, 0);
       render("--");
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SRet)
    {
       Latte.Absyn.SRet _sret = (Latte.Absyn.SRet) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("return");
       pp(_sret.expr_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SVRet)
    {
       Latte.Absyn.SVRet _svret = (Latte.Absyn.SVRet) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("return");
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SCond)
    {
       Latte.Absyn.SCond _scond = (Latte.Absyn.SCond) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("if");
       render("(");
       pp(_scond.expr_, 0);
       render(")");
       pp(_scond.stmt_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SCondElse)
    {
       Latte.Absyn.SCondElse _scondelse = (Latte.Absyn.SCondElse) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("if");
       render("(");
       pp(_scondelse.expr_, 0);
       render(")");
       pp(_scondelse.stmt_1, 0);
       render("else");
       pp(_scondelse.stmt_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SWhile)
    {
       Latte.Absyn.SWhile _swhile = (Latte.Absyn.SWhile) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("while");
       render("(");
       pp(_swhile.expr_, 0);
       render(")");
       pp(_swhile.stmt_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SExp)
    {
       Latte.Absyn.SExp _sexp = (Latte.Absyn.SExp) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sexp.expr_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(Latte.Absyn.Item foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.SNoInit)
    {
       Latte.Absyn.SNoInit _snoinit = (Latte.Absyn.SNoInit) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_snoinit.ident_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.SInit)
    {
       Latte.Absyn.SInit _sinit = (Latte.Absyn.SInit) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sinit.ident_, 0);
       render("=");
       pp(_sinit.expr_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(Latte.Absyn.ListItem foo, int _i_)
  {
     for (java.util.Iterator<Item> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }
  }

  private static void pp(Latte.Absyn.Type foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.TInt)
    {
       Latte.Absyn.TInt _tint = (Latte.Absyn.TInt) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("int");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.TStr)
    {
       Latte.Absyn.TStr _tstr = (Latte.Absyn.TStr) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("string");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.TBool)
    {
       Latte.Absyn.TBool _tbool = (Latte.Absyn.TBool) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("boolean");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.TVoid)
    {
       Latte.Absyn.TVoid _tvoid = (Latte.Absyn.TVoid) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("void");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.TFun)
    {
       Latte.Absyn.TFun _tfun = (Latte.Absyn.TFun) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_tfun.type_, 0);
       render("(");
       pp(_tfun.listtype_, 0);
       render(")");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(Latte.Absyn.ListType foo, int _i_)
  {
     for (java.util.Iterator<Type> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }
  }

  private static void pp(Latte.Absyn.Expr foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.EVar)
    {
       Latte.Absyn.EVar _evar = (Latte.Absyn.EVar) foo;
       if (_i_ > 6) render(_L_PAREN);
       pp(_evar.ident_, 0);
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.ELitInt)
    {
       Latte.Absyn.ELitInt _elitint = (Latte.Absyn.ELitInt) foo;
       if (_i_ > 6) render(_L_PAREN);
       pp(_elitint.integer_, 0);
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.ELitTrue)
    {
       Latte.Absyn.ELitTrue _elittrue = (Latte.Absyn.ELitTrue) foo;
       if (_i_ > 6) render(_L_PAREN);
       render("true");
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.ELitFalse)
    {
       Latte.Absyn.ELitFalse _elitfalse = (Latte.Absyn.ELitFalse) foo;
       if (_i_ > 6) render(_L_PAREN);
       render("false");
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.EApp)
    {
       Latte.Absyn.EApp _eapp = (Latte.Absyn.EApp) foo;
       if (_i_ > 6) render(_L_PAREN);
       pp(_eapp.ident_, 0);
       render("(");
       pp(_eapp.listexpr_, 0);
       render(")");
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.EString)
    {
       Latte.Absyn.EString _estring = (Latte.Absyn.EString) foo;
       if (_i_ > 6) render(_L_PAREN);
       printQuoted(_estring.string_);
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.Neg)
    {
       Latte.Absyn.Neg _neg = (Latte.Absyn.Neg) foo;
       if (_i_ > 5) render(_L_PAREN);
       render("-");
       pp(_neg.expr_, 6);
       if (_i_ > 5) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.Not)
    {
       Latte.Absyn.Not _not = (Latte.Absyn.Not) foo;
       if (_i_ > 5) render(_L_PAREN);
       render("!");
       pp(_not.expr_, 6);
       if (_i_ > 5) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.EMul)
    {
       Latte.Absyn.EMul _emul = (Latte.Absyn.EMul) foo;
       if (_i_ > 4) render(_L_PAREN);
       pp(_emul.expr_1, 4);
       pp(_emul.mulop_, 0);
       pp(_emul.expr_2, 5);
       if (_i_ > 4) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.EAdd)
    {
       Latte.Absyn.EAdd _eadd = (Latte.Absyn.EAdd) foo;
       if (_i_ > 3) render(_L_PAREN);
       pp(_eadd.expr_1, 3);
       pp(_eadd.addop_, 0);
       pp(_eadd.expr_2, 4);
       if (_i_ > 3) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.ERel)
    {
       Latte.Absyn.ERel _erel = (Latte.Absyn.ERel) foo;
       if (_i_ > 2) render(_L_PAREN);
       pp(_erel.expr_1, 2);
       pp(_erel.relop_, 0);
       pp(_erel.expr_2, 3);
       if (_i_ > 2) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.EAnd)
    {
       Latte.Absyn.EAnd _eand = (Latte.Absyn.EAnd) foo;
       if (_i_ > 1) render(_L_PAREN);
       pp(_eand.expr_1, 2);
       render("&&");
       pp(_eand.expr_2, 1);
       if (_i_ > 1) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.EOr)
    {
       Latte.Absyn.EOr _eor = (Latte.Absyn.EOr) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_eor.expr_1, 1);
       render("||");
       pp(_eor.expr_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(Latte.Absyn.ListExpr foo, int _i_)
  {
     for (java.util.Iterator<Expr> it = foo.iterator(); it.hasNext();)
     {
       pp(it.next(), 0);
       if (it.hasNext()) {
         render(",");
       } else {
         render("");
       }
     }
  }

  private static void pp(Latte.Absyn.AddOp foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.Plus)
    {
       Latte.Absyn.Plus _plus = (Latte.Absyn.Plus) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("+");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.Minus)
    {
       Latte.Absyn.Minus _minus = (Latte.Absyn.Minus) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("-");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(Latte.Absyn.MulOp foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.Times)
    {
       Latte.Absyn.Times _times = (Latte.Absyn.Times) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("*");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.Div)
    {
       Latte.Absyn.Div _div = (Latte.Absyn.Div) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("/");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.Mod)
    {
       Latte.Absyn.Mod _mod = (Latte.Absyn.Mod) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("%");
       if (_i_ > 0) render(_R_PAREN);
    }
  }

  private static void pp(Latte.Absyn.RelOp foo, int _i_)
  {
    if (foo instanceof Latte.Absyn.LTH)
    {
       Latte.Absyn.LTH _lth = (Latte.Absyn.LTH) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("<");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.LE)
    {
       Latte.Absyn.LE _le = (Latte.Absyn.LE) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("<=");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.GTH)
    {
       Latte.Absyn.GTH _gth = (Latte.Absyn.GTH) foo;
       if (_i_ > 0) render(_L_PAREN);
       render(">");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.GE)
    {
       Latte.Absyn.GE _ge = (Latte.Absyn.GE) foo;
       if (_i_ > 0) render(_L_PAREN);
       render(">=");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.EQU)
    {
       Latte.Absyn.EQU _equ = (Latte.Absyn.EQU) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("==");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof Latte.Absyn.NE)
    {
       Latte.Absyn.NE _ne = (Latte.Absyn.NE) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("!=");
       if (_i_ > 0) render(_R_PAREN);
    }
  }


  private static void sh(Latte.Absyn.Program foo)
  {
    if (foo instanceof Latte.Absyn.Program)
    {
       Latte.Absyn.Program _program = (Latte.Absyn.Program) foo;
       render("(");
       render("Program");
       render("[");
       sh(_program.listtopdef_);
       render("]");
       render(")");
    }
  }

  private static void sh(Latte.Absyn.TopDef foo)
  {
    if (foo instanceof Latte.Absyn.FnDef)
    {
       Latte.Absyn.FnDef _fndef = (Latte.Absyn.FnDef) foo;
       render("(");
       render("FnDef");
       sh(_fndef.type_);
       sh(_fndef.ident_);
       render("[");
       sh(_fndef.listarg_);
       render("]");
       sh(_fndef.block_);
       render(")");
    }
  }

  private static void sh(Latte.Absyn.ListTopDef foo)
  {
     for (java.util.Iterator<TopDef> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(Latte.Absyn.Arg foo)
  {
    if (foo instanceof Latte.Absyn.Arg)
    {
       Latte.Absyn.Arg _arg = (Latte.Absyn.Arg) foo;
       render("(");
       render("Arg");
       sh(_arg.type_);
       sh(_arg.ident_);
       render(")");
    }
  }

  private static void sh(Latte.Absyn.ListArg foo)
  {
     for (java.util.Iterator<Arg> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(Latte.Absyn.Block foo)
  {
    if (foo instanceof Latte.Absyn.Block)
    {
       Latte.Absyn.Block _block = (Latte.Absyn.Block) foo;
       render("(");
       render("Block");
       render("[");
       sh(_block.liststmt_);
       render("]");
       render(")");
    }
  }

  private static void sh(Latte.Absyn.ListStmt foo)
  {
     for (java.util.Iterator<Stmt> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(Latte.Absyn.Stmt foo)
  {
    if (foo instanceof Latte.Absyn.SEmpty)
    {
       Latte.Absyn.SEmpty _sempty = (Latte.Absyn.SEmpty) foo;
       render("SEmpty");
    }
    if (foo instanceof Latte.Absyn.SBStmt)
    {
       Latte.Absyn.SBStmt _sbstmt = (Latte.Absyn.SBStmt) foo;
       render("(");
       render("SBStmt");
       sh(_sbstmt.block_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.SDecl)
    {
       Latte.Absyn.SDecl _sdecl = (Latte.Absyn.SDecl) foo;
       render("(");
       render("SDecl");
       sh(_sdecl.type_);
       render("[");
       sh(_sdecl.listitem_);
       render("]");
       render(")");
    }
    if (foo instanceof Latte.Absyn.SAss)
    {
       Latte.Absyn.SAss _sass = (Latte.Absyn.SAss) foo;
       render("(");
       render("SAss");
       sh(_sass.ident_);
       sh(_sass.expr_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.SIncr)
    {
       Latte.Absyn.SIncr _sincr = (Latte.Absyn.SIncr) foo;
       render("(");
       render("SIncr");
       sh(_sincr.ident_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.SDecr)
    {
       Latte.Absyn.SDecr _sdecr = (Latte.Absyn.SDecr) foo;
       render("(");
       render("SDecr");
       sh(_sdecr.ident_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.SRet)
    {
       Latte.Absyn.SRet _sret = (Latte.Absyn.SRet) foo;
       render("(");
       render("SRet");
       sh(_sret.expr_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.SVRet)
    {
       Latte.Absyn.SVRet _svret = (Latte.Absyn.SVRet) foo;
       render("SVRet");
    }
    if (foo instanceof Latte.Absyn.SCond)
    {
       Latte.Absyn.SCond _scond = (Latte.Absyn.SCond) foo;
       render("(");
       render("SCond");
       sh(_scond.expr_);
       sh(_scond.stmt_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.SCondElse)
    {
       Latte.Absyn.SCondElse _scondelse = (Latte.Absyn.SCondElse) foo;
       render("(");
       render("SCondElse");
       sh(_scondelse.expr_);
       sh(_scondelse.stmt_1);
       sh(_scondelse.stmt_2);
       render(")");
    }
    if (foo instanceof Latte.Absyn.SWhile)
    {
       Latte.Absyn.SWhile _swhile = (Latte.Absyn.SWhile) foo;
       render("(");
       render("SWhile");
       sh(_swhile.expr_);
       sh(_swhile.stmt_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.SExp)
    {
       Latte.Absyn.SExp _sexp = (Latte.Absyn.SExp) foo;
       render("(");
       render("SExp");
       sh(_sexp.expr_);
       render(")");
    }
  }

  private static void sh(Latte.Absyn.Item foo)
  {
    if (foo instanceof Latte.Absyn.SNoInit)
    {
       Latte.Absyn.SNoInit _snoinit = (Latte.Absyn.SNoInit) foo;
       render("(");
       render("SNoInit");
       sh(_snoinit.ident_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.SInit)
    {
       Latte.Absyn.SInit _sinit = (Latte.Absyn.SInit) foo;
       render("(");
       render("SInit");
       sh(_sinit.ident_);
       sh(_sinit.expr_);
       render(")");
    }
  }

  private static void sh(Latte.Absyn.ListItem foo)
  {
     for (java.util.Iterator<Item> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(Latte.Absyn.Type foo)
  {
    if (foo instanceof Latte.Absyn.TInt)
    {
       Latte.Absyn.TInt _tint = (Latte.Absyn.TInt) foo;
       render("TInt");
    }
    if (foo instanceof Latte.Absyn.TStr)
    {
       Latte.Absyn.TStr _tstr = (Latte.Absyn.TStr) foo;
       render("TStr");
    }
    if (foo instanceof Latte.Absyn.TBool)
    {
       Latte.Absyn.TBool _tbool = (Latte.Absyn.TBool) foo;
       render("TBool");
    }
    if (foo instanceof Latte.Absyn.TVoid)
    {
       Latte.Absyn.TVoid _tvoid = (Latte.Absyn.TVoid) foo;
       render("TVoid");
    }
    if (foo instanceof Latte.Absyn.TFun)
    {
       Latte.Absyn.TFun _tfun = (Latte.Absyn.TFun) foo;
       render("(");
       render("TFun");
       sh(_tfun.type_);
       render("[");
       sh(_tfun.listtype_);
       render("]");
       render(")");
    }
  }

  private static void sh(Latte.Absyn.ListType foo)
  {
     for (java.util.Iterator<Type> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(Latte.Absyn.Expr foo)
  {
    if (foo instanceof Latte.Absyn.EVar)
    {
       Latte.Absyn.EVar _evar = (Latte.Absyn.EVar) foo;
       render("(");
       render("EVar");
       sh(_evar.ident_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.ELitInt)
    {
       Latte.Absyn.ELitInt _elitint = (Latte.Absyn.ELitInt) foo;
       render("(");
       render("ELitInt");
       sh(_elitint.integer_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.ELitTrue)
    {
       Latte.Absyn.ELitTrue _elittrue = (Latte.Absyn.ELitTrue) foo;
       render("ELitTrue");
    }
    if (foo instanceof Latte.Absyn.ELitFalse)
    {
       Latte.Absyn.ELitFalse _elitfalse = (Latte.Absyn.ELitFalse) foo;
       render("ELitFalse");
    }
    if (foo instanceof Latte.Absyn.EApp)
    {
       Latte.Absyn.EApp _eapp = (Latte.Absyn.EApp) foo;
       render("(");
       render("EApp");
       sh(_eapp.ident_);
       render("[");
       sh(_eapp.listexpr_);
       render("]");
       render(")");
    }
    if (foo instanceof Latte.Absyn.EString)
    {
       Latte.Absyn.EString _estring = (Latte.Absyn.EString) foo;
       render("(");
       render("EString");
       sh(_estring.string_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.Neg)
    {
       Latte.Absyn.Neg _neg = (Latte.Absyn.Neg) foo;
       render("(");
       render("Neg");
       sh(_neg.expr_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.Not)
    {
       Latte.Absyn.Not _not = (Latte.Absyn.Not) foo;
       render("(");
       render("Not");
       sh(_not.expr_);
       render(")");
    }
    if (foo instanceof Latte.Absyn.EMul)
    {
       Latte.Absyn.EMul _emul = (Latte.Absyn.EMul) foo;
       render("(");
       render("EMul");
       sh(_emul.expr_1);
       sh(_emul.mulop_);
       sh(_emul.expr_2);
       render(")");
    }
    if (foo instanceof Latte.Absyn.EAdd)
    {
       Latte.Absyn.EAdd _eadd = (Latte.Absyn.EAdd) foo;
       render("(");
       render("EAdd");
       sh(_eadd.expr_1);
       sh(_eadd.addop_);
       sh(_eadd.expr_2);
       render(")");
    }
    if (foo instanceof Latte.Absyn.ERel)
    {
       Latte.Absyn.ERel _erel = (Latte.Absyn.ERel) foo;
       render("(");
       render("ERel");
       sh(_erel.expr_1);
       sh(_erel.relop_);
       sh(_erel.expr_2);
       render(")");
    }
    if (foo instanceof Latte.Absyn.EAnd)
    {
       Latte.Absyn.EAnd _eand = (Latte.Absyn.EAnd) foo;
       render("(");
       render("EAnd");
       sh(_eand.expr_1);
       sh(_eand.expr_2);
       render(")");
    }
    if (foo instanceof Latte.Absyn.EOr)
    {
       Latte.Absyn.EOr _eor = (Latte.Absyn.EOr) foo;
       render("(");
       render("EOr");
       sh(_eor.expr_1);
       sh(_eor.expr_2);
       render(")");
    }
  }

  private static void sh(Latte.Absyn.ListExpr foo)
  {
     for (java.util.Iterator<Expr> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(Latte.Absyn.AddOp foo)
  {
    if (foo instanceof Latte.Absyn.Plus)
    {
       Latte.Absyn.Plus _plus = (Latte.Absyn.Plus) foo;
       render("Plus");
    }
    if (foo instanceof Latte.Absyn.Minus)
    {
       Latte.Absyn.Minus _minus = (Latte.Absyn.Minus) foo;
       render("Minus");
    }
  }

  private static void sh(Latte.Absyn.MulOp foo)
  {
    if (foo instanceof Latte.Absyn.Times)
    {
       Latte.Absyn.Times _times = (Latte.Absyn.Times) foo;
       render("Times");
    }
    if (foo instanceof Latte.Absyn.Div)
    {
       Latte.Absyn.Div _div = (Latte.Absyn.Div) foo;
       render("Div");
    }
    if (foo instanceof Latte.Absyn.Mod)
    {
       Latte.Absyn.Mod _mod = (Latte.Absyn.Mod) foo;
       render("Mod");
    }
  }

  private static void sh(Latte.Absyn.RelOp foo)
  {
    if (foo instanceof Latte.Absyn.LTH)
    {
       Latte.Absyn.LTH _lth = (Latte.Absyn.LTH) foo;
       render("LTH");
    }
    if (foo instanceof Latte.Absyn.LE)
    {
       Latte.Absyn.LE _le = (Latte.Absyn.LE) foo;
       render("LE");
    }
    if (foo instanceof Latte.Absyn.GTH)
    {
       Latte.Absyn.GTH _gth = (Latte.Absyn.GTH) foo;
       render("GTH");
    }
    if (foo instanceof Latte.Absyn.GE)
    {
       Latte.Absyn.GE _ge = (Latte.Absyn.GE) foo;
       render("GE");
    }
    if (foo instanceof Latte.Absyn.EQU)
    {
       Latte.Absyn.EQU _equ = (Latte.Absyn.EQU) foo;
       render("EQU");
    }
    if (foo instanceof Latte.Absyn.NE)
    {
       Latte.Absyn.NE _ne = (Latte.Absyn.NE) foo;
       render("NE");
    }
  }


  private static void pp(Integer n, int _i_) { buf_.append(n); buf_.append(" "); }
  private static void pp(Double d, int _i_) { buf_.append(d); buf_.append(" "); }
  private static void pp(String s, int _i_) { buf_.append(s); buf_.append(" "); }
  private static void pp(Character c, int _i_) { buf_.append("'" + c.toString() + "'"); buf_.append(" "); }
  private static void sh(Integer n) { render(n.toString()); }
  private static void sh(Double d) { render(d.toString()); }
  private static void sh(Character c) { render(c.toString()); }
  private static void sh(String s) { printQuoted(s); }
  private static void printQuoted(String s) { render("\"" + s + "\""); }
  private static void indent()
  {
    int n = _n_;
    while (n > 0)
    {
      buf_.append(" ");
      n--;
    }
  }
  private static void backup()
  {
     if (buf_.charAt(buf_.length() - 1) == ' ') {
      buf_.setLength(buf_.length() - 1);
    }
  }
  private static void trim()
  {
     while (buf_.length() > 0 && buf_.charAt(0) == ' ')
        buf_.deleteCharAt(0); 
    while (buf_.length() > 0 && buf_.charAt(buf_.length()-1) == ' ')
        buf_.deleteCharAt(buf_.length()-1);
  }
  private static int _n_ = 0;
  private static StringBuilder buf_ = new StringBuilder(INITIAL_BUFFER_SIZE);
}

