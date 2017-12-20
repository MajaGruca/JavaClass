import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

public class AdminUnitList{
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
            if(element.name.contains(pattern))
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

    AdminUnitList sortInplaceByName(){
        class CompareByName implements Comparator<AdminUnit> {
            @Override
            public int compare(AdminUnit t, AdminUnit t1) {
                    return t.name.compareTo(t1.name);
            }

        }
        CompareByName comp = new CompareByName();
        this.units.sort(comp);

        return this;
    }


    AdminUnitList sortInplaceByArea(){
        Comparator<AdminUnit> comp = new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit t, AdminUnit t1) { return Double.compare(t.area,t1.area); }
        };
        this.units.sort(comp);
        return this;
    }

    AdminUnitList sortInplaceByPopulation(){
        this.units.sort((t,t1)->Double.compare(t.population,t1.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        this.units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList sorted = new AdminUnitList();
        for(AdminUnit n: this.units)
        {
            AdminUnit ad = new AdminUnit();
            ad=n;
            sorted.units.add(ad);
        }
        sorted.sortInplace(cmp);
        return sorted;
    }

    interface AdUnTester{
        boolean check(AdminUnit p);
    }

    AdminUnitList filter(Predicate<AdminUnit> pred){
        AdminUnitList ad = new AdminUnitList();
        for(AdminUnit n: this.units)
        {
            if(pred.test(n))
                ad.units.add(n);
        }
        return ad;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        int i=0;
        AdminUnitList ad = new AdminUnitList();
        for(AdminUnit n: this.units)
        {
            if(pred.test(n))
            {
                ad.units.add(n);
                i++;
            }
            if(i==limit)
                break;
        }
        return ad;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        int i=0;
        AdminUnitList ad = new AdminUnitList();
        for(AdminUnit n: this.units)
        {
            if(pred.test(n))
            {
                if(i<offset)
                    i++;
                else {
                    ad.units.add(n);
                    i++;
                }
            }
            if(i==limit)
                break;
        }
        return ad;
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

