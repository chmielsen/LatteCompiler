package FunctionRecognizer;

import Latte.Absyn.Arg;
import Latte.Absyn.FnDef;
import Latte.Absyn.ListType;
import Latte.Absyn.TopDef;

import java.util.AbstractMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/11/13
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class TopDefFR implements TopDef.Visitor<Map.Entry<String, FunctionSignature>, Object> {

    @Override
    public Map.Entry<String, FunctionSignature> visit(FnDef p, Object arg) {
        ListType argumentListType = new ListType();
        for (Arg argument : p.listarg_) {
            argumentListType.add(argument.type_);
        }
        FunctionSignature signature = new FunctionSignature(p.type_, argumentListType);
        return new AbstractMap.SimpleEntry<String, FunctionSignature>(p.ident_, signature);
    }
}
