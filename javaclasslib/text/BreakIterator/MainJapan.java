import java.awt.*;

class MainJapan extends Frame {

    public static void main(String[] args) {
        
        StringBuffer sbuf = new StringBuffer();

        sbuf = new StringBuffer("\u7956\u7236\u304c\u30af\u30ea\u30b9\u30de\u30b9\u306b\u829d\u5c45\u3092\u898b\u306b\u884c\u304d\u3001\u3068\u3066\u3082\u611f\u6fc0\u3057\u305f\u3068\u3044\u3063\u3066\u307e\u3057\u305f\u3002");

        new Main(new String(sbuf), new String(sbuf));
    }
}
