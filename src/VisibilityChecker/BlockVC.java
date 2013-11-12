package VisibilityChecker;

import Latte.Absyn.Block;
import Latte.Absyn.Stmt;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/12/13
 * Time: 12:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class BlockVC implements Block.Visitor<Set<VisibilityError>, Set<String>> {
    @Override
    public Set<VisibilityError> visit(Block block, Set<String> visibleIds) {
        Set<VisibilityError> visibilityErrors = new HashSet<VisibilityError>();
        for (Stmt statement : block.liststmt_) {
            // Pass visibleIds by reference
            //visibilityErrors.addAll(new )
            ;
        }
        return null;
    }
}

