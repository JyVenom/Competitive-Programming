import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class P15_4 {
    public static void main(String[] args) throws TransformerException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("Prob15.in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        StringBuilder b4 = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            b4.append(line);
        }

        Source xmlInput = new StreamSource(new StringReader("<?xml version=\\\"1.0\\\"?><catalog><book id=\\\"bk101\\\"><author>Gambardella, Matthew</author><title>XML Developers Guide</title><genre>Computer</genre><price>44.95</price><publish_date>2000-10-01</publish_date><description>An in-depth look at creating applications with XML.</description></book><book id=\\\"bk102\\\"><author>Ralls, Kim</author><title>Midnight Rain</title><genre>Fantasy</genre><price>5.95</price><publish_date>2000-12-16</publish_date><description>A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world.</description></book></catalog>"));
        StringWriter stringWriter = new StringWriter();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
        transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(xmlInput, new StreamResult(stringWriter));

        pw.println(stringWriter.toString().trim());
    }
}