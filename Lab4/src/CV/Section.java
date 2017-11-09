package CV;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section extends Document{
    String title;
    List<Paragraph> paragraps = new ArrayList<>() ;


    Section(String title){}
    Section setTitle(String title){
        this.title = title;
        return this;}
    Section addParagraph(String paragraphText){
        Paragraph newp = Paragraph();
        paragraps.add(newp);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraps.add(p);
        return this;
    }
    void writeHTML(PrintStream out){}
}
