import java.io.*;

class Main {

public static void main (String[] args) {
try {
    // set up command, arguments, and environment variables
    String[] progarray = new String[2];
    String[] envp = new String[1];
//    String prog = "/bin/ls";
    String prog = "e:\\app\\mks\\mksnt\\ls.exe";

    Runtime rt = Runtime.getRuntime();

    progarray[0] = prog;
    progarray[1] = "/";
    envp[0] = "TERM=vt100";

    Process p1 = rt.exec(prog);                // '/bin/ls'
    Process p2 = rt.exec(prog, envp);          // '(TERM=vt100; ls)'
    Process p3 = rt.exec(progarray);           // '/bin/ls /'
    Process p4 = rt.exec(progarray, envp);     // '(TERM=vt100; ls /)'
} catch (IOException e) {
    System.err.println("exec error:" + e);
}
}
}
