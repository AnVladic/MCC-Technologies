package tmDat;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class ReadConfig {
    static String inXml;
    static String outXml;
    static String inTmi;
    static String outTmi;

    public static TreeMap<Integer, String> arr = new TreeMap<Integer, String>();

    ReadConfig(String cFile) throws ParserConfigurationException, SAXException, IOException {
        Read(cFile);
    }

    private static void Read(String file) throws ParserConfigurationException, SAXException, IOException {

        InputStream in = new FileInputStream(file);
        Document doc;
        try {
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = parser.parse(in);
            final NodeList topNodes = doc.getElementsByTagName("xml");
            for (int i = 0; i < topNodes.getLength(); i++) {
                final Node node = topNodes.item(i);
                if (!(node instanceof Element))
                    continue;
                final Element element = (Element)node;
                if ("xml".equals(element.getTagName())) {
                    inXml = element.getAttribute("in");
                    outXml = element.getAttribute("out");
                }
            }

            final NodeList topNodes2 = doc.getElementsByTagName("tmi");
            for (int i = 0; i < topNodes2.getLength(); i++) {
                final Node node2 = topNodes2.item(i);
                if (!(node2 instanceof Element))
                    continue;
                final Element element2 = (Element)node2;
                if ("tmi".equals(element2.getTagName())) {
                    inTmi = element2.getAttribute("in");
                    outTmi = element2.getAttribute("out");
                }
            }
        }
        finally {
            in.close();
        }
    }

    public static void PrintNames(){
        System.out.println("Input TMI file name: "+inTmi);
        System.out.println("Input XML file name: "+inXml);
    }

    public static void main(String args[]) throws ParserConfigurationException, IOException, SAXException {
        Read("data/config.xml");
        PrintNames();
    }
}
