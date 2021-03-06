/**
 *
 */
package descriptoren;

import app.TokenID;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Marc Wüseke
 */
public class SymbolTable {

    private Map<String, AbstractDescr> descriptorMap;
    private Map<String, Integer> addressMap;
    private int currentAddress = 0;

    //Todo Sinn klären
    private int currentParameterAddress = 0; // muss spaeter geaendert werden
    // -> wenn SL-Register eingebaut
    private SymbolTable parentTable;

    public SymbolTable() {
        this.parentTable = null;
        descriptorMap = new HashMap<>();
        addressMap = new HashMap<>();
    }

    public SymbolTable(SymbolTable parentTable) {
        this.parentTable = parentTable;
        descriptorMap = new HashMap<>();
        addressMap = new HashMap<>();
    }

    @Override
    public String toString() {
        String retS = "Descriptor Map:\n";
        for (Entry<String, AbstractDescr> aD : descriptorMap.entrySet()) {
            retS += aD.toString() + "\n";
        }
        retS += "Address Map:\n";
        for (Entry<String, Integer> aM : addressMap.entrySet()) {
            retS += aM.toString() + "\n";
        }
        return "SymbolTable\n" + retS + "Current Address:" + currentAddress;
    }

    public void declare(String ident, AbstractDescr descr) {
        if (!(addressMap.containsKey(ident))) {
            descriptorMap.put(ident, descr);
            addressMap.put(ident, currentAddress);
            if (descr == null) {
                System.out.println("---- compile Error-----\n Variable = " + ident);
            }
            // May return NullPointerException
            currentAddress += descr.getSize();
        } else {
            System.out
                    .println("Fehler, zweimal die gleiche Variable deklariert");
        }
    }

    public void declareType(String ident, AbstractDescr descr) {
        if (!(descriptorMap.containsKey(ident))) {
            descriptorMap.put(ident, descr);
            addressMap.put(ident, -1);
            if (descr == null) {
                System.out.println("---- compile Error-----\n Variable = "
                        + ident);
            }
        } else {
            System.out
                    .println("Fehler, zweimal die gleiche Variable deklariert");
        }
    }

    public void declareParams(String ident, AbstractDescr descr) {
        if (!(addressMap.containsKey(ident))) {
            currentParameterAddress = currentParameterAddress + descr.getSize();
            addressMap.put(ident, currentParameterAddress);
            descriptorMap.put(ident, descr);
        } else {
            System.out.println("Variable zweimal deklariert");
        }

    }

    public AbstractDescr descriptorFor(String ident) {
        // built-in types
        if (("integer").equalsIgnoreCase(ident)) {
            return new SimpleTypeDescr(TokenID.INT);
        } else if (("string").equalsIgnoreCase(ident)) {
            return new SimpleTypeDescr(TokenID.STR);
        } else if (("boolean").equalsIgnoreCase(ident)) {
            return new SimpleTypeDescr(TokenID.BOOLEAN);
        }
        AbstractDescr d = descriptorMap.get(ident);
        if (d == null && parentTable != null) {
            return parentTable.descriptorFor(ident);
        } else if (d == null) {
            System.out.println("Deskriptor fuer " + ident + " nicht gefunden.");
        }
        return d;
    }

    public int addressOf(String ident) {

        if (addressMap.containsKey(ident)) {
            return addressMap.get(ident);
        }
        if (parentTable != null) {
            return parentTable.addressOf(ident);
        }
        return -1;

    }

    public int getSize() {
        return currentAddress;
    }

    public void link(String ident, String toIdent) {
        if (addressMap.containsKey(ident) && addressMap.containsKey(toIdent)
                && descriptorFor(ident) == descriptorFor(toIdent)) {
            addressMap.put(ident, addressOf(toIdent));
        } else {
            if (!addressMap.containsKey(ident))
                System.out.println(ident + " isn't declarated");
            if (!addressMap.containsKey(toIdent))
                System.out.println(ident + " isn't declarated");
            if (descriptorFor(ident) != descriptorFor(toIdent))
                System.out.println("Cann't link address. " + ident + " and "
                        + toIdent + " have different types.");
        }
    }

    public int getLevel() {
        if (this.parentTable == null)
            return 0;
        else
            return 1 + parentTable.getLevel();
    }

}
