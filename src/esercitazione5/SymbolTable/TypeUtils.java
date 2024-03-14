package esercitazione5.SymbolTable;
import java.util.HashMap;
import java.util.Objects;

public class TypeUtils {

    //HashMap con Key object che può avere 1 o 2 tipi di controllo.
    public static HashMap<Key, String> typeResult= new HashMap<>();

    public static void initializeMap() {
        //Binary operator

        String[] op1 = {"add", "diff", "mul"};
        for(String name : op1){
            typeResult.put(new Key(name,"int","int"), "int");
            typeResult.put(new Key(name,"int","float"), "float");
            typeResult.put(new Key(name,"float","float"), "float");
            typeResult.put(new Key(name,"float","int"), "float");
            typeResult.put(new Key(name,"string","string"), "string");
            typeResult.put(new Key(name,"int","string"), "string");
            typeResult.put(new Key(name,"string","int"), "string");
            typeResult.put(new Key(name,"float","string"), "string");
            typeResult.put(new Key(name,"string","float"), "string");
            typeResult.put(new Key(name,"Boolean","string"), "string");
            typeResult.put(new Key(name,"string","Boolean"), "string");
        }

        typeResult.put(new Key("div","int","float"), "float");
        typeResult.put(new Key("div","int","int"), "float");
        typeResult.put(new Key("div","float","float"), "float");
        typeResult.put(new Key("div","float","int"), "float");


        typeResult.put(new Key("or","Boolean","Boolean"), "Boolean");
        typeResult.put(new Key("and","Boolean","Boolean"), "Boolean");

        String[] op = {"gt", "ge", "lt", "le","eq","ne"};
        for(String name : op){
            typeResult.put(new Key(name,"int","int"), "Boolean");
            typeResult.put(new Key(name,"float","int"), "Boolean");
            typeResult.put(new Key(name,"int","float"), "Boolean");
            typeResult.put(new Key(name,"float","float"), "Boolean");
        }

        typeResult.put(new Key("eq","Boolean","Boolean"), "Boolean");
        typeResult.put(new Key("ne","Boolean","Boolean"), "Boolean");
        typeResult.put(new Key("eq","string","string"), "Boolean");
        typeResult.put(new Key("ne","string","string"), "Boolean");


        //Unary operator
        typeResult.put(new Key("uminus","int"), "int");
        typeResult.put(new Key("uminus","float"), "float");
        typeResult.put(new Key("not","Boolean"), "Boolean");
    }

    public static String getType(String op, String type1, String type2){
        return typeResult.get(new Key(op,type1,type2));
    }
    public static String getType(String op, String type1){
        return typeResult.get(new Key(op,type1));
    }

    public static class Key{

        private String op;
        private String type1;
        private String type2;

        //op binarie
        public Key(String op, String type1, String type2){
            this.op = op;
            this.type1 = type1;
            this.type2 = type2;
        }

        // op unarie
        public Key(String op , String type1){
            this.op = op;
            this.type1 = type1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(op, key.op) && Objects.equals(type1, key.type1) && Objects.equals(type2, key.type2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(op, type1, type2);
        }

    }

}