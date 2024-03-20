package esercitazione5.visitor;

import esercitazione5.Expression.ConstOP.*;
import esercitazione5.Expression.Expr;
import esercitazione5.Expression.FunCallOp;
import esercitazione5.Expression.IdOp;
import esercitazione5.Expression.Operation.*;
import esercitazione5.Expression.ParOp;
import esercitazione5.Expression.RelOp.*;
import esercitazione5.Node.*;
import esercitazione5.Statement.*;
import esercitazione5.SymbolTable.SymbolTable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class CodeGenVisitor implements Visitor {
    private SymbolTable table;
    private FileWriter fileWriter;
    private String nameFile;

    public CodeGenVisitor(String nameFile){
        this.nameFile = nameFile;
    }

    @Override
    public Object visit(ProgramOp programOp) throws Exception {
        String path = "test_files" + File.separator + "c_out" + File.separator;

        table = programOp.getSymbolTable();


            if (!Files.exists(Path.of(path)))
                new File(path).mkdirs();

            File file = new File(path + File.separator + this.nameFile + ".c");

            file.createNewFile();

            fileWriter = new FileWriter(file);


            //TODO

        return null;
    }

    @Override
    public Object visit(IfStatOp ifStatOp) {
try {
    fileWriter.write("if ( ");
    ifStatOp.getExpr().accept(this);
    fileWriter.write(" )");
    fileWriter.write("\n");
    ifStatOp.getBody().accept(this);
    ifStatOp.getElifS().forEach(elifs -> elifs.accept(this));

    if (ifStatOp.getElseBody() != null) {
        fileWriter.write("else");
        fileWriter.write("\n");
        ifStatOp.getElseBody().accept(this);
    }

} catch (IOException e) {
    throw new RuntimeException(e);
}
        return null;
    }

    @Override
    public Object visit(ElifOp elifOp)  {

        try {
            fileWriter.write("else if (");

        elifOp.getExpr().accept(this);
        fileWriter.write(" )");
        elifOp.getBody().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(FalseOp falseOp)  {
        try {
            fileWriter.write("false");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(TrueOp trueOp) {
        try {
            fileWriter.write("true");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(IntegerOp integerOp) {
        try {
            fileWriter.write(integerOp.getAttribute());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(RealOp realOp)  {
        try {
            fileWriter.write("" + realOp.getAttribute());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(ParOp parOp)  {
        try {
            fileWriter.write("(");

        parOp.getExpr().accept(this);
        fileWriter.write(")");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(Expr expr) {
        return null;
    }

    @Override
    public Object visit(StringOp stringOp)  {
        try {
            fileWriter.write(stringOp.getAttribute());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(IdOp idOp)  {
        try {
            if (idOp.isRef())
                fileWriter.write("*");

            fileWriter.write(idOp.getName());
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(AddOp addOp)  {
        try {
        if(isString(addOp.getExpr1())  || isString(addOp.getExpr2())){
          //TODO  concatString(addOp);

        }
        addOp.getExpr1().accept(this);

            fileWriter.write("+");

        addOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(MulOp mulOp) {
        try {
        mulOp.getExpr1().accept(this);
        fileWriter.write("*");
        mulOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(DivOp divOp) {
        try {
        divOp.getExpr1().accept(this);
        fileWriter.write("/");
            divOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(DiffOp diffOp)  {
        try {
        diffOp.getExpr1().accept(this);
        fileWriter.write("-");
            diffOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(GTOp gtOp)  {
        try {
        gtOp.getExpr1().accept(this);
        fileWriter.write(">");

            gtOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(GEOp geOp) {
        try {
        geOp.getExpr1().accept(this);
        fileWriter.write(">=");
        geOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(LEOp leOp)  {
        try {
        leOp.getExpr1().accept(this);
        fileWriter.write("<=");
        leOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(LTOp ltOp)  {
        try {
        ltOp.getExpr1().accept(this);
        fileWriter.write("<");
        ltOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(OrOp orOp)  {
        try {
        orOp.getExpr1().accept(this);
        fileWriter.write("||");
        orOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(AndOp andOp)  {
        try {
        andOp.getExpr1().accept(this);
        fileWriter.write("&&");
        andOp.getExpr2().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(NotOp notOp)  {
        try {
            fileWriter.write("!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(UminusOp uminusOp) {
        try {
            fileWriter.write("-");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(EQOp eqOp) {
        try {
        if(isString(eqOp.getExpr1()) && isString(eqOp.getExpr2())){
            fileWriter.write("strncmp(");
            eqOp.getExpr1().accept(this);
            fileWriter.write(", ");
            eqOp.getExpr2().accept(this);
            fileWriter.write(") == 0");
        }
        else
            fileWriter.write("==");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(NEOp neOp) {
        try {
            if(isString(neOp.getExpr1()) && isString(neOp.getExpr2())){
                fileWriter.write("strncmp(");
                neOp.getExpr1().accept(this);
                fileWriter.write(", ");
                neOp.getExpr2().accept(this);
                fileWriter.write(") != 0");
            }
            else
                fileWriter.write("!=");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Object visit(FunCallOp funCallOp)  {
        try {
        funCallOp.getId().accept(this);
        fileWriter.write("(");

        if(funCallOp.getExprs().size() > 0) {
            for (int i=0;i < funCallOp.getExprs().size(); i++) {
                funCallOp.getExprs().get(i).accept(this);
                if((funCallOp.getExprs().size() - i) != 1)
                    fileWriter.write(",");
            }
        }

            fileWriter.write(")");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    @Override
    public Object visit(BodyOp bodyOp) {

        try {
            fileWriter.write("{");

        fileWriter.write("\n");

        if(!bodyOp.getStmt().isEmpty())
            bodyOp.getStmt().forEach(statement -> statement.accept(this));

        if(!bodyOp.getVarDecl().isEmpty())
            bodyOp.getVarDecl().forEach(vars -> vars.accept(this));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(FuncParamsOp funcParamsOp) {
        try {
        funcParamsOp.getType().accept(this);
        fileWriter.write(" ");
        funcParamsOp.getId().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(FunOp funOp) {
        try {

        //firma funzione
        if(funOp.getTypes().size() > 1){
            fileWriter.write("Struct ");
            funOp.getId().accept(this);
        }
        else{
            funOp.getTypes().get(0).accept(this);
            fileWriter.write(" ");
            funOp.getId().accept(this);
        }

        fileWriter.write("(");

            if(!funOp.getFuncParams().isEmpty()) {
                for (int i=0;i < funOp.getFuncParams().size(); i++) {
                    funOp.getFuncParams().get(i).accept(this);
                    if((funOp.getFuncParams().size() - i) != 1)
                        fileWriter.write(",");
                }
            }

            fileWriter.write(")");
            table = funOp.getSymbolTable();
            funOp.getBody().accept(this);
            table = funOp.getSymbolTable().getFather();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(IterOp iterOp)  {
        return null;
    }

    @Override
    public Object visit(ProcedureOp procedureOp)  {
        try {
        fileWriter.write("void ");
        procedureOp.getId().accept(this);
        fileWriter.write("(");

        table = procedureOp.getSymbolTable();

        if(!procedureOp.getParams().isEmpty()) {
            for (int i=0;i < procedureOp.getParams().size(); i++) {
                procedureOp.getParams().get(i).accept(this);
                if((procedureOp.getParams().size() - i) != 1)
                    fileWriter.write(",");
            }
        }

            fileWriter.write(")");

        procedureOp.getBody().accept(this);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        table = procedureOp.getSymbolTable().getFather();

        return null;
    }

    @Override
    public Object visit(TypeOp typeOp)  {
        try{
        switch (typeOp.getType()) {
            case "Boolean":
                fileWriter.write("bool");
                break;
            case "int":
                fileWriter.write("int");
                break;
            case "float":
                fileWriter.write("double");
                break;
            case "string":
                fileWriter.write("char*");
                break;
        }
        }
         catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(ProcParamsOp procParamsOp) {

        procParamsOp.getType().accept(this);
        procParamsOp.getParamId().accept(this);

        return null;
    }

    @Override
    public Object visit(ProcParamIdOp procParamIdOp)  {
        try {
            fileWriter.write(" ");

        if(procParamIdOp.getOut())
            fileWriter.write("*");

        procParamIdOp.getId().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object visit(VarDeclOp varDeclOp) {
        //TODO
        return null;
    }

    @Override
    public Object visit(AssignStatOp assignStatOp) {
        return null;
    }


    @Override
    public Object visit(ProcCallOp procCallOp) {

        try {
            procCallOp.getId().accept(this);
            fileWriter.write("(");
            LinkedList<Expr> exprs = procCallOp.getProcExprs().getExprs();

            if(exprs.size() > 0)
               for(int i = 0; i < exprs.size()-1;i++){
                   if(exprs.get(i) instanceof IdOp id && id.isRef())
                       fileWriter.write("&");

                   exprs.get(i).accept(this);
                   if((exprs.size() - i) != 1)
                       fileWriter.write(",");
                }

            fileWriter.write(");");
            fileWriter.write("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Object visit(ProcExprsOp procExprsOp) {
        return null;
    }

    @Override
    public Object visit(ReadOp readOp) {
        try {
            if(!readOp.getExprs().isEmpty())
                for(int i=0; i< readOp.getExprs().size() -1; i++){
                    Expr expr = readOp.getExprs().get(i);
                    if(expr.getDollar()){
                        fileWriter.write("scanf(\"");
                        String type = table.typeOfId((IdOp) expr).getInputType().get(0).getType();
                        if(type.equalsIgnoreCase("string"))
                            fileWriter.write("%s\",");
                        if(type.equalsIgnoreCase("int"))
                            fileWriter.write("%d\",");
                        if(type.equalsIgnoreCase("float"))
                            fileWriter.write("%f\",");

                    fileWriter.write("&");
                    expr.accept(this);
                    fileWriter.write(");");
                    }
                    else {
                        fileWriter.write("printf(\"");
                        expr.accept(this);
                        fileWriter.write("\n\");");
                    }
                }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    @Override
    public Object visit(Statement statement) {
        return null;
    }

    @Override
    public Object visit(WhileStatOp whileStatOp) {

        try {
            fileWriter.write("while(");
            whileStatOp.getExpr().accept(this);
            fileWriter.write(")");
            whileStatOp.getBody().accept(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Object visit(ReturnStatOp returnStatOp) {
        //TODO
        return null;
    }

    @Override
    public Object visit(WriteOp writeOp) {

        try {
            fileWriter.write("printf(\"");
            String type ;
            if(!writeOp.getIOArgs().isEmpty())
                for(Expr expr : writeOp.getIOArgs()){
                    if(expr.getDollar()) {
                        type = (String) expr.accept(new TypeVisitor());  //TODO passare il visitor nel costruttore

                        if (type.equalsIgnoreCase("string"))
                            fileWriter.write("%s");
                        if (type.equalsIgnoreCase("int") || type.equalsIgnoreCase("Boolean"))
                            fileWriter.write("%d");
                        if (type.equalsIgnoreCase("float"))
                            fileWriter.write("%f");
                    } else if (!expr.getDollar() && expr instanceof StringOp string) {
                            fileWriter.write( string.getAttribute());
                    }
                }
            //se writereturn
            if(writeOp.getType())
                fileWriter.write("\n\",");

            if(writeOp.getIOArgs().size() > 0)
                fileWriter.write(",");
                for (int i=0; i < writeOp.getIOArgs().size() -1; i++) {
                    if(writeOp.getIOArgs().get(i).getDollar())
                        writeOp.getIOArgs().get(i).accept(this);

                    if((writeOp.getIOArgs().size() - i) != 1)
                        fileWriter.write(",");
                }

                fileWriter.write("\");");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }




    private boolean isString(Expr expr){

        if(expr instanceof StringOp)
            return true;
        if(expr instanceof FunCallOp) {
            FunCallOp func = (FunCallOp) expr;
            String type = table.typeOfId(func.getId()).getOutputType().get(0).getType();
            if (type.equalsIgnoreCase("string"))
                return true;
        }
        if(expr instanceof IdOp){
            IdOp id = (IdOp) expr;
            String typeId = table.typeOfId(id).getOutputType().get(0).getType();
            if(typeId.equalsIgnoreCase("string"))
                return true;
        }

        return false;
    }
}
