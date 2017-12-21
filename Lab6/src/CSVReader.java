import java.io.*;
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
        if(columnIndex>=current.length || current[columnIndex].isEmpty())
            return true;
        else
            return false;
    }

    boolean isMissing(String columnLabel){
        if(!columnLabelsToInt.containsKey(columnLabel))
            return true;
        return isMissing(columnLabelsToInt.get(columnLabel));
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

        AdminUnitList list = new AdminUnitList();
        list.read("admin-units.csv");
        PrintStream out = System.out;
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);
        query.execute().list(out);
    }
}
