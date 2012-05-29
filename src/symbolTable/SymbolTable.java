/**
 * 
 */
package symbolTable;

/**
 * @author Marc Wüseke
 * 
 */
public interface SymbolTable {

	void declare(String ident, AbstractDescr descr);

	AbstractDescr descriptorFor(String ident);

	int globalAddressOf(String ident);

	int localAddressOf(String ident);

	int size();
}
