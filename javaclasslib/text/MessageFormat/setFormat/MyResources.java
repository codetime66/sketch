import java.util.ListResourceBundle;

public class MyResources extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
        // LOCALIZE THE FOLLOWING
        {"color", "blue"},
        {"animal", "dog"},
        {"pattern", "At {1} on {1}, a {2} {3} ate on Planet {0}."}
        // END OF MATERIAL TO LOCALIZE
    };
}
