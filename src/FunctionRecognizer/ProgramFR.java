package FunctionRecognizer;

import Latte.Absyn.Program;
import Latte.Absyn.TopDef;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/11/13
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 * TODO: Dodaj sprawdzanie returnow w funkcjach, zmien nazwe pakietu
 */
public class ProgramFR implements Program.Visitor<Map<String, FunctionSignature>, Object> {

    @Override
    public Map<String, FunctionSignature> visit(Program p, Object arg) {
        Map<String, FunctionSignature> typeMap = new HashMap<String, FunctionSignature>();
        for (TopDef topDef : p.listtopdef_) {
            Map.Entry<String, FunctionSignature> idToSignature = topDef.accept(new TopDefFR(), null);
            typeMap.put(idToSignature.getKey(), idToSignature.getValue());
        }
        return typeMap;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
