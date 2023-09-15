package com.spring.generator.domain;

import java.util.List;

public class DomainGenerator {

    String path;
    boolean isPrimaryKey;
    String entityName;
    List<VariableEntry> variables;
    boolean constructor;
    boolean getter;
    boolean setter;

    public List<VariableEntry> getVariables() {
        return variables;
    }

    public String getEntityName() {
        return entityName;
    }

    public boolean isConstructor() {
        return constructor;
    }

    public void setConstructor(boolean constructor) {
        this.constructor = constructor;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setVariables(List<VariableEntry> variables) {
        this.variables = variables;
    }

    public boolean isGetter() {
        return getter;
    }

    public void setGetter(boolean getter) {
        this.getter = getter;
    }

    public boolean isSetter() {
        return setter;
    }

    public void setSetter(boolean setter) {
        this.setter = setter;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }
}
