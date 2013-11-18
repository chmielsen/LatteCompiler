package Utils;

import Latte.Absyn.TBool;
import Latte.Absyn.TInt;
import Latte.Absyn.TStr;
import Latte.Absyn.TVoid;

/**
 * Class for purely economic resources, so we don't create
 * new object each time
 */
public class TypeConstants {
    public static TInt INT = new TInt();
    public static TStr STRING = new TStr();
    public static TBool BOOL = new TBool();
    public static TVoid VOID = new TVoid();
}
