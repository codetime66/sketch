class Main {

public static void main(String[] args) {
    try {
	Class c = Class.forName("java.lang.String");
	Object[] signers = c.getSigners();

	if (signers == null) {
	    System.out.println("null signers");
        } else {
	    for (int i = 0; i < signers.length; i++) {
		System.out.println("signer[" + i + "]:" + signers[i]);
		System.out.println(signers[i].getClass());
	}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
}
	}
