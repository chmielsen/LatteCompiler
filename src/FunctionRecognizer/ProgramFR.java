package FunctionRecognizer;

import Latte.Absyn.Program;
import Latte.Absyn.TopDef;
import Utils.FunctionSignature;
import Utils.SemanticAnalysis;
import Utils.State;

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
public class ProgramFR implements Program.Visitor<SemanticAnalysis, State> {

    @Override
    public SemanticAnalysis visit(Program p, State state) {
        SemanticAnalysis analysis = new SemanticAnalysis();
        for (TopDef topDef : p.listtopdef_) {
            SemanticAnalysis fnAnalysis = topDef.accept(new TopDefFR(), state);
            if (fnAnalysis.hasErrors()) {
                analysis = analysis.merge(fnAnalysis);
            }
        }
        return analysis;
    }
}
