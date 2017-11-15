package CV;

import java.io.PrintStream;

public class ListItem {
    String content;

    ListItem(String t)
    {
        content = t;
    }
    void writeHTML(PrintStream out){
        out.printf("<li>%s</li>",content);
    }
}
