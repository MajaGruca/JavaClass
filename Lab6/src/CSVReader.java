import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String filename;
    String[] current;
    String[] header;

    List<String> columnLabels = new ArrayList<>();
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(String filename){
        this.filename=filename;
    }

    public CSVReader(String filename,String delimiter){
        this(filename);
        this.delimiter=delimiter;
    }


    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader) parseHeader();
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
            columnLabelsToInt.put(header[i],i);
        }
    }

    List<String> getColumnLabels(){
        return columnLabels;
    }

    int getRecordLength(){
        return current.length;
    }

    boolean isMissing(int columnIndex){
        if(current[columnIndex].length()==0)
            return true;
        else
            return false;
    }

    boolean isMissing(String columnLabel){
        int index = columnLabels.indexOf(columnLabel);
        if(current[index].length()==0)
            return true;
        else
            return false;
    }

    String get(int columnIndex){
        if(isMissing(columnIndex))
            return "";
        return current[columnIndex];
    }

    String get(String columnLabel){
        if(isMissing(columnLabel))
            return "";
        int index = columnLabelsToInt.get(columnLabel);
        return current[index];
    }

    int getInt(int columnIndex){
        if(isMissing(columnIndex))
            throw new RuntimeException("Field is empty");
        String data = get(columnIndex);
        return Integer.parseInt(data);
    }

    int getInt(String columnLabel){
        if(isMissing(columnLabel))
            throw new RuntimeException("Field is empty");
        String data = get(columnLabel);
        return Integer.parseInt(data);
    }

    long getLong(int columnIndex){
        if(isMissing(columnIndex))
            throw new RuntimeException("Field is empty");
        String data = get(columnIndex);
        return Long.parseLong(data);
    }

    long getLong(String columnLabel){
        if(isMissing(columnLabel))
            throw new RuntimeException("Field is empty");
        String data = get(columnLabel);
        return Long.parseLong(data);
    }

    double getDouble(int columnIndex){
        if(isMissing(columnIndex))
            throw new RuntimeException("Field is empty");
        String data = get(columnIndex);
        return Double.parseDouble(data);
    }

    double getDouble(String columnLabel){
        if(isMissing(columnLabel))
            throw new RuntimeException("Field is empty");
        String data = get(columnLabel);
        return Double.parseDouble(data);
    }


    public static void main(String[] args) throws IOException {

//        CSVReader reader1 = new CSVReader("with-header.csv",";",true);
//        while(reader1.next()){
//            int id = reader1.getInt("id");
//            String name = reader1.get("imi\u0119");
//            String surname = reader1.get("nazwisko");
//            String street = reader1.get("ulica");
//            int num_house = reader1.getInt("nrdomu");
//            int num_flat = reader1.getInt("nrmieszkania");
//
//            System.out.printf(Locale.US,"%d %s %s %s %d %d",id, name, surname, street, num_house, num_flat);
//        }

        CSVReader reader2 = new CSVReader("with-header.csv",";",true);
        while(reader2.next()){
            int id = reader2.getInt(0);
            String name = reader2.get(1);
            String surname = reader2.get(2);
            String street = reader2.get(3);
            int num_house = reader2.getInt(4);
            int num_flat = reader2.getInt(5);
            System.out.printf(Locale.US,"%d %s %s %s %d %d\n",id, name, surname, street, num_house, num_flat);

        }
        reader2 = new CSVReader("no-header.csv",";",false);
        while(reader2.next()){
            int id = reader2.getInt(0);
            String name = reader2.get(1);
            String surname = reader2.get(2);
            String street = reader2.get(3);
            int num_house = reader2.getInt(4);
            int num_flat = reader2.getInt(5);
            System.out.printf(Locale.US,"%d %s %s %s %d %d\n",id, name, surname, street, num_house, num_flat);

        }
    }
}
