package Checkers;

import Latte.Absyn.Block;
import Latte.Absyn.Stmt;
import Utils.SemanticAnalysis;
import Utils.State;

/**
 * Class implementing Visitor pattern in order to check {@link Block} for return statements.
 * To keep variable local to the block the {@link State} have to be a copy.
 */public class BlockCorrectnessChecker implements Block.Visitor<SemanticAnalysis, State> {
    @Override
    public SemanticAnalysis visit(Block block, State state) {
        state.setCurrentBlock(block);
        SemanticAnalysis analysis = new SemanticAnalysis();
        for (Stmt statement : block.liststmt_) {
            analysis = analysis.merge(statement.accept(new StatementCorrectnessChecker(), state));
        }
        return analysis;
    }
}

