import java.util.ListResourceBundle;

public class MyResources extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
        // LOCALIZE THE FOLLOWING
        {"are no files", "are no files"},
        {"is one file", "is one file"},
        {"are X files", "are {2} files"},
        {"pattern", "There {0} on {1}."}
        // END OF MATERIAL TO LOCALIZE
    };
}
