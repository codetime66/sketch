import java.util.ListResourceBundle;

public class MyResources_fr extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
        // LOCALIZE THE FOLLOWING
        {"are no files", "n' y a pas des fichiers",},
        {"is one file", "y a un fichier"},
        {"are X files", "y a {2} fichiers"},
        {"pattern", "Il {0} sur {1}."}
        // END OF MATERIAL TO LOCALIZE
    };
}
