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
        String s= "nazwa:"+name+"\ntyp jednostki :"+adminLevel+"\npopulacja: "+population+"\npowierzchnia :"+area+"\nzageszczenie: "+density+"\n\n";
        return s;
    }

}
