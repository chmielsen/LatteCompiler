package FunctionRecognizer.Unittests;

import FunctionRecognizer.TopDefFR;
import Latte.Absyn.*;
import Utils.FunctionSignature;
import Utils.SemanticAnalysis;
import Utils.State;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/11/13
 * Time: 6:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class TopDefFRTest {

    private static final String IDENTIFIER = "IDENT";

    @Test
    public void testVisit() throws Exception {
        ListArg listArg = new ListArg();
        listArg.add(new Arg(new TStr(), null));
        listArg.add(new Arg(new TInt(), null));
        FnDef fnDef = new FnDef(new TInt(), IDENTIFIER, listArg, null);
        TopDefFR topDefTypeRecognizer = new TopDefFR();

        State state = new State();
        SemanticAnalysis analysis = topDefTypeRecognizer.visit(fnDef, state);

        ListType expectedTypeList = new ListType();
        expectedTypeList.add(new TStr());
        expectedTypeList.add(new TInt());
        FunctionSignature expectedSignature = new FunctionSignature(new TInt(), expectedTypeList);

        assertEquals(expectedSignature, state.getDeclaredFunctions().get(IDENTIFIER));

    }
}
