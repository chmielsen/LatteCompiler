package FunctionRecognizer;

import Latte.Absyn.Arg;
import Latte.Absyn.FnDef;
import Latte.Absyn.ListType;
import Latte.Absyn.TopDef;
import Utils.FunctionSignature;
import Utils.SemanticAnalysis;
import Utils.State;
import Checkers.Errors.DuplicatedIdentifier;

import static Utils.SemanticAnalysis.createSemanticAnalysis;

/**
 * Class implementing Visitor pattern
 */
public class TopDefFunRecognizer implements TopDef.Visitor<SemanticAnalysis, State> {

    @Override
    public SemanticAnalysis visit(FnDef p, State state) {
        if (state.hasId(p.ident_)) {
            return createSemanticAnalysis(new DuplicatedIdentifier(p.ident_));
        }
        ListType argumentListType = new ListType();
        for (Arg argument : p.listarg_) {
            argumentListType.add(argument.type_);
        }
        FunctionSignature signature = new FunctionSignature(p.type_, argumentListType);
        state.getDeclaredFunctions().put(p.ident_, signature);
        return new SemanticAnalysis();
    }
}
