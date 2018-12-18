import java.util.Observable;
import java.util.Observer;

class Main {
    final static int white = 0;
    final static int red = 1;
    final static int blue = 2;

    public static void main(String[] args) {
        ColorObservable colors = new ColorObservable();
        Statistician counter = new Statistician(3);

        // Assign Observers
        colors.addObserver(counter);
        colors.addObserver(new Echoer());
        System.out.println("Number of observers: " + colors.countObservers());

        // Make changes to Observable
        colors.changeColor(blue);
        colors.changeColor(white);
        colors.changeColor(red);
        colors.changeColor(blue);

        counter.report();

        // Remove one Observer
        colors.deleteObserver(counter);
        System.out.println("Number of observers: " + colors.countObservers());
        // Remove all Observers
        colors.deleteObservers();
        System.out.println("Number of observers: " + colors.countObservers());
    }
}
