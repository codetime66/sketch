import java.util.ListResourceBundle;

public class MyResources_fr extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
        // LOCALIZE THE FOLLOWING
        {"color", "bleu"},
        {"animal", "chien"},
        {"pattern", 
         "A {1,time} le {1,date}, un {3} {2} a mang� sur la Plan�te {0,number,integer}."}
        // END OF MATERIAL TO LOCALIZE
    };
}
