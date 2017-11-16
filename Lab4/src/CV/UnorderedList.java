package CV;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class UnorderedList{
    @XmlElement(name="item")
    List<ListItem> items = new ArrayList<>();

    UnorderedList addItem(String t){
        ListItem it = new ListItem(t);
        items.add(it);
        return this;
    }
}
