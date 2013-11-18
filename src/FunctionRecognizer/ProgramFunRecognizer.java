package FunctionRecognizer;

import Latte.Absyn.Program;
import Latte.Absyn.TopDef;
import Utils.SemanticAnalysis;
import Utils.State;

/**
 * Class implementing Visitor pattern in order to recognize functiond defined
 * in the {@link Program}
 */
public class ProgramFunRecognizer implements Program.Visitor<SemanticAnalysis, State> {

    @Override
    public SemanticAnalysis visit(Program p, State state) {
        SemanticAnalysis analysis = new SemanticAnalysis();
        for (TopDef topDef : p.listtopdef_) {
            SemanticAnalysis fnAnalysis = topDef.accept(new TopDefFunRecognizer(), state);
            if (fnAnalysis.hasErrors()) {
                analysis = analysis.merge(fnAnalysis);
            }
        }
        return analysis;
    }
}
