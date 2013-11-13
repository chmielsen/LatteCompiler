package FunctionRecognizer;

import Latte.Absyn.*;
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

        Map.Entry<String, FunctionSignature> result = topDefTypeRecognizer.visit(fnDef, null);

        ListType expectedTypeList = new ListType();
        expectedTypeList.add(new TStr());
        expectedTypeList.add(new TInt());
        FunctionSignature expectedSignature = new FunctionSignature(new TInt(), expectedTypeList);
        String expectedIdent = IDENTIFIER;
        Map.Entry<String, FunctionSignature> expectedEntry = new AbstractMap.SimpleEntry<String, FunctionSignature>(expectedIdent, expectedSignature);

        assertEquals(expectedEntry, result);

    }
}
