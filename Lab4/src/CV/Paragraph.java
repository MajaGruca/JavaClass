package CV;

import javax.xml.bind.annotation.*;
import java.io.PrintStream;
@XmlRootElement
@XmlSeeAlso({ParagraphWithList.class})
public class Paragraph{
    @XmlAttribute
    String content;

    Paragraph(){}

    Paragraph(String t)
    {
        content=t;
    }

    Paragraph setContent(String s){
        content=s;
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>%s</p>",content);
    }
}
