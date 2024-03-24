package esercitazione5;

import esercitazione5.Node.ProgramOp;
import esercitazione5.visitor.CodeGenVisitor;
import esercitazione5.visitor.ScopeVisitor;
import esercitazione5.visitor.TypeVisitor;
import esercitazione5.visitor.XmlGenerator;
import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Tester {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("File name not found");
            System.exit(1);
        }

        String filePath = "Test" + File.separator + args[0];

        try {
            Reader reader = new FileReader(filePath);
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
           // parser.parse();

            ProgramOp program =  (ProgramOp) parser.parse().value;

            ScopeVisitor scopeVisitor = new ScopeVisitor();
            program.accept(scopeVisitor);

            TypeVisitor typeVisitor = new TypeVisitor();
            program.accept(typeVisitor);

            CodeGenVisitor codeGenVisitor = new CodeGenVisitor(args[0].substring(0,args[0].lastIndexOf(".")), typeVisitor);
            program.accept(codeGenVisitor);


/*
            XmlGenerator xml = new XmlGenerator();
            Document doc = (Document) program.accept(xml);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);

            StreamResult streamResult = new StreamResult(new File(System.getProperty("user.dir")+"\\albero_sintattico.xml"));
            transformer.transform(domSource, streamResult);
            System.out.println("Il File XML è stato generato correttamente.");

*/
        System.out.println("Compilazione Eseguita con successo");  //TODO togliere alla fine dei test

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

