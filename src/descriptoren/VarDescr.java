package descriptoren;

public class VarDescr extends AbstractDescr {
    private static final long serialVersionUID = 1L;
    private int addr;
    private boolean isVarPar;
    private AbstractDescr type;

    public VarDescr(int addr, AbstractDescr type) {
        // ToDo size berechnen
        this.isVarPar = false;
        this.addr = addr;
        this.type = type;
    }

    public int getAddr() {
        return addr;
    }

    public boolean isVarPar() {
        return isVarPar;
    }

    public void setVarPar(boolean varPar) {
        isVarPar = varPar;
    }

    public AbstractDescr getType() {
        return type;
    }

    public int getSize() {
        return -1;
    }

}

