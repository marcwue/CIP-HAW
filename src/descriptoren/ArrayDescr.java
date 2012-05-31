package descriptoren;

import nodes.*;

public class ArrayDescr extends TypeDescr {
    private static final long serialVersionUID = 1L;
    private int numberOfElements;
    private AbstractNode basetype;

    public ArrayDescr(int numberOfElements, int size, AbstractNode basetype) {
        super(size);
        this.numberOfElements = numberOfElements;
        this.basetype = basetype;
    }
}