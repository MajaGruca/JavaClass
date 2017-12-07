import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,AdminUnit> id_list = new HashMap<>();
    Map<AdminUnit,Long> parent_id = new HashMap<>();
    Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();

    void list(PrintStream out){
        for(int i=0;i<units.size();i++)
            out.append(units.get(i).toString());
    }

    void list(PrintStream out,int offset, int limit ){
        int max=limit+offset;
        if(units.size()<max)
            max=units.size();
        for(int i=offset;i<max;i++)
            out.append(units.get(i).toString());
    }

    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        regex=false;
        for(AdminUnit element: units)
            if(element.name.matches(pattern))
            {
                regex=true;
                ret.units.add(element);
            }
        return ret;
    }

    public void read(String filename) throws IOException {
        CSVReader reader1 = new CSVReader(filename,",",true);
        int i=0;
        while(reader1.next()){//reader1.next()){
            AdminUnit ad = new AdminUnit();
            Long id;
            id = reader1.getLong("id");
            Long parent = null;
            if(!reader1.isMissing(1))
                parent = reader1.getLong("parent");
            //ad.parent = id_list.get(reader1.getLong("parent"));
            if(!reader1.isMissing(2))
                ad.name = reader1.get("name");
            if(!reader1.isMissing(3))
                ad.adminLevel = reader1.getInt("admin_level");
            if(!reader1.isMissing(4))
                ad.population = reader1.getDouble("population");
            if(!reader1.isMissing(5))
                ad.area = reader1.getDouble("area");
            if(!reader1.isMissing(6))
                ad.density = reader1.getDouble("density");
            id_list.put(id,ad);
            parent_id.put(ad,parent);
            units.add(ad);
            i++;
        }
        for(AdminUnit n: units) {
            if (parent_id.get(n) != null)
                n.parent = id_list.get(parent_id.get(n));
        }
    }
}

