/**
 * 
 */
package symbolTable;

import java.util.HashMap;

/**
 * @author Marc W�seke
 */
public class RecordDescr extends AbstractDescr {
	private static final long serialVersionUID = 1L;
	HashMap<String, AbstractDescr> recsymbolTable;

	public RecordDescr(int fs, HashMap<String, AbstractDescr> fr) {
		size = fs;
		recsymbolTable = fr;
	}

	/* (non-Javadoc)
	 * @see symbolTable.AbstractDescr#getSize()
	 */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}