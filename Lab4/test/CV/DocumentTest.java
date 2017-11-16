package CV;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DocumentTest {
    @org.junit.Test
    public void setTitle() throws Exception {
        Document cv = new Document();
        String exp="Jakis tytul";
        cv.setTitle(exp);
        assertEquals(cv.title,"Jakis tytul");
    }

    @org.junit.Test
    public void addPhoto() throws Exception {
        Document cv = new Document();
        String exp="http://www.dailydogmemes.com/cute-doge.jpg";
        cv.addPhoto(exp);
        assertEquals(cv.title,"Jakis tytul");
    }

    @org.junit.Test
    public void addSection() throws Exception {
    }

    @org.junit.Test
    public void addSection1() throws Exception {
    }

    @org.junit.Test
    public void writeHTML() throws Exception {
    }

    @org.junit.Test
    public void write() throws Exception {
    }

    @org.junit.Test
    public void read() throws Exception {
    }

}