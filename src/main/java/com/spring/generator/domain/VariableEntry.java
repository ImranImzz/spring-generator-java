package com.spring.generator.domain;

public class VariableEntry {

    private String varType;
    private String varName;
    private boolean nestedClass;
    private String relationShip;

    
    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }


    public VariableEntry(String dataTypes, String varName) {
        this.varType = dataTypes;
        this.varName = varName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public boolean isNestedClass() {
        return nestedClass;
    }

    public void setNestedClass(boolean nestedClass) {
        this.nestedClass = nestedClass;
    }
}
