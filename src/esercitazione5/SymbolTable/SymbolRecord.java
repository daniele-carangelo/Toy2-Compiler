package esercitazione5.SymbolTable;

public class SymbolRecord {
    private String symbol;
    private String kind;
    private Boolean properties = false;
    private SymbolType symbolType;

    public SymbolRecord(String symbol, String kind, SymbolType symbolType){
        this.symbol = symbol;
        this.kind = kind;
        this.symbolType = symbolType;
    }
    public SymbolRecord(String symbol, String kind, SymbolType symbolType, Boolean properties){
        this.symbol = symbol;
        this.kind = kind;
        this.symbolType = symbolType;
        this.properties = properties;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public Boolean getProperties() {
        return properties;
    }

    public void setProperties(Boolean properties) {
        this.properties = properties;
    }

    public void setSymbolType(SymbolType symbolType) {
        this.symbolType = symbolType;
    }
}
