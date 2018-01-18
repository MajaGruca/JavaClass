package Zad1;

import java.util.ArrayList;
import java.util.List;

public class Employees {

    String title;
    String name;
    String surname;
    String building;
    String room;
    String[] phones;
    String mail;

    Employees(){
        title="";
        name="";
        surname="";
        building="";
        room="";
        mail="";
    }

    public String toString(){
        String s= name+" "+surname;
        return s;
    }

}
