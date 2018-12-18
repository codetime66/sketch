import java.text.*;
import java.util.*;

class Main {
    static public void main(String[] args) {

        MessageFormat form = new MessageFormat("The disk {1} contains {0}."); 
        double[] filelimits = {0,1,2}; 
        String[] filepart = {"no files","one file","{0,number} files"}; 
        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart); 
        form.setFormat(1,fileform);

        Object[] arguments = {null, null};

        arguments[1] = "MyDisk"; 

        arguments[0] = new Long(0); 
        System.out.println(form.format(arguments)); 

        arguments[0] = new Long(1);
        System.out.println(form.format(arguments)); 

        arguments[0] = new Long(12373); 
        System.out.println(form.format(arguments)); 
    }
}
