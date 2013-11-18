package Checkers;

import Latte.Absyn.ListType;
import Latte.Absyn.Program;
import Latte.Absyn.TInt;
import Latte.Absyn.TopDef;
import Utils.FunctionSignature;
import Utils.SemanticAnalysis;
import Utils.State;
import Utils.TypeConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Class implementing Visitor pattern in order to check {@link Program}
 * for semantic correctness. Each method returns empty {@link SemanticAnalysis}
 * on successful analyze, and one with list of {@link Checkers.Errors.SemanticError}
 * otherwise/
 */
public class ProgramCorrectnessChecker implements Program.Visitor<SemanticAnalysis, State> {

    @Override
    public SemanticAnalysis visit(Program p, State state) {
        state.getDeclaredFunctions().putAll(predefinedFunctions());
        try {
            SemanticAnalysis analysis = new SemanticAnalysis();
            for (TopDef topDef : p.listtopdef_) {
                State localFunctionState = (State)state.clone();
                SemanticAnalysis fnAnalysis = topDef.accept(new TopDefCorrectnessChecker(), localFunctionState);
                analysis.getErrors().addAll(fnAnalysis.getErrors());
            }
            return analysis;
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedClassVersionError("State version is not cloneable");
        }
    }

    /**
     * Creates signatures of predefined functions
     * @return Map with FunctionId -> {@link FunctionSignature}
     */
    public static Map<String, FunctionSignature> predefinedFunctions() {
        Map<String, FunctionSignature> predefined = new HashMap<String, FunctionSignature>();
        FunctionSignature readIntFS = new FunctionSignature(TypeConstants.INT, new ListType());

        predefined.put("readInt", readIntFS);

        FunctionSignature readStringFS = new FunctionSignature(TypeConstants.STRING, new ListType());

        predefined.put("readString", readStringFS);

        ListType printIntLT = new ListType();
        printIntLT.add(new TInt());
        FunctionSignature printIntFS = new FunctionSignature(TypeConstants.VOID, printIntLT);
        predefined.put("printInt", printIntFS);

        ListType printStringLT = new ListType();
        printStringLT.add(TypeConstants.STRING);
        FunctionSignature printStringFS = new FunctionSignature(TypeConstants.VOID, printStringLT);
        predefined.put("printString", printStringFS);

        return predefined;
    }

}
