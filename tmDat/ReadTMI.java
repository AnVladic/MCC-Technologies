package tmDat;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import  java.io.*;

public class ReadTMI {
    FileInputStream input_file;
    FileWriter output_file;

    ReadTMI(String name) throws IOException {
        input_file = new FileInputStream(name);
    }


    int run() throws IOException {
        byte[] buff = new byte[16];
        int count = 0, size = 0, type = 0, number = 0, len = 0;
        input_file.skip(32);
        while ((size = input_file.read(buff, 0, 16)) > 0) {
            number = ((buff[0] << 8) & 0xFF00) | (buff[1] & 0xFF);
            if (number == 0xFFFF) continue;
            count++;
            type = buff[7] & 0xF;
            if (type == 3) {
                len = ((buff[10] << 8) & 0xFF00) | (buff[11] & 0xFF);
                if (len > 4) {
                    input_file.skip(len - 4);
                }
            }
        }
        return count;
    }


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        ReadConfig.main(args);
        ReadTMI obj = new ReadTMI(ReadConfig.intmi);
        int count = obj.run();
        System.out.println("count = " + String.format("%x", count));
    }
};

