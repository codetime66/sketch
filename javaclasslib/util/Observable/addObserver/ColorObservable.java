import java.util.Observable;

class ColorObservable extends Observable {
    public static String colorName(int i) {
        switch (i) {
        case 1: return ("Red");
        case 0: return ("White");
        case 2: return ("Blue");
        default: return (null);
            }
    }
    public void changeColor(int i) {
        setChanged();
        notifyObservers(new Integer(i));
        clearChanged(); // not necessary; notifyObservers() already clears it
    }
}
