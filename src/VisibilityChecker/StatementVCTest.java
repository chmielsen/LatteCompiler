package VisibilityChecker;

import Latte.Absyn.*;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/12/13
 * Time: 12:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class StatementVCTest {

    @Test
    public void testGoodDecl() throws Exception {
        ListItem declarationItems = new ListItem();
        declarationItems.add(new SNoInit("id"));
        declarationItems.add(new SNoInit("id2"));
        SDecl declaration = new SDecl(null,declarationItems);


        Stmt.Visitor<Set<VisibilityError>, Set<String>>  visitor = new StatementVC();
        Set<VisibilityError> errors = visitor.visit(declaration, new HashSet<String>());

        Assert.assertEquals(0, errors.size());
    }


    @Test
    public void testDeclDuplicated() throws Exception {
        ListItem declarationItems = new ListItem();
        declarationItems.add(new SNoInit("id"));
        declarationItems.add(new SNoInit("id"));
        SDecl declaration = new SDecl(null,declarationItems);


        Stmt.Visitor<Set<VisibilityError>, Set<String>>  visitor = new StatementVC();
        Set<VisibilityError> errors = visitor.visit(declaration, new HashSet<String>());

        Set<VisibilityError> expectedErrors = new HashSet<VisibilityError>();
        expectedErrors.add(new DuplicatedIdentifier("id"));
        Assert.assertEquals(expectedErrors, errors);
    }

    @Test
    public void testDeclMissingId() throws Exception {
        ListItem declarationItems = new ListItem();
        Expr expression = new EAdd(new EVar("missingId"), new Plus(), new ELitInt(0));
        declarationItems.add(new SInit("id", expression));
        SDecl declaration = new SDecl(null,declarationItems);

        Stmt.Visitor<Set<VisibilityError>, Set<String>>  visitor = new StatementVC();
        Set<VisibilityError> errors = visitor.visit(declaration, new HashSet<String>());

        Set<VisibilityError> expectedErrors = new HashSet<VisibilityError>();
        expectedErrors.add(new IdentifierNotVisible("missingId"));
        Assert.assertEquals(expectedErrors, errors);
    }

    @Test
    public void testEmptyStmt() {
        SEmpty empty = new SEmpty();
        assertEquals(null, empty.accept(new StatementVC(), null));
    }

}
