package Utils;

import Latte.Absyn.Block;
import Latte.Absyn.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/13/13
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class State implements Cloneable {

    private Block currentBlock;
    private Map<String, List<VariableDefinition>> declaredIds;
    private Map<String, FunctionSignature> declaredFunctions;

    public State(Block currentBlock, Map<String, List<VariableDefinition>> declaredIds,
                 Map<String, FunctionSignature> declaredFunctions) {
        this.currentBlock = currentBlock;
        this.declaredIds = declaredIds;
        this.declaredFunctions = declaredFunctions;
    }

    public State() {
        declaredIds = new HashMap<String, List<VariableDefinition>>();
        declaredFunctions = new HashMap<String, FunctionSignature>();
    }

    public boolean hasId(String id) {
        return declaredIds.containsKey(id) || declaredFunctions.containsKey(id);
    }

    public boolean isFunction(String id) {
        return declaredFunctions.containsKey(id);
    }

    public Type getIdentifierType(String id) {
        if (declaredIds.containsKey(id)) {
            List<VariableDefinition> varDefs = declaredIds.get(id);
            assert (varDefs.size() > 0);
            return varDefs.get(varDefs.size() - 1).getType();
        } else if (declaredFunctions.containsKey(id)) {
            return declaredFunctions.get(id).toParserType();
        } else {
            return null;
        }
    }

    public void addVarDefinition(String id, Type t) {
        // Dopisz sprawdzanie czy jest definicja
        VariableDefinition varDef = new VariableDefinition(getCurrentBlock(), t);
        if (declaredIds.containsKey(id)) {
            declaredIds.get(id).add(varDef);
        } else {
            // creating 1st definition
            List<VariableDefinition> listWithFirstDef = new ArrayList<VariableDefinition>();
            listWithFirstDef.add(varDef);
            declaredIds.put(id, listWithFirstDef);
        }
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    public Map<String, List<VariableDefinition>> getDeclaredIds() {
        return declaredIds;
    }

    public void setDeclaredIds(Map<String, List<VariableDefinition>> declaredIds) {
        this.declaredIds = declaredIds;
    }

    public Map<String, FunctionSignature> getDeclaredFunctions() {
        return declaredFunctions;
    }

    public void setDeclaredFunctions(Map<String, FunctionSignature> declaredFunctions) {
        this.declaredFunctions = declaredFunctions;
    }

    @Override
    public String toString() {
        return "State{" +
                "currentBlock=" + currentBlock +
                ", declaredIds=" + declaredIds +
                ", declaredFunctions=" + declaredFunctions +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Map<String, FunctionSignature> functionsCopy = new HashMap<String, FunctionSignature>();
        functionsCopy.putAll(declaredFunctions);
        Map<String, List<VariableDefinition>> variablesCopy = new HashMap<String, List<VariableDefinition>>();
        variablesCopy.putAll(declaredIds);
        return new State(currentBlock, variablesCopy, functionsCopy);
    }
}
