package esercitazione5;

import esercitazione5.Node.ProgramOp;
import esercitazione5.visitor.CodeGenVisitor;
import esercitazione5.visitor.ScopeVisitor;
import esercitazione5.visitor.TypeVisitor;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tester {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("File name not found");
            System.exit(1);
        }

       // String filePath = "test" + File.separator + args[0];
        Path path = Paths.get(args[0]);

        try {
            Reader reader = new FileReader(path.toString());
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
           // parser.parse();

            ProgramOp program =  (ProgramOp) parser.parse().value;

            ScopeVisitor scopeVisitor = new ScopeVisitor();
            program.accept(scopeVisitor);

            TypeVisitor typeVisitor = new TypeVisitor();
            program.accept(typeVisitor);

            String name = "";
            int separatorIndex = args[0].lastIndexOf("\\" );
            if(separatorIndex == -1)
                separatorIndex = args[0].lastIndexOf("/");


            int extensionIndex = args[0].lastIndexOf(".");
            if (separatorIndex != -1 && extensionIndex != -1 && separatorIndex < extensionIndex)
                name = args[0].substring(separatorIndex + 1, extensionIndex);


            CodeGenVisitor codeGenVisitor = new CodeGenVisitor(name, typeVisitor);
            program.accept(codeGenVisitor);



        //System.out.println("Compilazione Eseguita con successo");  //TODO togliere alla fine dei test

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

