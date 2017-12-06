public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();

    AdminUnit(){
        name="";
        adminLevel=0;
        population=0;
        area=0;
        density=0;
    }

    public String toString(){
        String s= "\nnazwa:"+name+"\ntyp jednostki :"+adminLevel+"\npopulacja: "+population+"\npowierzchnia :"+area+"\nzageszczenie: "+density;
        return s;
    }

}
