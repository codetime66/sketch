import java.util.Observer;
import java.util.Observable;

// Observer that counts number of times the Integer 'arg' has been accessed
class Statistician implements Observer {
    private int[] counts;
    Statistician(int array_size) {
        counts = new int[array_size];
    }
    public void update(Observable o, Object arg) {
        Integer int_obj = (Integer)arg;
        if (int_obj.intValue() < counts.length)
            ++counts[int_obj.intValue()];
    }
    public void report() {
        System.out.println("Record of changes: ");
        for (int i = 0; i < counts.length; i++)
            System.out.println(ColorObservable.colorName(i) + ": " + counts[i]);
    }
}

// Observer that prints out arg each time that it is accessed
class Echoer implements Observer {
    int current_color;
    public void update(Observable o, Object arg) {
        int new_color = ((Integer)arg).intValue();
        System.out.println("Changing from " + 
                           ColorObservable.colorName(current_color) +
                           " to " +
                           ColorObservable.colorName(new_color));
        current_color = new_color;
    }
}
