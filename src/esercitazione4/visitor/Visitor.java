package esercitazione4.visitor;

import esercitazione4.Expression.ConstOP.*;
import esercitazione4.Expression.Expr;
import esercitazione4.Expression.FunCallOp;
import esercitazione4.Expression.IdOp;
import esercitazione4.Expression.Operation.*;
import esercitazione4.Expression.ParOp;
import esercitazione4.Expression.RelOp.*;
import esercitazione4.Node.*;
import esercitazione4.Statement.*;

public interface Visitor {

    Object visit(FalseOp falseOp);
    Object visit(IntegerOp integerOp);
    Object visit(RealOp realOp);
    Object visit(StringOp stringOp);
    Object visit(TrueOp trueOp);
    Object visit(AddOp addOp);
    Object visit(Expr expr);
    Object visit(DiffOp diffOp);
    Object visit(DivOp divOp);
    Object visit(MulOp mulOp);
    Object visit(UminusOp uminusOp);
    Object visit(AndOp andOp);
    Object visit(EQOp eqOp);
    Object visit(GEOp geOp);
    Object visit(GTOp gtOp);
    Object visit(LEOp leOp);
    Object visit(LTOp ltOp);
    Object visit(NEOp neOp);
    Object visit(NotOp notOp);
    Object visit(OrOp orOp);
    Object visit(FunCallOp funCallOp);
    Object visit(IdOp idOp);
    Object visit(ParOp parOp);
    Object visit(BodyOp bodyOp);
    Object visit(FuncParamsOp funcParamsOp);
    Object visit(FunOp funOp) throws Exception;
    Object visit(IterOp iterOp) throws Exception;
    Object visit(ProcedureOp procedureOp);
    Object visit(ProcParamsOp procParamsOp);
    Object visit(ProcParamIdOp procParamIdOp);
    Object visit(ProgramOp programOp) throws Exception;
    Object visit(TypeOp typeOp);
    Object visit(VarDeclOp varDeclOp);
    Object visit (AssignStatOp assignStatOp);
    Object visit(ElifOp elifOp);
    Object visit(IfStatOp ifStatOp);
    Object visit(Statement statement);
    Object visit(ProcCallOp procCallOp);
    Object visit(ProcExprsOp procExprsOp);
    Object visit(ReadOp readOp);
    Object visit(WhileStatOp whileStatOp);
    Object visit(ReturnStatOp returnStatOp);
    Object visit(WriteOp writeOp);
}
