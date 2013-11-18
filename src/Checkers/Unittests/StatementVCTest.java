package Checkers.Unittests;

import Checkers.Errors.DuplicatedIdentifier;
import Checkers.Errors.TypeError;
import Checkers.StatementCorrectnessChecker;
import Latte.Absyn.*;
import Utils.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/15/13
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class StatementVCTest {

    @Test
    public void testGoodDecl() throws Exception {
        ListItem vars = new ListItem();
        vars.add(new SNoInit("id1"));
        SDecl decl = new SDecl(TypeConstants.INT, vars);
        State state = new State();
        SemanticAnalysis analysis = decl.accept(new StatementCorrectnessChecker(), state);

        assertFalse(analysis.hasErrors());
    }

    @Test
    public void testGoodMultDecl() throws Exception {
        ListItem vars = new ListItem();
        vars.add(new SNoInit("id1"));
        vars.add(new SInit("id2", new ELitInt(5)));
        SDecl decl = new SDecl(TypeConstants.INT, vars);
        State state = new State();
        SemanticAnalysis analysis = decl.accept(new StatementCorrectnessChecker(), state);

        assertFalse(analysis.hasErrors());
    }

    @Test
    public void testDeclBadType() throws Exception {
        ListItem vars = new ListItem();
        vars.add(new SNoInit("id1"));
        vars.add(new SInit("id2", new EString("aha")));
        SDecl decl = new SDecl(TypeConstants.INT, vars);
        State state = new State();
        SemanticAnalysis analysis = decl.accept(new StatementCorrectnessChecker(), state);

        assertTrue(analysis.hasErrors());
        assertEquals(1, analysis.getErrors().size());
        assertEquals(new TypeError(TypeConstants.INT, TypeConstants.STRING), analysis.getErrors().get(0));
    }

    @Test
    public void testDeclDuplicated() throws Exception {
        ListItem vars = new ListItem();
        vars.add(new SNoInit("id1"));
        SDecl decl = new SDecl(TypeConstants.INT, vars);
        Map<String, FunctionSignature> functions = new HashMap<String, FunctionSignature>();
        functions.put("id1", null);
        State state = new State(null, new HashMap<String, List<VariableDefinition>>(), functions);
        SemanticAnalysis analysis = decl.accept(new StatementCorrectnessChecker(), state);

        assertTrue(analysis.hasErrors());
        assertEquals(1, analysis.getErrors().size());
        assertEquals(new DuplicatedIdentifier("id1"), analysis.getErrors().get(0));
    }

    @Test
    public void testIncrMissingId() {
        SIncr incr = new SIncr("id");
        SemanticAnalysis analysis = incr.accept(new StatementCorrectnessChecker(), new State());
        assertTrue(analysis.hasErrors());
    }

    @Test
    public void testGoodIncr() {
        SIncr incr = new SIncr("id");
        Map<String, List<VariableDefinition>> visibleVarIds = new HashMap<String, List<VariableDefinition>>();
        List<VariableDefinition> varDefs = new ArrayList<VariableDefinition>();
        varDefs.add(new VariableDefinition(null, TypeConstants.INT));
        visibleVarIds.put("id", varDefs);
        State state = new State();
        state.setDeclaredIds(visibleVarIds);
        SemanticAnalysis analysis = incr.accept(new StatementCorrectnessChecker(), state);
        System.out.println(analysis.toString());

        assertFalse(analysis.hasErrors());
    }

    @Test
    public void testGoodCond () {
        SCond conditionalStatement = new SCond(new ELitTrue(), new SVRet());
        SemanticAnalysis analysis = conditionalStatement.accept(new StatementCorrectnessChecker(), new State());

        assertFalse(analysis.hasErrors());
    }


    @Test
    public void testBadConditioninIf () {
        SCond conditionalStatement = new SCond(new ELitInt(5), new SVRet());
        SemanticAnalysis analysis = conditionalStatement.accept(new StatementCorrectnessChecker(), new State());

        assertTrue(analysis.hasErrors());
        assertEquals(1, analysis.getErrors().size());
        assertEquals(new TypeError(TypeConstants.BOOL, TypeConstants.INT), analysis.getErrors().get(0));
    }

}

