package VisibilityChecker;

import Latte.Absyn.FnDef;
import Latte.Absyn.TopDef;
import VisibilityChecker.Errors.VisibilityError;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/12/13
 * Time: 12:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class TopDefVC implements TopDef.Visitor<List<VisibilityError>, Object> {

    @Override
    public List<VisibilityError> visit(FnDef p, Object arg) {
        return null;
    }
}
