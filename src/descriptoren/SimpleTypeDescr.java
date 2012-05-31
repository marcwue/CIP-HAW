package descriptoren;

public class SimpleTypeDescr extends TypeDescr {
    private String name;

    public SimpleTypeDescr(int size, String name) {
        super(size);
        this.name = name;
    }
}
