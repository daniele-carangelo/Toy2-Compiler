package esercitazione4;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Tester {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("File name not found");
            System.exit(1);
        }

        String filePath = "test_files" + File.separator + args[0];

        try {
            Reader reader = new FileReader(filePath);
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
            parser.parse();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

