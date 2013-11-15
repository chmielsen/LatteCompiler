package VisibilityChecker.Unittests;

import FunctionRecognizer.FunctionSignature;
import Latte.Absyn.*;
import VisibilityChecker.Errors.DuplicatedIdentifier;
import VisibilityChecker.Errors.IdentifierNotVisible;
import VisibilityChecker.Errors.VisibilityError;
import VisibilityChecker.State;
import VisibilityChecker.StatementVC;
import VisibilityChecker.VariableDefinition;
import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

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


        Stmt.Visitor<Set<VisibilityError>, State>  visitor = new StatementVC();
        Set<VisibilityError> errors = visitor.visit(declaration, new State());

        Assert.assertEquals(0, errors.size());
    }


    @Test
    public void testDeclDuplicated() throws Exception {
        ListItem declarationItems = new ListItem();
        declarationItems.add(new SNoInit("id"));
        declarationItems.add(new SNoInit("id"));
        SDecl declaration = new SDecl(null,declarationItems);


        Stmt.Visitor<Set<VisibilityError>, State>  visitor = new StatementVC();
        Set<VisibilityError> errors = visitor.visit(declaration, new State());

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

        Stmt.Visitor<Set<VisibilityError>, State>  visitor = new StatementVC();
        Set<VisibilityError> errors = visitor.visit(declaration, new State());

        Set<VisibilityError> expectedErrors = new HashSet<VisibilityError>();
        expectedErrors.add(new IdentifierNotVisible("missingId"));
        Assert.assertEquals(expectedErrors, errors);
    }

    @Test
    public void testEmptyStmt() {
        SEmpty empty = new SEmpty();
        assertEquals(null, empty.accept(new StatementVC(), null));
    }

    @Test
    public void testSimpleBlock() {
        ListStmt statements = new ListStmt();
        ListItem vars = new ListItem();
        vars.add(new SNoInit("var1"));
        statements.add(new SDecl(new TInt(), vars));
        SBStmt blockStmt = new SBStmt(new Block(statements));

        // already visible ids
        Map<String, List<VariableDefinition>> visibleIds = new HashMap<String, List<VariableDefinition>>();
        visibleIds.put("var1", null);

        Set<VisibilityError> errors = blockStmt.accept(new StatementVC(), new State(null, visibleIds,
                new HashMap<String, FunctionSignature>()));

        assertEquals(new HashSet<VisibilityError>(), errors);

    }

}
