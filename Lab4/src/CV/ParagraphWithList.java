package CV;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class ParagraphWithList extends Paragraph{

    @XmlElement(name="list")
    UnorderedList list = new UnorderedList();

    ParagraphWithList(){}

    ParagraphWithList setContent(String s){
        content=s;
        return this;
    }

    ParagraphWithList addListItem(String t)
    {
        list.addItem(t);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>%s<ol>",content);
        for(int i=0;i<list.items.size();i++) {
            (list.items.get(i)).writeHTML(out);
        }
        out.printf("</ol></p>");
    }
}
