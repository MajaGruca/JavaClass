package CV;

import javax.xml.bind.annotation.*;
import java.io.PrintStream;
@XmlRootElement
public class Photo {
    @XmlAttribute
    String url;
    Photo(){}
    Photo(String url){
        this.url =url;
    }
    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"120\" width=\"160\"/>\n",url);
    }
}
