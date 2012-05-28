package symbolTable;

import java.io.Serializable;

/**
 * @author Marc W�seke
 * 
 */
public abstract class AbstractDescr implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int size;
	protected int level;

	public AbstractDescr() {
		this.size = 0;
		// this.level = CodeGen.level;
	}

	public int getLevel() {
		return level;
	}
	
	abstract public int getSize();
}

