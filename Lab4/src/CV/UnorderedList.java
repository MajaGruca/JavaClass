package CV;

import java.util.ArrayList;
import java.util.List;

public class UnorderedList{
    List<ListItem> items = new ArrayList<>();

    UnorderedList addItem(String t){
        ListItem it = new ListItem(t);
        items.add(it);
        return this;
    }
}
