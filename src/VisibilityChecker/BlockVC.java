package VisibilityChecker;

import Latte.Absyn.Block;
import Latte.Absyn.Stmt;
import Utils.SemanticAnalysis;
import Utils.State;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/12/13
 * Time: 12:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class BlockVC implements Block.Visitor<SemanticAnalysis, State> {
    @Override
    public SemanticAnalysis visit(Block block, State state) {
        state.setCurrentBlock(block);
        SemanticAnalysis analysis = new SemanticAnalysis();
        for (Stmt statement : block.liststmt_) {
            analysis = analysis.merge(statement.accept(new StatementVC(), state));
        }
        return analysis;
    }
}

