package Zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String filename;
    String[] current;
    String[] header;

    List<Employees> employees = new ArrayList<>();

    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(String filename) {
        this.filename = filename;
    }

    public CSVReader(String filename, String delimiter) {
        this(filename);
        this.delimiter = delimiter;
    }


    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    boolean next() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return false;
        }
        current = line.split(delimiter);
        return true;
    }

    void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        header = line.split(delimiter);
        int k = header.length;
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }

    List<String> getColumnLabels() {
        return columnLabels;
    }

    int getRecordLength() {
        return current.length;
    }

    boolean isMissing(int columnIndex) {
        if (columnIndex >= current.length || current[columnIndex].isEmpty())
            return true;
        else
            return false;
    }

    boolean isMissing(String columnLabel) {
        if (!columnLabelsToInt.containsKey(columnLabel))
            return true;
        return isMissing(columnLabelsToInt.get(columnLabel));
    }

    String get(int columnIndex) {
        if (isMissing(columnIndex))
            return "";
        String str = current[columnIndex].replaceAll("\\s+","");
        return str;
    }

    String get(String columnLabel) {
        if (isMissing(columnLabel))
            return "";
        int index = columnLabelsToInt.get(columnLabel);
        String str = current[index].replaceAll("\\s+","");
        return str;
    }


    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("pracownicy-geo-agh.csv", ";", true);
        while (reader.next()) {
            Employees em = new Employees();
            if (!reader.isMissing(1))
                em.title = reader.get("tytuł");
            if (!reader.isMissing(2))
                em.name = reader.get("imię");
            if (!reader.isMissing(3))
                em.surname = reader.get("nazwisko");
            if (!reader.isMissing(4))
                em.building = reader.get("pawilon");
            if (!reader.isMissing(5))
                em.room = reader.get("pokój");
            if (!reader.isMissing(6))
                em.phones = reader.current[6].split(",");
            if (!reader.isMissing(7))
                em.mail = reader.get("mail");
            reader.employees.add(em);
        }

        //ile kobiet i mezczyzn
        int k=0; //kobiety
        int m=0; //mezczyzni
        for(Employees n: reader.employees)
        {
            if(n.name.charAt(n.name.length()-1) =='a')
            {
                k++;
            }
            else
            {
                m++;
            }
        }
        System.out.println("Ilość kobiet: "+k+", ilość mężczyzn: "+m);

        //kobiety w C4
        System.out.println("\nKobiety pracujące w C4: ");
        for(Employees n: reader.employees)
        {
            if(n.name.charAt(n.name.length()-1) =='a' && n.building.equals("C4"))
            {
                System.out.println(n.toString());
            }
        }

        //wiecej niz 1 numer
        System.out.println("\nPracownicy posiadajacy wiecej niz jeden numer tel.: ");
        for(Employees n: reader.employees)
        {
            if(n.phones.length > 1)
            {
                System.out.println(n.toString());
            }
        }
    }
}
