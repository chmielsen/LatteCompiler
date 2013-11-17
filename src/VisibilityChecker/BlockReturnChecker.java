package VisibilityChecker;

import Latte.Absyn.Block;
import Latte.Absyn.Stmt;
import Latte.Absyn.Type;
import Utils.SemanticAnalysis;
import Utils.ExpectedType;

/**
 * The state is the copy
 */
public class BlockReturnChecker implements Block.Visitor<SemanticAnalysis<Type>, ExpectedType> {

    @Override
    public SemanticAnalysis<Type> visit(Block block, ExpectedType expectedType) {
        expectedType.getState().setCurrentBlock(block);
        SemanticAnalysis analysis = new SemanticAnalysis();
        for (Stmt statement : block.liststmt_) {
            analysis = analysis.merge(statement.accept(new FunctionReturnChecker(), expectedType));
        }
        return analysis;
    }
}
