package esercitazione5.SymbolTable;

import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.Optional;

public class SymbolTable {

    private LinkedList<SymbolRecord> symbolRecords;
    private SymbolTable father;
    private String scope;


    public SymbolTable(){
        this.symbolRecords = new LinkedList<SymbolRecord>();
    }

    public Optional<SymbolRecord> lookup(String symbol) {
        SymbolTable temp = this;
        Optional<SymbolRecord> symTemp = Optional.ofNullable(null);
        while (temp != null) {
            symTemp = temp.symbolRecords.stream().filter(record -> record.getSymbol().equals(symbol)).findFirst();
            if(!symTemp.isEmpty())
                return symTemp;
            else
                temp = temp.father;
        }

        return symTemp;
    }

    public boolean probe(String symbol) {
        return this.symbolRecords.stream().anyMatch(record -> record.getSymbol().equals(symbol));
    }

    public void addRecord(SymbolRecord symbolRecord){

        if(probe(symbolRecord.getSymbol()))
            throw new RuntimeException("L'elemento: " + symbolRecord.getSymbol() + " è stato già dichiarato");
        else
            this.symbolRecords.add(symbolRecord);
    }

    public LinkedList<SymbolRecord> getSymbolRecords() {
        return symbolRecords;
    }

    public void setSymbolRecords(LinkedList<SymbolRecord> symbolRecords) {
        this.symbolRecords = symbolRecords;
    }

    public SymbolTable getFather() {
        return father;
    }

    public void setFather(SymbolTable father) {
        this.father = father;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
