# TOY2 COMPILER
Compilatore Front-end del linguaggio Toy2 per il corso di Compilatori 2023/2024 presso l'Università di Salerno.

[![pipeline status](https://gitlab.com/g4660/compilatori_2023_24/toy2-semancgen-es5/imparato-carangelo_es5/badges/master/pipeline.svg)](https://gitlab.com/g4660/compilatori_2023_24/toy2-semancgen-es5/imparato-carangelo_es5/-/commits/master)



# GRAMMATICA


    Program ::= Iter1:iter1 Procedure:procedure Iter2:iter2
    {: LinkedList<VarDeclOp> varDecls = iter1.getVarDecls();
    varDecls.addAll(iter2.getVarDecls());
    LinkedList<FunOp> func = iter1.getFunctions();
    func.addAll(iter2.getFunctions());
    LinkedList<ProcedureOp> proc = iter2.getProcedures();
    proc.add(procedure);
    RESULT = new ProgramOp(varDecls, func, proc);
    :};

/*---------------------------------------------------------------------------------*/

    Iter1 ::= VarDecl:var Iter1:iter
    {: LinkedList<VarDeclOp>  varDecl = iter.getVarDecls();
    varDecl.addAll(var);
    RESULT = iter;:}



      | Function:func Iter1:iter
                    {: LinkedList<FunOp>  function = iter.getFunctions();
                        function.add(func);
                        RESULT = iter;:}

      | /* empty */ {: LinkedList<VarDeclOp> varDecl = new LinkedList<>();
                       LinkedList<FunOp> func = new LinkedList<>();
                       RESULT = new IterOp(varDecl, func);  :};

/*---------------------------------------------------------------------------------*/

    Iter2 ::= VarDecl:var Iter2:iter
    {: LinkedList<VarDeclOp>  varDecl = iter.getVarDecls();
    varDecl.addAll(var);
    RESULT = iter; :}

      | Function:func Iter2:iter
            {: LinkedList<FunOp>  function = iter.getFunctions();
               function.add(func);
               RESULT = iter;:}

      | Procedure:procedure Iter2:iter
            {: LinkedList<ProcedureOp> proc = iter.getProcedures();
               proc.add(procedure);
               RESULT = iter;:}
      | /* empty */ {: LinkedList<VarDeclOp> varDecl = new LinkedList<>();
                       LinkedList<FunOp> func = new LinkedList<>();
                       LinkedList<ProcedureOp> proc = new LinkedList<>();
                       RESULT = new IterOp(varDecl, func, proc);  :};

/*---------------------------------------------------------------------------------*/

    VarDecl ::= VAR Decls:decList {: RESULT = decList; :} ;

/*---------------------------------------------------------------------------------*/

    Decls ::= Ids:idList COLON Type:type SEMI Decls:decList
    {: decList.add(new VarDeclOp(idList,type));
    RESULT = decList;:}
    | Ids:idList ASSIGN Consts:constList SEMI Decls:decList
    {: decList.add(new VarDeclOp(idList,constList));
    RESULT = decList;:}
    | Ids:idList COLON Type:type SEMI ENDVAR
    {: LinkedList<VarDeclOp> decList = new LinkedList<> ();
    decList.add(new VarDeclOp(idList,type));
    RESULT = decList; :}
    | Ids:idList ASSIGN Consts:constList SEMI ENDVAR
    {: LinkedList<VarDeclOp> decList = new LinkedList<> ();
    decList.add(new VarDeclOp(idList,constList));
    RESULT = decList; :} ;

/*---------------------------------------------------------------------------------*/

    Ids ::= ID:id COMMA Ids:idList
    {: idList.add(new IdOp(id));
    RESULT = idList; :}

        | ID:id {: LinkedList<IdOp> idList = new LinkedList<>();
                idList.add(new IdOp(id));
                RESULT = idList; :};

/*---------------------------------------------------------------------------------*/

    Consts ::= Const:expr COMMA Consts:constList
    {: constList.add(expr);
    RESULT = constList; :}
    | Const:expr {:  LinkedList<Expr> constList = new LinkedList<>();
    constList.add(expr);
    RESULT = constList; :} ;

/*---------------------------------------------------------------------------------*/

    Const ::= REAL_CONST:lexeme     {: RESULT = new RealOp(lexeme); :}
    | INTEGER_CONST:lexeme      {: RESULT = new IntegerOp(lexeme); :}
    | STRING_CONST:lexeme       {: RESULT = new StringOp(lexeme); :}
    | TRUE                      {: RESULT = new TrueOp(); :}
    | FALSE                     {: RESULT = new FalseOp(); :}   ;

/*---------------------------------------------------------------------------------*/

    Type ::= REAL    {: RESULT = new TypeOp("float"); :}
    | INTEGER    {: RESULT = new TypeOp("int"); :}
    | STRING     {: RESULT = new TypeOp("string"); :}
    | BOOLEAN    {: RESULT = new TypeOp("Boolean"); :} ;

/*---------------------------------------------------------------------------------*/
    
    Function  ::= FUNCTION ID:id LPAR FuncParams:params RPAR TYPERETURN Types:type COLON Body:body ENDFUNCTION
    {: RESULT = new FunOp(new IdOp(id), params, type, body); :};

/*---------------------------------------------------------------------------------*/

    FuncParams ::= ID:id COLON Type:type OtherFuncParams:otherParams
    {: otherParams.add(new FuncParamsOp(new IdOp(id), type));
    RESULT = otherParams; :}
    | /* empty */  {: RESULT = new LinkedList<FuncParamsOp>(); :} ;

/*---------------------------------------------------------------------------------*/

    OtherFuncParams ::= COMMA ID:id COLON Type:type OtherFuncParams:otherParams
    {: otherParams.add(new FuncParamsOp(new IdOp(id), type));
    RESULT = otherParams;  :}
    | /* empty */ {:  RESULT = new LinkedList<FuncParamsOp>(); :};

/*---------------------------------------------------------------------------------*/

    Types ::= Type:type COMMA Types:typeList
    {: typeList.add(type);
    RESULT = typeList;  :}

	| Type:type {: LinkedList<TypeOp> typeList = new LinkedList<>();
	               typeList.add(type);
	               RESULT = typeList;  :} ;

/*---------------------------------------------------------------------------------*/

    Procedure ::= PROCEDURE ID:id LPAR ProcParams:params RPAR COLON Body:body ENDPROCEDURE
    {: RESULT = new ProcedureOp(new IdOp(id), params, body); :};

/*---------------------------------------------------------------------------------*/

    ProcParams::= ProcParamId:paramId COLON Type:type OtherProcParams:paramList
    {: paramList.add(new ProcParamsOp(paramId, type));
    RESULT = paramList;  :}
    | /* empty */ {:RESULT = new LinkedList<ProcParamsOp>(); :} ;

/*---------------------------------------------------------------------------------*/

    OtherProcParams ::= COMMA ProcParamId:paramId COLON Type:type OtherProcParams:paramList
    {: paramList.add(new ProcParamsOp(paramId, type));
    RESULT = paramList;  :}

	   | /* empty */ {: RESULT = new LinkedList<ProcParamsOp>(); :};

/*---------------------------------------------------------------------------------*/

    ProcParamId ::= ID:id {: RESULT = new ProcParamIdOp(new IdOp(id), false); :}
    | OUT ID:id {: RESULT = new ProcParamIdOp(new IdOp(id), true); :} ;

/*---------------------------------------------------------------------------------*/

    Body ::= VarDecl:varDecl Body:body  {: body.getVarDecl().addAll(varDecl);
    RESULT = body;  :}
    | Stat:stat Body:body {: body.getStmt().add(stat);
    RESULT = body;:}

	| /* empty */ {: LinkedList<VarDeclOp> varDecl = new LinkedList<> ();
	                 LinkedList<Statement> statement = new LinkedList<> ();
                     RESULT = new BodyOp(varDecl, statement); :} ;

/*---------------------------------------------------------------------------------*/

    Stat ::= Ids:ids ASSIGN Exprs:exprs SEMI   {: RESULT = new AssignStatOp(ids, exprs); :}
    | ProcCall:proCall SEMI               {: RESULT = proCall; :}
    | RETURN Exprs:exprs SEMI             {: RESULT = new ReturnStatOp(exprs); :}
    | WRITE IOArgs:args SEMI              {: RESULT = new WriteOp(false,args); :}
    | WRITERETURN IOArgs:args SEMI        {: RESULT = new WriteOp(true,args); :}
    | READ IOArgs:args SEMI               {: RESULT = new ReadOp(args); :}
    | IfStat:ifStat SEMI                  {: RESULT = ifStat; :}
    | WhileStat:whileStat SEMI            {: RESULT = whileStat; :} ;

/*---------------------------------------------------------------------------------*/

    FunCall ::= ID:id LPAR Exprs:exprs RPAR
    {: RESULT = new FunCallOp(new IdOp(id), exprs); :}      /* chiamata con almeno un parametro */
    | ID:id LPAR RPAR
    {: RESULT = new FunCallOp(new IdOp(id), new LinkedList<>()); :}   ;   /* chiamata con nessun parametro */

/*---------------------------------------------------------------------------------*/

    ProcCall ::= ID:id LPAR ProcExprs:exprs RPAR
    {: RESULT = new ProcCallOp(new IdOp(id), exprs); :}                   /* chiamata con almeno un parametro */
    | ID:id LPAR RPAR
    {:
    RESULT = new ProcCallOp(new IdOp(id), new ProcExprsOp(new LinkedList<Expr>()) ); :}   ;  /* chiamata con nessun parametro */

/*---------------------------------------------------------------------------------*/

    IfStat ::= IF Expr:expr THEN Body:body Elifs:elifs Else:elseOp ENDIF
    {: if(elifs == null && elseOp == null)
    RESULT = new IfStatOp(expr,body);
    else if(elifs != null && elseOp != null){
    RESULT = new IfStatOp(expr,body,elifs,elseOp);
    }
    else if(elifs != null && elseOp == null){
    RESULT = new IfStatOp(expr,body,elifs);
    }
    else
    RESULT = new IfStatOp(expr,body,elseOp);
    
                    :};

/*---------------------------------------------------------------------------------*/
        /* Elifs modificata */
    
    Elifs ::= Elif:elif Elifs:elifs     {:  if(elifs == null){
    elifs = new LinkedList<ElifOp>();}
    elifs.add(elif);
    RESULT = elifs;  :}
    
          | /* empty */     {: RESULT = null; :}	   ;

/*---------------------------------------------------------------------------------*/

    Elif ::= ELIF Expr:expr THEN Body:body    {: RESULT = new ElifOp(expr,body); :};

/*---------------------------------------------------------------------------------*/

    Else ::= ELSE Body:body  {: RESULT = body; :}
    | /* empty */       {: RESULT = null; :} ;

/*---------------------------------------------------------------------------------*/

    WhileStat ::= WHILE Expr:expr DO Body:body ENDWHILE {: RESULT = new WhileStatOp(expr, body); :} ;

/*---------------------------------------------------------------------------------*/
//In exp aggiungere un campo type( se exp normale oppure dollarsign)

    IOArgs ::= Expr:expr
    {: LinkedList<Expr> exprList = new LinkedList<>();
    exprList.add(expr);
    RESULT = exprList; :}
    | Expr:expr1 DOLLARSIGN LPAR Expr:expr2 RPAR IOArgs:list
    {: expr2.setDollar(true);
    list.add(expr2);
    list.add(expr1);
    RESULT = list; :}
    | DOLLARSIGN LPAR Expr:expr RPAR IOArgs:list
    {: expr.setDollar(true);
    list.add(expr);
    RESULT = list;:}
    | /* empty */  {: RESULT = new LinkedList<Expr>(); :} ;

/*---------------------------------------------------------------------------------*/

    ProcExprs::= Expr:expr COMMA ProcExprs:procExprs    {: procExprs.getExprs().add(expr);
    RESULT = procExprs; :}

	  | REF ID:id COMMA ProcExprs:procExprs  {: IdOp idNew = new IdOp(id);
	                                            idNew.setRef(true);
	                                            procExprs.getExprs().add(idNew);
	                                            RESULT = procExprs; :}

      | Expr:expr  {: LinkedList<Expr> exprs = new LinkedList<>();
                      exprs.add(expr);
                      RESULT = new ProcExprsOp(exprs); :}

	  | REF ID:id  {: LinkedList<Expr> refIds = new LinkedList<>();
                      IdOp newID = new IdOp(id);
                      newID.setRef(true);
	                  refIds.add(newID);
	                  RESULT = new ProcExprsOp(refIds); :}    ;

/*---------------------------------------------------------------------------------*/

    Exprs ::= Expr:expr COMMA Exprs:exprs  {: exprs.add(expr);
    RESULT = exprs; :}
    | Expr:expr {: LinkedList<Expr> exprList = new LinkedList<>();
    exprList.add(expr);
    RESULT = exprList;  :}  ;

/*---------------------------------------------------------------------------------*/
           


    Expr ::= FunCall:funcall         {: RESULT = funcall; :}
    | REAL_CONST:realConst       {: RESULT = new  RealOp(realConst); :}
    | INTEGER_CONST:intConst     {: RESULT = new IntegerOp(intConst); :}
    | STRING_CONST:stringConst    {: RESULT = new StringOp(stringConst); :}
    | ID:id                      {: RESULT = new IdOp(id); :}
    | TRUE                       {: RESULT = new TrueOp();  :}
    | FALSE                      {: RESULT = new FalseOp(); :}
    | Expr:e1  PLUS Expr:e2      {: RESULT = new AddOp(e1,e2); :}
    | Expr:e1  MINUS Expr:e2     {: RESULT = new DiffOp(e1,e2);:}
    | Expr:e1  TIMES Expr:e2     {: RESULT = new MulOp(e1,e2); :}
    | Expr:e1  DIV Expr:e2       {: RESULT = new DivOp(e1,e2); :}
    | Expr:e1  AND Expr:e2       {: RESULT = new AndOp(e1,e2); :}
    | Expr:e1  OR Expr:e2        {: RESULT = new OrOp(e1,e2); :}
    | Expr:e1  GT Expr:e2        {: RESULT = new GTOp(e1,e2); :}
    | Expr:e1  GE Expr:e2        {: RESULT = new GEOp(e1,e2); :}
    | Expr:e1  LT Expr:e2        {: RESULT = new LTOp(e1,e2); :}
    | Expr:e1  LE Expr:e2        {: RESULT = new LEOp(e1,e2); :}
    | Expr:e1  EQ Expr:e2        {: RESULT = new EQOp(e1,e2); :}
    | Expr:e1  NE Expr:e2        {: RESULT = new NEOp(e1,e2); :}
    | LPAR Expr:e1 RPAR          {: RESULT = new ParOp(e1); :}
    | MINUS Expr:e               {: RESULT = new UminusOp(e); :} %prec UMINUS
    | NOT Expr:e                 {: RESULT = new NotOp(e); :}   ;