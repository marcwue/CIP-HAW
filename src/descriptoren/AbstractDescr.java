package descriptoren;

import cip.base.CodeGen;

import java.io.Serializable;

/**
 * @author Marc Wüseke
 */
public abstract class AbstractDescr implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int size;
    protected int level;

    public AbstractDescr() {
    	
    }

    public int getLevel() {
        return level;
    }

    public int getSize() {
        return 0;
    }
}