import java.text.*;
import java.util.*;

class MainHELENA {
    static public void main(String[] args) {

        MessageFormat fmt = new MessageFormat("There {0,choice,0#are no dollars|1#is one dollar|1<are {0, number, currency}| dollars} in the drawer.");
        System.out.println("Formatter Pattern : " + fmt.toPattern());
        Object[] test1 = {new Double(0)}, test2 = {new Double(0.9)}, 
                 test3 = {new Double(1)}, test4 = {new Double(1.5)};
        System.out.println("Format with 0 :   " + fmt.format(test1));
        System.out.println("Format with 0.9 : " + fmt.format(test2));
        System.out.println("Format with 1 :   " + fmt.format(test3));
        System.out.println("Format with 1.5 : " + fmt.format(test4));
    }
}

/*  This outputs:
Formatter Pattern : There {0,choice,0.0#are no money |1.0#is one dollar |1.0<are {0, number, currency} } in the drawer.
Format with 0   : There are no dollars  in the drawer.
Format with 0.9 : There are no dollars  in the drawer.
Format with 1   : There is one dollar  in the drawer.
Format with 1.5 : There are $1.50  in the drawer.
                                                 
*/

