package esercitazione5.SymbolTable;

import esercitazione5.Node.ProcParamsOp;
import esercitazione5.Node.TypeOp;

import java.util.LinkedList;

public class SymbolType {

    private LinkedList<TypeOp> inputType;
    private LinkedList<TypeOp> outputType;

    //patch per Procedure
    private LinkedList<ProcParamsOp> paramsOps;

    public SymbolType(LinkedList<TypeOp> inputType, LinkedList<TypeOp> outputType ){
        this.inputType = inputType;
        this.outputType = outputType;
    }

    public SymbolType(LinkedList<TypeOp> Type ){
        this.outputType = Type;
        this.inputType = null;
    }

    public SymbolType(LinkedList<TypeOp> inputType,LinkedList<ProcParamsOp> params, int i){
        this.inputType = inputType;
        this.paramsOps = params;
    }

    public LinkedList<ProcParamsOp> getParamsOps() {
        return paramsOps;
    }

    public void setParamsOps(LinkedList<ProcParamsOp> paramsOps) {
        this.paramsOps = paramsOps;
    }

    public LinkedList<TypeOp> getInputType() {
        return inputType;
    }

    public void setInputType(LinkedList<TypeOp> inputType) {
        this.inputType = inputType;
    }

    public LinkedList<TypeOp> getOutputType() {
        return outputType;
    }

    public void setOutputType(LinkedList<TypeOp> outputType) {
        this.outputType = outputType;
    }
}