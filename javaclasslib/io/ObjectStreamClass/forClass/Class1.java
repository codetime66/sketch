import java.io.Serializable;

public class Class1 implements Serializable {
    int field1;
    String field2;
    transient int field3;

    public Class1() {
	field1 = 10;
	field2 = "a string";
	field3 = -1;
    }
}
