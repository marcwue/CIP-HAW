/**
 * 
 */
package symbolTable;

import java.util.HashMap;

import nodes.IntNode;

/**
 * @author Marc Wüseke
 */
public class ArrayDescr extends AbstractDescr {
	private static final long serialVersionUID = 1L;
	int numberelems;
	AbstractDescr basetype;

	public ArrayDescr(int fn, int fs, AbstractDescr fb) {
		numberelems = fn;
		size = fs;
		basetype = fb;
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
