package CV;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class Document {
    @XmlElement(name="title")
    String title;
    @XmlElement(name="photo")
    Photo photo;
    @XmlElement(name="section")
    List<Section> sections = new ArrayList<>();

    Document(){}
    Document(String t)
    {
        title=t;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document addPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section new_sec = new Section(sectionTitle);
        sections.add(new_sec);
        return new_sec;
    }

    Document addSection(Section s){
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        out.printf("<?xml version=\"1.0\"?>\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<title>CV</title>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml;\n" +
                "charset=UTF-8\" />\n" +
                "</head>\n" +
                "<body>");
        out.printf("<h1>%s</h1>",title);
        photo.writeHTML(out);
        for(int i=0;i<sections.size();i++) {
            (sections.get(i)).writeHTML(out);
        }
        out.printf("</body>\n</html>");
    }

    public void write(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer= new FileWriter(fileName);;
            m.marshal(this, writer);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public static Document read(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Unmarshaller m = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);
            return (Document) m.unmarshal(reader);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Document cv = new Document("Maja Gruca - CV");
        cv.addPhoto("http://www.dailydogmemes.com/cute-doge.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Jezyki")
                            .addListItem("C")
                            .addListItem("C++")
                            .addListItem("Java")

                );
        //cv.writeHTML(System.out);
        try {
            cv.writeHTML(new PrintStream("cv.html","UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cv.write("cv.xml");
        Document cv2 = Document.read("cv.xml");
        try {
            cv2.writeHTML(new PrintStream("cv2.html","UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}


