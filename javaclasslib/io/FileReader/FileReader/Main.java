import java.io.*;

class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("usage: java Main <inputfile> num");
        }
        int num = Integer.parseInt(args[1]);  // number of lines
        try {
            BufferedReader in = 
                new BufferedReader(new FileReader(new File(args[0])));
            PrintWriter out = new PrintWriter(System.out);
            String str;
            while (num > 0 && (str = in.readLine()) != null) {
                out.println(str);
                --num;
            }
            out.flush();
            out.close();
            in.close();
        } catch (NumberFormatException e) {
            System.err.println(e);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
