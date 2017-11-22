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
        Photo act = new Photo(exp);
        assertEquals(cv.photo.url,"http://www.dailydogmemes.com/cute-doge.jpg");
        assertEquals(cv.photo.url,act.url);
    }

    @org.junit.Test
    public void addSection() throws Exception {
        Document cv = new Document();
        String exp ="Sekcja 1";
        cv.addSection(exp);
        Section act = new Section(exp);
        assertEquals(cv.sections.get(0).title,exp);
        assertEquals(cv.sections.get(0).title,act.title);
    }

    @org.junit.Test
    public void addSection1() throws Exception {
        Document cv = new Document();
        String title="Sekcja 2";
        Section exp = new Section(title);
        cv.addSection(exp);
        assertEquals(cv.sections.get(0).title,title);
        assertEquals(cv.sections.get(0),exp);
    }

}