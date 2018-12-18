import java.lang.reflect.*;

class Main {
    static void printConstructors(Constructor[] cons) {
        for (int i=0; i<cons.length; i++) {
            System.out.println(cons[i]);
        }
    }

    public static void main(String[] args) {
        printConstructors(java.lang.String.class.getDeclaredConstructors());
            // public java.lang.String()
            // public java.lang.String(java.lang.String)
            // public java.lang.String(char[])
            // public java.lang.String(char[],int,int)
            // public java.lang.String(byte[],int,int,int)
            // public java.lang.String(byte[],int)
            // private java.lang.String(byte[],int,int,
            //     sun.io.ByteToCharConverter)
            // public java.lang.String(byte[],int,int,java.lang.String) 
            //     throws java.io.UnsupportedEncodingException
            // public java.lang.String(byte[],java.lang.String) 
            //     throws java.io.UnsupportedEncodingException
            // public java.lang.String(byte[],int,int)
            // public java.lang.String(byte[])
            // public java.lang.String(java.lang.StringBuffer)
            // private java.lang.String(int,int,char[])

        printConstructors(InnerC.class.getDeclaredConstructors());
            // public Main$InnerC()
            // Main$InnerC(int)
            // private Main$InnerC(double)
    }

    static class InnerC {
        public InnerC() {}
        InnerC(int i) {}
        private InnerC(double i) {}
    }
}
