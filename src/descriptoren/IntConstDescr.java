package descriptoren;

public class IntConstDescr extends AbstractDescr {
    private int value;

    public IntConstDescr(int size, int value) {
        super(size);
        this.value = value;
    }
}
