import java.io.BufferedReader;
import java.io.FileReader;
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

    List<String> columnLabels = new ArrayList<>();
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(String filename){
        this.filename=filename;
    }

    public CSVReader(String filename,String delimiter){
        this(filename);
        this.delimiter=delimiter;
    }



    public CSVReader(String filename,String delimiter,boolean hasHeader) {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader) parseHeader();
    }

    boolean next(){

        String line = reader.readLine();
        if (line == null) {
            return false;
        }
        current = line.split(delimiter);
        return true;
    }

    void parseHeader() {
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        header = line.split(delimiter);
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(current[i]);
            columnLabelsToInt.put(current[i],i);
        }
    }
    //...
}
