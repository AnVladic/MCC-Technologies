package tmDat;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Map;
import java.util.TreeMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileWriter;


public class ReadXML {
    TreeMap<Integer,String> arr = new TreeMap<Integer,String>();
    String inputStream;
    String out;
    ReadXML(String inputStream, String out) throws ParserConfigurationException, SAXException, IOException, NullPointerException {
        this.inputStream = inputStream;

        this.out = out;
    }
    public void load() throws ParserConfigurationException, SAXException, IOException, NullPointerException, FileNotFoundException {
        int number;
        String name;

        try {
            Document doc;
            InputStream inputStream = new FileInputStream(this.inputStream);
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = parser.parse(inputStream);
            final NodeList topNodes = doc.getElementsByTagName("Param");
            for (int i = 0; i < topNodes.getLength(); i++) {
                final Node node = topNodes.item(i);
                if (!(node instanceof Element))
                    continue;
                final Element element = (Element)node;
                if ("Param".equals(element.getTagName())) {
                    number = Integer.parseInt(element.getAttribute("number"));
                    name = element.getAttribute("name");
                    arr.put(number, name);
                }
            }
            inputStream.close();
        }
        finally {
        }

    }
    void print() {
        try {
            FileWriter out =  new FileWriter(this.out);
            for(Map.Entry<Integer, String> item : arr.entrySet()) {
                out.write(item.getKey() + " " + item.getValue() + "\n");
            }
            out.close();
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
