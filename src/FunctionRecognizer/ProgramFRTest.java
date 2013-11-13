package FunctionRecognizer;

import Latte.Absyn.*;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/11/13
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgramFRTest {

    private static final String IDENTIFIER = "IDENT";

    @Test
    public void testVisit() throws Exception {

        ListArg listArg = new ListArg();
        FnDef fnDef = new FnDef(new TInt(), IDENTIFIER, listArg, null);
        ListTopDef listTopDef = new ListTopDef();
        listTopDef.add(fnDef);
        Program p = new Program(listTopDef);

        Map<String, FunctionSignature> typeMap = p.accept(new ProgramFR(), null);
        System.out.println(typeMap.toString());

        Map<String, FunctionSignature> expectedTypeMap = new HashMap<String, FunctionSignature>();
        ListType argumentTypes = new ListType();
        expectedTypeMap.put(IDENTIFIER, new FunctionSignature(new TInt(), argumentTypes));

        Assert.assertEquals(expectedTypeMap, typeMap);
    }
}
