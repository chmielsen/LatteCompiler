package VisibilityChecker;

import Latte.Absyn.*;
import Utils.FunctionSignature;
import Utils.SemanticAnalysis;
import Utils.State;
import VisibilityChecker.Errors.VisibilityError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/11/13
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgramVC implements Program.Visitor<SemanticAnalysis, State> {

    @Override
    public SemanticAnalysis visit(Program p, State state) {
        state.getDeclaredFunctions().putAll(predefinedInstructions());
        try {
            SemanticAnalysis analysis = new SemanticAnalysis();
            for (TopDef topDef : p.listtopdef_) {
                State localFunctionState = (State)state.clone();
                SemanticAnalysis fnAnalysis = topDef.accept(new TopDefVC(), localFunctionState);
                analysis.getErrors().addAll(fnAnalysis.getErrors());
            }
            return analysis;
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedClassVersionError("State version is not cloneable");
        }
    }

    public static Map<String, FunctionSignature> predefinedInstructions() {
        Map<String, FunctionSignature> predefined = new HashMap<String, FunctionSignature>();
        FunctionSignature readIntFS = new FunctionSignature(ExprCorrectnessChecker.INT, new ListType());

        predefined.put("readInt", readIntFS);

        FunctionSignature readStringFS = new FunctionSignature(ExprCorrectnessChecker.STRING, new ListType());

        predefined.put("readString", readStringFS);

        ListType printIntLT = new ListType();
        printIntLT.add(new TInt());
        FunctionSignature printIntFS = new FunctionSignature(ExprCorrectnessChecker.VOID, printIntLT);
        predefined.put("printInt", printIntFS);

        ListType printStringLT = new ListType();
        printStringLT.add(ExprCorrectnessChecker.STRING);
        FunctionSignature printStringFS = new FunctionSignature(ExprCorrectnessChecker.VOID, printStringLT);
        predefined.put("printString", printStringFS);

        return predefined;
    }

}
