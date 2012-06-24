package descriptoren;

public class ProcDescr extends AbstractDescr {

    private int labelInAssembler;
    private SymbolTable lokal;

    public ProcDescr(int labelInAssembler, SymbolTable lokal) {
        this.labelInAssembler = labelInAssembler;
        this.lokal = lokal;
    }

    public int getLabelInAssembler() {
        return labelInAssembler;
    }

    @Override
    public int getSize() {
        return lokal.getSize() + 1;
    }

    public SymbolTable getLokal() {
        return lokal;
    }

    public String toString() {
        return "  " + lokal.toString();
    }

    /* (non-Javadoc)
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + labelInAssembler;
        result = prime * result + ((lokal == null) ? 0 : lokal.hashCode());
        return result;
    }

    /* (non-Javadoc)
      * @see java.lang.Object#equals(java.lang.Object)
      */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProcDescr other = (ProcDescr) obj;
        if (labelInAssembler != other.labelInAssembler)
            return false;
        if (lokal == null) {
            if (other.lokal != null)
                return false;
        } else if (!lokal.equals(other.lokal))
            return false;
        return true;
    }


}
