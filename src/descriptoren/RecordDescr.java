package descriptoren;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marc Wüseke
 */
public class RecordDescr extends TypeDescr {
    private static final long serialVersionUID = 1L;
    Map<String, AbstractDescr> recsymbolTable;

    public RecordDescr(int fs, HashMap<String, AbstractDescr> fr) {
        super(fs);
        recsymbolTable = fr;
    }
}