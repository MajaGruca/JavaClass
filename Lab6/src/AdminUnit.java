import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children;

    AdminUnit(){
        name="";
        adminLevel=0;
        population=0;
        area=0;
        density=0;
        parent=null;
    }

    public String toString(){
        String s= "nazwa: "+name+"\ntyp jednostki: "+adminLevel+"\npopulacja: "+population+"\npowierzchnia: "+area+"\nzageszczenie: "+density+"\nbbox:\n"+bbox.toString()+"\n\n";
        return s;
    }

    void fixMissingValues(){
        if(density==0 && parent!=null)
        {
            if(parent.density!=0)
                density = parent.density;
            else
                parent.fixMissingValues();
        }
        if(population==0)
        {
            population=area*density;
        }
    }

}
