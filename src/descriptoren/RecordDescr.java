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

	public SymbolTable fields() {
		return this.recSymbolTable;
	}

	public int size() {
		return recSymbolTable.getSize();
	}

	public String toString() {
		String b = "";
		b += "RecordDescriptor[ ";
		b += recSymbolTable.toString();
		b += " ] ";
		return b;
	}

	public AbstractDescr descriptorFor(String s) {
		return recSymbolTable.descriptorFor(s);
	}

	public int addressOf(String s) {
		return recSymbolTable.addressOf(s);
	}

	public boolean equals(Object o) {
		if (!(o instanceof RecordDescr))
			return false;
		RecordDescr d = (RecordDescr) o;
		return this.recSymbolTable.equals(d.fields());
	}

	public int hashCode() {
		int result = 7;
		result = 31 * result + recSymbolTable.hashCode();
		return result;
	}
}