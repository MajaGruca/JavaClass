import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long,AdminUnit> id_list = new HashMap<>();

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

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList nbs = new AdminUnitList();
        for(AdminUnit n: units) {
            if(n.adminLevel==unit.adminLevel && n!=unit)
            {
                if(unit.adminLevel==6)
                {
                    if(unit.bbox.distanceTo(n.bbox)<maxdistance || unit.bbox.intersects(n.bbox))
                        nbs.units.add(n);
                }
                else
                {
                    if(unit.bbox.intersects(n.bbox))
                        nbs.units.add(n);
                }
            }
        }
        return nbs;
    }

    public void read(String filename) throws IOException {
        CSVReader reader1 = new CSVReader(filename,",",true);
        Map<AdminUnit,Long> parent_id = new HashMap<>();
        Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();
        while(reader1.next()){
            AdminUnit ad = new AdminUnit();
            Long id;
            id = reader1.getLong("id");
            Long parent = null;
            if(!reader1.isMissing("parent"))
                parent = reader1.getLong("parent");
            if(!reader1.isMissing("name"))
                ad.name = reader1.get("name");
            if(!reader1.isMissing("admin_level"))
                ad.adminLevel = reader1.getInt("admin_level");
            if(!reader1.isMissing("population"))
                ad.population = reader1.getDouble("population");
            if(!reader1.isMissing("area"))
                ad.area = reader1.getDouble("area");
            if(!reader1.isMissing("density"))
                ad.density = reader1.getDouble("density");
            if(!reader1.isMissing("x1") && !reader1.isMissing("y1"))
            {
                double Px=reader1.getDouble("x1");
                double Py=reader1.getDouble("y1");
                ad.bbox.addPoint(Px,Py);
            }
            if(!reader1.isMissing("x3") && !reader1.isMissing("y3"))
            {
                double Px=reader1.getDouble("x3");
                double Py=reader1.getDouble("y3");
                ad.bbox.addPoint(Px,Py);
            }

            id_list.put(id,ad);
            parent_id.put(ad,parent);
            units.add(ad);
            if(!parentid2child.containsKey(parent))
            {
                List<AdminUnit> child = new ArrayList<>();
                child.add(ad);
                parentid2child.put(parent, child);
            }
            else
            {
                List<AdminUnit> child = parentid2child.get(parent);
                child.add(ad);
                parentid2child.put(parent,child);
            }
        }
        for(AdminUnit n: units) {
            if (parent_id.get(n) != null)
            {
                n.parent = id_list.get(parent_id.get(n));
            }
            if(parentid2child.containsKey(id_list.get(n)))
            {
                n.children = parentid2child.get(id_list.get(n));
            }
            n.fixMissingValues();
        }
    }

}

