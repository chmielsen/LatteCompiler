package Checkers;

import Latte.Absyn.Block;
import Latte.Absyn.Stmt;
import Latte.Absyn.Type;
import Utils.ExpectedType;
import Utils.SemanticAnalysis;

/**
 * Class implementing Visitor pattern in order to check {@link Block}for return statements.
 * To keep variable local to the block the state in {@link ExpectedType} have to be a copy
 */
public class BlockReturnChecker implements Block.Visitor<SemanticAnalysis<Type>, ExpectedType> {

    @Override
    public SemanticAnalysis<Type> visit(Block block, ExpectedType expectedType) {
        expectedType.getState().setCurrentBlock(block);
        SemanticAnalysis analysis = new SemanticAnalysis();
        for (Stmt statement : block.liststmt_) {
            analysis = analysis.merge(statement.accept(new StatementReturnChecker(), expectedType));
        }
        return analysis;
    }
}
