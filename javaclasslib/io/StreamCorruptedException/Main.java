import java.io.*;

class Main {
    public static void main(String[] args) {
        try {
            // Try to create an ObjectInputStream for
            // a non-object stream.
            FileInputStream f2 = new FileInputStream("Main.class");
            ObjectInputStream in = new ObjectInputStream(f2);
            System.out.println(in.readObject());
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
