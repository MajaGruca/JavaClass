package CV;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph{

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
        out.printf("<p><ol>");
        for(int i=0;i<list.items.size();i++) {
            (list.items.get(i)).writeHTML(out);
        }
        out.printf("</ol></p>");
    }
}
