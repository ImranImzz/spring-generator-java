package com.spring.generator.service;


import org.springframework.stereotype.Service;

import com.spring.generator.domain.DomainGenerator;

@Service
public class ServiceGenService {

public String generateServiceCode(DomainGenerator json, String className) {
        StringBuilder javaCode = new StringBuilder("package service").append(";\n");
        javaCode.append("\n");
        javaCode.append("import java.util.List;\n");
        javaCode.append("\n");

        javaCode.append("public interface " + className + " {\n");
        if (json.isPrimaryKey()) {
            javaCode.append("   public "+json.getEntityName() + " findById(Integer id);\n");
        }
        javaCode.append("   public List<"+ json.getEntityName() +"> findAll();\n");
        javaCode.append("   public void save"+json.getEntityName() +"("+json.getEntityName()+ " "+json.getEntityName().toLowerCase() + ");\n");
        javaCode.append("   public boolean delete"+json.getEntityName() +"("+ json.getEntityName() +" "+ json.getEntityName().toLowerCase() +"Id);\n");
        javaCode.append("}");
        return javaCode.toString();
    }
    
}
