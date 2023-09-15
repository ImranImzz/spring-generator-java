package com.spring.generator.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.generator.domain.DomainGenerator;
import com.spring.generator.domain.VariableEntry;

@Service
public class ServiceImplService {

    public String generateServiceImplCode(DomainGenerator json, String className, String repoName, String serviceName) {
        String repository = json.getEntityName().toLowerCase()+"Repository";
        StringBuilder javaCode = new StringBuilder("package service").append(";\n");
        javaCode.append("\n");
        javaCode.append("import java.util.List;\n");
        javaCode.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        javaCode.append("import org.springframework.stereotype.Service;\n");
        javaCode.append("import org.springframework.transaction.annotation.Transactional;\n");
        javaCode.append("\n");

        javaCode.append("@Service\n");
        javaCode.append("@Transactional\n");
        javaCode.append("public interface " + className + " implements " + serviceName + " {\n");
        javaCode.append("\n");
        javaCode.append("   @Autowired\n");
        javaCode.append("   private " + repoName +" "+repository+";\n");
        javaCode.append("\n");
        javaCode.append("   @Transactional\n");
        javaCode.append("   public " +json.getEntityName()+" findById(Integer id) {\n");
        javaCode.append("       return "+repository+".findById(id);\n");
        javaCode.append("   }\n");

        javaCode.append("   @Transactional\n");
        javaCode.append("   public List<" +json.getEntityName()+"> findAll() {\n");
        javaCode.append("       return "+repository+".findAll();\n");
        javaCode.append("   }\n");
        // save method
        javaCode.append("   @Transactional\n");
        javaCode.append("   public void save" +json.getEntityName()+"("+json.getEntityName()+" "+json.getEntityName().toLowerCase()+") {\n");
        javaCode.append("       "+json.getEntityName()+" existing"+json.getEntityName()+" = "+ repository+".findById("+json.getEntityName().toLowerCase()+".getId());\n");
        javaCode.append("       if(existing" + json.getEntityName() + " != null) {\n"); 
        javaCode.append("           if(existing" + json.getEntityName() + " != " + json.getEntityName().toLowerCase()+ ") {\n"); 
        javaCode.append("               existing"+json.getEntityName()+".setId("+json.getEntityName().toLowerCase()+".getId());\n");
        for(VariableEntry variable: json.getVariables()){
            String capitalizedVarName = variable.getVarName().substring(0, 1).toUpperCase()
                    + variable.getVarName().substring(1);
            javaCode.append("               existing"+json.getEntityName()+".set"+capitalizedVarName+"("+json.getEntityName().toLowerCase()+".get"+capitalizedVarName+"());\n");
        }
        javaCode.append("           }\n");
        javaCode.append("       "+json.getEntityName().toLowerCase()+" = "+ repository + ".save(existing"+json.getEntityName()+");\n");
        javaCode.append("       }else{\n");
        javaCode.append("       "+json.getEntityName().toLowerCase()+" = "+ repository + ".save("+json.getEntityName().toLowerCase()+");\n");
        javaCode.append("       }\n");
        javaCode.append("       "+repository+".flush();\n");
        javaCode.append("   }");
        javaCode.append("\n");
        // delete Method

        javaCode.append("   public boolean delete"+json.getEntityName()+"(Integer id) {\n");
        javaCode.append("       "+json.getEntityName()+ " "+json.getEntityName().toLowerCase()+" = "+repository+".findById(id);\n");
        javaCode.append("       if(" + json.getEntityName().toLowerCase() + " != null) {\n");
        javaCode.append("           "+repository+".delete(id);\n");
        javaCode.append("           return true;\n");
        javaCode.append("       }else {\n");
        javaCode.append("           return false;\n");
        javaCode.append("       }\n");
        javaCode.append("   }\n");

        javaCode.append("}");
        return javaCode.toString();
    }
}
