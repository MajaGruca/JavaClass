package CV;

import java.io.PrintStream;

public class Photo {
    String url;
    Photo(String url){
        this.url =url;
    }
    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"120\" width=\"160\"/>\n",url);
    }
}
