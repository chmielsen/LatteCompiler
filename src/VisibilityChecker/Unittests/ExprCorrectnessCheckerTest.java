package VisibilityChecker.Unittests;

import FunctionRecognizer.FunctionSignature;
import Latte.Absyn.*;
import VisibilityChecker.Errors.IdentifierNotVisible;
import VisibilityChecker.Errors.TypeError;
import VisibilityChecker.Errors.WrongNumberOfArguments;
import VisibilityChecker.ExprCorrectnessChecker;
import VisibilityChecker.SemanticAnalysis;
import VisibilityChecker.State;
import VisibilityChecker.VariableDefinition;
import org.junit.Test;

import static VisibilityChecker.ExprCorrectnessChecker.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/14/13
 * Time: 9:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExprCorrectnessCheckerTest {

    @Test
    public void testVisitGoodVar() {
        EVar var = new EVar("id");
        State state = new State(null, new HashMap<String, List<VariableDefinition>>(),
                new HashMap<String, FunctionSignature>());
        List<VariableDefinition> varDefs = new ArrayList<VariableDefinition>();
        varDefs.add(new VariableDefinition(null, new TInt()));
        state.getDeclaredIds().put("id", varDefs);
        SemanticAnalysis<Type> analysis = var.accept(new ExprCorrectnessChecker(), state);

        assertFalse(analysis.hasErrors());
    }

    @Test
    public void testVisitVarNotDefined() {
        EVar var = new EVar("id");
        State state = new State(null, new HashMap<String, List<VariableDefinition>>(),
                new HashMap<String, FunctionSignature>());

        SemanticAnalysis<Type> analysis = var.accept(new ExprCorrectnessChecker(), state);

        assertTrue(analysis.getErrors().size() == 1);
        assertTrue(analysis.getErrors().get(0) instanceof IdentifierNotVisible);
    }

    @Test
    public void testFunctionAppBadParameterCount() {
        EApp application = new EApp("fid", new ListExpr());
        Map<String, FunctionSignature> functions = new HashMap<String, FunctionSignature>();
        ListType fidTypes = new ListType();
        fidTypes.add(new TInt());
        functions.put("fid", new FunctionSignature(new TInt(),fidTypes));
        State state = new State(null, new HashMap<String, List<VariableDefinition>>(), functions);

        SemanticAnalysis<Type> analysis = application.accept(new ExprCorrectnessChecker(), state);

        assertTrue(analysis.hasErrors());
        assertEquals(1, analysis.getErrors().size());
        assertEquals(new WrongNumberOfArguments("fid"), analysis.getErrors().get(0));
    }

    @Test
    public void testFunctionGoodApp() {
        ListExpr arguments = new ListExpr();
        arguments.add(new ELitInt(0));
        arguments.add(new EString("aha"));
        EApp application = new EApp("fid", arguments);
        Map<String, FunctionSignature> functions = new HashMap<String, FunctionSignature>();
        ListType fidTypes = new ListType();
        fidTypes.add(new TInt());
        fidTypes.add(new TStr());
        functions.put("fid", new FunctionSignature(new TInt(),fidTypes));
        State state = new State(null, new HashMap<String, List<VariableDefinition>>(), functions);

        SemanticAnalysis<Type> analysis = application.accept(new ExprCorrectnessChecker(), state);

        assertFalse(analysis.hasErrors());
        assertEquals(new TInt(), analysis.getT());
    }

    @Test
    public void testFunctionBadArgumentTypesApp() {
        ListExpr arguments = new ListExpr();
        arguments.add(new ELitInt(0));
        arguments.add(new EString("aha"));
        EApp application = new EApp("fid", arguments);
        Map<String, FunctionSignature> functions = new HashMap<String, FunctionSignature>();
        ListType fidTypes = new ListType();
        fidTypes.add(new TStr());
        fidTypes.add(new TInt());
        functions.put("fid", new FunctionSignature(new TInt(),fidTypes));
        State state = new State(null, new HashMap<String, List<VariableDefinition>>(), functions);

        SemanticAnalysis<Type> analysis = application.accept(new ExprCorrectnessChecker(), state);

        assertTrue(analysis.hasErrors());
        assertEquals(2, analysis.getErrors().size());
        assertEquals(new TypeError(new TStr(), new TInt()), analysis.getErrors().get(0));
        assertEquals(new TypeError(new TInt(), new TStr()), analysis.getErrors().get(1));
    }

    @Test
    public void testGoodNot() {
        Not not = new Not(new ELitTrue());
        SemanticAnalysis<Type> analysis = not.accept(new ExprCorrectnessChecker(), null);
        assertFalse(analysis.hasErrors());
        assertEquals(BOOL, analysis.getT());
    }

    @Test
    public void testNotBad() { // haha...
        Not not = new Not(new EString("slaby zart"));
        SemanticAnalysis<Type> analysis = not.accept(new ExprCorrectnessChecker(), null);
        assertTrue(analysis.hasErrors());
        assertEquals(1, analysis.getErrors().size());
        assertEquals(new TypeError(BOOL, STRING), analysis.getErrors().get(0));
    }

    @Test
    public void testNegBad() {
        Neg neg = new Neg(new EString("slaby zart"));
        SemanticAnalysis<Type> analysis = neg.accept(new ExprCorrectnessChecker(), null);
        assertTrue(analysis.hasErrors());
        assertEquals(1, analysis.getErrors().size());
        assertEquals(new TypeError(INT, STRING), analysis.getErrors().get(0));
    }

    @Test
    public void testGoodNeg() {
        Neg neg = new Neg(new ELitInt(56));
        SemanticAnalysis<Type> analysis = neg.accept(new ExprCorrectnessChecker(), null);
        assertFalse(analysis.hasErrors());
        assertEquals(INT, analysis.getT());
    }

    @Test
    public void testGoodMul() {
        EMul mul = new EMul(new ELitInt(5), new Times(), new ELitInt(10));
        SemanticAnalysis<Type> analysis = mul.accept(new ExprCorrectnessChecker(), null);
        assertFalse(analysis.hasErrors());
        assertEquals(INT, analysis.getT());
    }

    @Test
    public void testBadMul() {
        EMul mul = new EMul(new ELitInt(5), new Div(), new EString("siemanko"));
        SemanticAnalysis<Type> analysis = mul.accept(new ExprCorrectnessChecker(), null);
        assertTrue(analysis.hasErrors());
        assertEquals(1, analysis.getErrors().size());
        assertEquals(new TypeError(INT, STRING), analysis.getErrors().get(0));
    }




}
