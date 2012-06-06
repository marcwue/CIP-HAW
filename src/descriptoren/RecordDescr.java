package descriptoren;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marc Wüseke
 */
public class RecordDescr extends AbstractDescr {
    private static final long serialVersionUID = 1L;
    SymbolTable recSymbolTable;

    public RecordDescr(SymbolTable recSymbolTable) {
        this.recSymbolTable = recSymbolTable;
    }
}