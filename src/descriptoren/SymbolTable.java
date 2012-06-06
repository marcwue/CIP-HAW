/**
 * 
 */
package descriptoren;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marc Wüseke
 * 
 */
public class SymbolTable {

	private Map<String, AbstractDescr> descriptorMap = new HashMap<String, AbstractDescr>();
	private Map<String, Integer> addressMap = new HashMap<String, Integer>();
	private int currentAddress = 0;
	private SymbolTable parentTable;

	public SymbolTable() {
		super();
	}
	
	/**
	 * @param parentTable
	 */
	public SymbolTable(SymbolTable parentTable) {
		super();
		this.parentTable = parentTable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SymbolTable [descriptorMap=" + descriptorMap + ", addressMap="
				+ addressMap + ", currentAddress=" + currentAddress
				+ ", parentTable=" + parentTable + "]";
	}

	public void declare(String ident, AbstractDescr descr) {
		if (!(addressMap.containsKey(ident))) {
			descriptorMap.put(ident, descr);
			addressMap.put(ident, currentAddress);
			if (descr == null) {
				System.out.println("---- compile Error-----\n Variable = "
						+ ident);
			}
			currentAddress += descr.getSize();
		} else {
			System.out
					.println("Fehler, zweimal die gleiche Variable deklariert");
		}
	}

	public AbstractDescr descriptorFor(String ident) {
		// built-in types
		if (ident.equalsIgnoreCase("integer")) {
			return new SimpleTypeDescr("INTEGER");
		}

		AbstractDescr d = descriptorMap.get(ident);
		if (d == null && parentTable != null) {
			return parentTable.descriptorFor(ident);
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

	public int size() {
		return currentAddress;
	}

}
