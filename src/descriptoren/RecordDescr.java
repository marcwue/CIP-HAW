package descriptoren;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marc Wüseke
 */
public class RecordDescr extends TypeDescr {
    private static final long serialVersionUID = 1L;
    Map<String, AbstractDescr> recSymbolTable;

    public RecordDescr(int size, HashMap<String, AbstractDescr> recSymbolTable) {
        super(size);
        this.recSymbolTable = recSymbolTable;
    }
}