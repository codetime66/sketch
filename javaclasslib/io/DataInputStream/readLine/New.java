import java.io.*;

class New {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("java New <inputfile>");
            System.exit(-1);
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));

            String line;
            while ((line=in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
