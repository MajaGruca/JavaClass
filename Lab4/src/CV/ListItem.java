package CV;

import javax.xml.bind.annotation.*;
import java.io.PrintStream;
@XmlRootElement
public class ListItem {
    @XmlValue
    String content;
    ListItem(){}

    ListItem(String t)
    {
        content = t;
    }
    void writeHTML(PrintStream out){
        out.printf("<li>%s</li>",content);
    }
}
