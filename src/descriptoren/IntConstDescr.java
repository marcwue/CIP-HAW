package descriptoren;

public class IntConstDescr extends AbstractDescr {
    int value;

    public IntConstDescr(int value) {
        this.value = value;
    }

    @Override
    public int getSize() {
        return 1;
    }

    public int value() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (!(o instanceof IntConstDescr))
            return false;
        IntConstDescr d = (IntConstDescr) o;
        return (this.value == d.value());
    }

    public int hashCode() {
        int result = 7;
        result = 31 * result + value;
        return result;
    }

    public String toString() {
        return "IntConstDescriptor value=" + value;
    }
}
