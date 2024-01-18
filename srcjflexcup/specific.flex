package esercitazione4;
import java_cup.runtime.Symbol;
import java.io.EOFException;
/* User code */
%%

%class Lexer
%cupsym Sym
%cup
%line
%column



%{
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }

%}

/* Declaration */

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]
Identifier = [:jletter:] [:jletterdigit:]*
Integer = 0 | [1-9][0-9]*
Real = ((\+|-)?(0|[1-9]+)(\.[0-9]+))



%state STRING
%state COMMENT

/* Lexical rules */
%%
<YYINITIAL> {

"var"       { return symbol(ParserSym.VAR); }
":"         { return symbol(ParserSym.COLON); }
"^="        { return symbol(ParserSym.ASSIGN); }
";"         { return symbol(ParserSym.SEMI); }
","         { return symbol(ParserSym.COMMA); }
"true"      { return symbol(ParserSym.TRUE); }
"false"     { return symbol(ParserSym.FALSE); }
"real"      { return symbol(ParserSym.REAL); }
"integer"   { return symbol(ParserSym.INTEGER); }
"string"    { return symbol(ParserSym.STRING); }
"boolean"   { return symbol(ParserSym.BOOLEAN); }
"return"    { return symbol(ParserSym.RETURN); }
"func"      { return symbol(ParserSym.FUNCTION); }
"->"        { return symbol(ParserSym.TYPERETURN); }
"endfunc"   { return symbol(ParserSym.ENDFUNCTION); }
"("         { return symbol(ParserSym.LPAR); }
")"         { return symbol(ParserSym.RPAR); }
"proc"      { return symbol(ParserSym.PROCEDURE); }
"endproc"   { return symbol(ParserSym.ENDPROCEDURE); }
"out"       { return symbol(ParserSym.OUT); }
"-->"       { return symbol(ParserSym.WRITE); }
"-->!"      { return symbol(ParserSym.WRITERETURN); }
"$"         { return symbol(ParserSym.DOLLARSIGN); }
"<--"       { return symbol(ParserSym.READ); }
"if"        { return symbol(ParserSym.IF); }
"then"      { return symbol(ParserSym.THEN); }
"else"      { return symbol(ParserSym.ELSE); }
"endif"     { return symbol(ParserSym.ENDIF); }
"elseif"    { return symbol(ParserSym.ELIF); }
"while"     { return symbol(ParserSym.WHILE); }
"do"        { return symbol(ParserSym.DO); }
"endwhile"  { return symbol(ParserSym.ENDWHILE); }



"+"         { return symbol(ParserSym.PLUS); }
"-"         { return symbol(ParserSym.MINUS); }
"*"         { return symbol(ParserSym.TIMES); }
"/"         { return symbol(ParserSym.DIV); }
"="         { return symbol(ParserSym.EQ); }
"<>"        { return symbol(ParserSym.NE); }
"<"         { return symbol(ParserSym.LT); }
"<="        { return symbol(ParserSym.LE); }
">"         { return symbol(ParserSym.GT); }
">="        { return symbol(ParserSym.GE); }
"&&"        { return symbol(ParserSym.AND); }

"||"        { return symbol(ParserSym.OR); }
"!"         { return symbol(ParserSym.NOT); }

"\\"         { return symbol(ParserSym.ENDVAR); }

"@"         { return symbol(ParserSym.REF); }



\"          { string.setLength(0); yybegin(STRING); }
"%"         { yybegin(COMMENT); }




{WhiteSpace} { /* ignore */ }

{Identifier} { return symbol(ParserSym.ID, yytext()); }

{Integer}    {return symbol(ParserSym.INTEGER_CONST, Integer.parseInt(yytext())); }

{Real}       {return symbol(ParserSym.REAL_CONST, Float.parseFloat(yytext())); }


<<EOF>>      { return symbol(ParserSym.EOF); }


}


<COMMENT> {
    % { /* end comment */
        yybegin(YYINITIAL);
        /* ignore */
    }
    [^%] {
    /* ignore */
    }
    <<EOF>> {throw new EOFException("Commento non chiuso");}
}

<STRING> {
  \" {
    yybegin(YYINITIAL);
    return symbol(ParserSym.STRING_CONST, string.toString());
  }
  [^\r\n\"\\]+ { string.append( yytext() ); }
  \t { string.append('\t'); }
  \n { string.append('\n'); }
  \r { string.append('\r'); }
  \' { string.append('\''); }
  \\  { string.append('\\'); }
  <<EOF>> {throw new EOFException("Stringa costante non completata"); }
}





// Error fallback
 [^] { throw new RuntimeException("Error:(" + (yyline+1) + ":" + (yycolumn+1) + ") Simbolo non riconosciuto '"+yytext()+"'");}

<<EOF>>      { return symbol(ParserSym.EOF); }