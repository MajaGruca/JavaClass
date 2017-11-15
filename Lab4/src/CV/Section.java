package CV;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section{
    String title;
    List<Paragraph> paragraps = new ArrayList<>();

    Section(String t){
        title=t;
    }
    Section setTitle(String title){
        this.title = title;
        return this;}
    Section addParagraph(String paragraphText){
        Paragraph newp = new Paragraph(paragraphText);
        paragraps.add(newp);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraps.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("<h1>%s</h1>",title);
        for(int i=0;i<paragraps.size();i++) {
            (paragraps.get(i)).writeHTML(out);
        }
    }
}
