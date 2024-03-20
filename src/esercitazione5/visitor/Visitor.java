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

import java.io.IOException;

public interface Visitor {

    Object visit(FalseOp falseOp) ;
    Object visit(IntegerOp integerOp) ;
    Object visit(RealOp realOp) ;
    Object visit(StringOp stringOp) ;
    Object visit(TrueOp trueOp) ;
    Object visit(AddOp addOp) ;
    Object visit(Expr expr);
    Object visit(DiffOp diffOp) ;
    Object visit(DivOp divOp) ;
    Object visit(MulOp mulOp) ;
    Object visit(UminusOp uminusOp);
    Object visit(AndOp andOp) ;
    Object visit(EQOp eqOp) ;
    Object visit(GEOp geOp) ;
    Object visit(GTOp gtOp) ;
    Object visit(LEOp leOp) ;
    Object visit(LTOp ltOp) ;
    Object visit(NEOp neOp) ;
    Object visit(NotOp notOp) ;
    Object visit(OrOp orOp) ;
    Object visit(FunCallOp funCallOp) ;
    Object visit(IdOp idOp);
    Object visit(ParOp parOp) ;
    Object visit(BodyOp bodyOp) ;
    Object visit(FuncParamsOp funcParamsOp) ;
    Object visit(FunOp funOp) throws Exception;
    Object visit(IterOp iterOp) throws Exception ;
    Object visit(ProcedureOp procedureOp) ;
    Object visit(ProcParamsOp procParamsOp);
    Object visit(ProcParamIdOp procParamIdOp) ;
    Object visit(ProgramOp programOp) throws Exception;
    Object visit(TypeOp typeOp) ;
    Object visit(VarDeclOp varDeclOp);
    Object visit (AssignStatOp assignStatOp) ;
    Object visit(ElifOp elifOp) ;
    Object visit(IfStatOp ifStatOp);
    Object visit(Statement statement);
    Object visit(ProcCallOp procCallOp);
    Object visit(ProcExprsOp procExprsOp);
    Object visit(ReadOp readOp);
    Object visit(WhileStatOp whileStatOp) ;
    Object visit(ReturnStatOp returnStatOp);
    Object visit(WriteOp writeOp);
}
