import java.util.ListResourceBundle;

public class MyResources_fr extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
        // LOCALIZE THE FOLLOWING
        {"pattern", "A {1} le {1}, un {3} {2} a mangé sur la Planète {0}."}
        // END OF MATERIAL TO LOCALIZE
    };
}
