package VisibilityChecker;

import Latte.Absyn.EVar;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/13/13
 * Time: 11:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExpressionVCTest {

    final String EXAMPLE_ID = "ID";
    final Integer EXAMPLE_INT = 1;
    final String EXAMPLE_STRING = "string_expression";

    /*
    final Expr EXAMPLE_VAR_EXPR = new EVar(EXAMPLE_ID);
    final Expr EXAMPLE_NUM_EXPR = new ELitInt(EXAMPLE_INT);
    final Expr EXAMPLE_STRING_EXPR = new EString(EXAMPLE_STRING);
    */

    @Test
    public void testMissingVar() throws Exception {
        EVar eVar = new EVar(EXAMPLE_ID);

        Set<VisibilityError> expectedErrors = new HashSet<VisibilityError>();
        expectedErrors.add(new IdentifierNotVisible(EXAMPLE_ID));
        assertEquals(expectedErrors, eVar.accept(new ExpressionVC(), new HashSet<String>()));
    }

    @Test
    public void testGoodVar() throws Exception {
        EVar eVar = new EVar(EXAMPLE_ID);
        Set<String> visibleIds = new HashSet<String>();
        visibleIds.add(EXAMPLE_ID);
        assertEquals(null, eVar.accept(new ExpressionVC(), visibleIds));
    }
}
