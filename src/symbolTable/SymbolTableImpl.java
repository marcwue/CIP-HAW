package symbolTable;

/**
 * @author Marc Wüseke
 *
 */
public class SymbolTableImpl implements SymbolTable {

	
	
	/* (non-Javadoc)
	 * @see symbolTable.SymbolTable#declare(java.lang.String, symbolTable.AbstractDescr)
	 */
	@Override
	public void declare(String ident, AbstractDescr descr) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see symbolTable.SymbolTable#descriptorFor(java.lang.String)
	 */
	@Override
	public AbstractDescr descriptorFor(String ident) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see symbolTable.SymbolTable#globalAddressOf(java.lang.String)
	 */
	@Override
	public int globalAddressOf(String ident) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see symbolTable.SymbolTable#localAddressOf(java.lang.String)
	 */
	@Override
	public int localAddressOf(String ident) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see symbolTable.SymbolTable#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
