package VisibilityChecker;

import Latte.Absyn.Program;
import Latte.Absyn.TopDef;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wojtek
 * Date: 11/11/13
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgramVC implements Program.Visitor<List<VisibilityError>, Object> {

    @Override
    public List<VisibilityError> visit(Program p, Object arg) {
        List<VisibilityError>  visibilityErrors = new ArrayList<VisibilityError>();

        for (TopDef topDef : p.listtopdef_) {
            //visibilityErrors.addAll();
            ;
        }
        return null;
    }
}
