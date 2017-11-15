package CV;

import java.io.PrintStream;

public class Paragraph{
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
