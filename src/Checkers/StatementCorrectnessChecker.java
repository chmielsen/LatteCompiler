package Checkers;

import Checkers.Errors.*;
import Latte.Absyn.*;
import Utils.SemanticAnalysis;
import Utils.State;
import Utils.VariableDefinition;

import java.util.List;

import static Utils.SemanticAnalysis.createSemanticAnalysis;
import static Utils.TypeConstants.BOOL;
import static Utils.TypeConstants.INT;

/**
 * Class implementing Visitor pattern for the {@link Stmt} in order
 * to check it's semantical correctness. Each method from {@link Stmt.Visitor}
 * returns empty {@link SemanticAnalysis} on no errors, otherwise the one with
 * list of {@link SemanticError}.
 */
public class StatementCorrectnessChecker implements Stmt.Visitor<SemanticAnalysis, State> {

    @Override
    public SemanticAnalysis visit(SBStmt p, State state) {
        try {
            // clone state to have local one in block
            State visibleIdsCopy = (State)state.clone();
            return p.block_.accept(new BlockCorrectnessChecker(), visibleIdsCopy);
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public SemanticAnalysis visit(SEmpty p, State state) {
        return new SemanticAnalysis();
    }

    @Override
    public SemanticAnalysis visit(SDecl p, State state) {
        return declareVariable(p, state);
    }

    @Override
    public SemanticAnalysis visit(SAss p, State state) {
        if (!state.hasId(p.ident_)) {
            return createSemanticAnalysis(new IdentifierNotVisible(p.ident_));
        } else if (state.getDeclaredFunctions().containsKey(p.ident_)) {
            return createSemanticAnalysis(new FunctionNotAVariable(p.ident_));
        }
        // identifier visible and is a variable
        SemanticAnalysis<Type> exprAnalysis = p.expr_.accept(new ExprCorrectnessChecker(), state);
        Type varType = state.getIdentifierType(p.ident_);
        if (exprAnalysis.hasErrors()) {
            return exprAnalysis;
        } else if (!varType.equals(exprAnalysis.getT())) {
            return createSemanticAnalysis(new TypeError(varType, exprAnalysis.getT()));
        }
        return new SemanticAnalysis();
    }

    @Override
    public SemanticAnalysis visit(SIncr p, State state) {
        if (!state.hasId(p.ident_)) {
            return SemanticAnalysis.createSemanticAnalysis(new IdentifierNotVisible(p.ident_));
        } else if (!INT.equals(state.getIdentifierType(p.ident_))) {
            return SemanticAnalysis.createSemanticAnalysis(new TypeError(INT, state.getIdentifierType(p.ident_)));
        } else {
            return new SemanticAnalysis();
        }
    }

    @Override
    public SemanticAnalysis visit(SDecr p, State state) {
        if (!state.hasId(p.ident_)) {
            return SemanticAnalysis.createSemanticAnalysis(new IdentifierNotVisible(p.ident_));
        } else if (!INT.equals(state.getIdentifierType(p.ident_))) {
            return SemanticAnalysis.createSemanticAnalysis(new TypeError(INT, state.getIdentifierType(p.ident_)));
        } else {
            return new SemanticAnalysis();
        }
    }

    @Override
    public SemanticAnalysis visit(SRet p, State state) {
        SemanticAnalysis<Type> exprAnalysis = p.expr_.accept(new ExprCorrectnessChecker(), state);
        SemanticAnalysis analysis = new SemanticAnalysis();
        analysis.setErrors(exprAnalysis.getErrors());
        return analysis;
    }

    @Override
    public SemanticAnalysis visit(SVRet p, State state) {
        return new SemanticAnalysis();
    }

    @Override
    public SemanticAnalysis visit(SCond p, State state) {
        SemanticAnalysis<Type> condAnalysis = p.expr_.accept(new ExprCorrectnessChecker(), state);
        if (condAnalysis.hasErrors()) {
            return condAnalysis;
        } else if (!condAnalysis.getT().equals(BOOL)) {
            return SemanticAnalysis.createSemanticAnalysis(new TypeError(BOOL, condAnalysis.getT()));
        } else {
            // expression and types alright, go to statement
           return p.stmt_.accept(this, state);
        }
    }

    @Override
    public SemanticAnalysis visit(SCondElse p, State state) {
        SemanticAnalysis<Type> condAnalysis = p.expr_.accept(new ExprCorrectnessChecker(), state);
        if (condAnalysis.hasErrors()) {
            return condAnalysis;
        } else if (!condAnalysis.getT().equals(BOOL)) {
            return SemanticAnalysis.createSemanticAnalysis(new TypeError(BOOL, condAnalysis.getT()));
        } else {
            // expression and types alright, go to statement
            SemanticAnalysis stmt1analysis = p.stmt_1.accept(this, state);
            SemanticAnalysis stmt2analysis = p.stmt_2.accept(this, state);
            return stmt1analysis.merge(stmt2analysis);
        }
    }

    @Override
    public SemanticAnalysis visit(SWhile p, State state) {
        SemanticAnalysis<Type> condAnalysis = p.expr_.accept(new ExprCorrectnessChecker(), state);
        if (condAnalysis.hasErrors()) {
            return condAnalysis;
        } else if (!condAnalysis.getT().equals(BOOL)) {
            return SemanticAnalysis.createSemanticAnalysis(new TypeError(BOOL, condAnalysis.getT()));
        } else {
            // expression and types alright, go to statement
           return p.stmt_.accept(this, state);
        }
    }

    @Override
    public SemanticAnalysis visit(SExp p, State state) {
        return p.expr_.accept(new ExprCorrectnessChecker(), state);
    }

    /**
     * Checks whether id is already defines in the {@link State}
     * @param id
     *          checked id
     * @param state
     *          current {@link State}
     * @return
     *      empty {@link SemanticAnalysis} on previously not defined,
     *      filled with {@link SemanticError} otherwise
     */
    static private SemanticAnalysis checkIfNotDuplicate(String id, State state) {
        if (state.hasId(id)) {
            if (state.isFunction(id)) {
                return SemanticAnalysis.createSemanticAnalysis(new DuplicatedIdentifier(id));
            }
            List<VariableDefinition> idDefs = state.getDeclaredIds().get(id);
            // get the last definiton
            Block lastDeclared = idDefs.get(idDefs.size() - 1).getDeclaredIn();
            if (lastDeclared == state.getCurrentBlock()) {
                return SemanticAnalysis.createSemanticAnalysis(new DuplicatedWithinBlock(id));
            } else {
                return new SemanticAnalysis();
            }
        }
        // no errors
        return new SemanticAnalysis();
    }

    /**
     * Checks whether {@link SDecl} is semantically correct and
     * add list of {@link Item} to {@link State}
     * @param p
     *          declaration {@link SDecl}
     * @param state
     *          current {@link State}
     * @return
     *          {@link SemanticAnalysis} with no erros on success,
     *          filled with {@link SemanticError} otherwise
     */
    static public SemanticAnalysis<Type> declareVariable(SDecl p, State state) {
        SemanticAnalysis analysis = new SemanticAnalysis();
        for(Item item : p.listitem_) {
            String id;
            if (item instanceof SNoInit) {
                // No variable initialization
                id = ((SNoInit) item).ident_;
                SemanticAnalysis itemAnalysis = checkIfNotDuplicate(id, state);
                if (itemAnalysis.hasErrors()) {
                    analysis.addErrors(itemAnalysis);
                    break;
                }
            } else { // item instanceof SInit
                // Variable is being intialized, have to check
                // the expression
                id = ((SInit)item).ident_;
                SemanticAnalysis itemAnalysis = checkIfNotDuplicate(id, state);
                if (itemAnalysis.hasErrors()) {
                    analysis.addErrors(itemAnalysis);
                    break;
                }
                SemanticAnalysis<Type> exprAnalysis = ((SInit)item).expr_.accept(new ExprCorrectnessChecker(), state);
                if (exprAnalysis.hasErrors()) {
                    analysis.addErrors(exprAnalysis);
                    break;
                }
                if (!p.type_.equals(exprAnalysis.getT())) {
                    analysis.getErrors().add(new TypeError(p.type_, exprAnalysis.getT()));
                    break;
                }
            }
            // no errors, can add to state
            state.addVarDefinition(id, p.type_);
        }
        return analysis;
    }

}
