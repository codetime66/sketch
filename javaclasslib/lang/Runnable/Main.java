// class that prints out "Lights On!" every second
class RunTest implements Runnable {
    private String str = "Lights On!";

    public void run() {
	while (true) {
	    System.out.println(str);
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		// ignore
	    }
	}
    }
}

class Main {
    public static void main(String[] args) {

	// create thread with 'RunTest.run()' as the core
	Thread tr = new Thread(new RunTest(), "runtest");

	tr.start(); 	// start thread
    }
}
	
