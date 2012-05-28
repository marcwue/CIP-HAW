package symbolTable;

/**
 * @author Marc Wüseke
 * 
 */
public class VarDescr extends AbstractDescr {
	private static final long serialVersionUID = 1L;
	int addr;
	boolean isvarpar;
	AbstractDescr typ;

	public VarDescr(int fa, AbstractDescr ftyp) {
		isvarpar = false;
		addr = fa;
		typ = ftyp;
	}

	/**
	 * @return the addr
	 */
	public int getAddr() {
		return addr;
	}

	/**
	 * @param addr the addr to set
	 */
	public void setAddr(int addr) {
		this.addr = addr;
	}

	/**
	 * @return the isvarpar
	 */
	public boolean isIsvarpar() {
		return isvarpar;
	}

	/**
	 * @param isvarpar the isvarpar to set
	 */
	public void setIsvarpar(boolean isvarpar) {
		this.isvarpar = isvarpar;
	}

	/**
	 * @return the typ
	 */
	public AbstractDescr getTyp() {
		return typ;
	}

	/**
	 * @param typ the typ to set
	 */
	public void setTyp(AbstractDescr typ) {
		this.typ = typ;
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

