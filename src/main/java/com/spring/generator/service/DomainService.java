package com.spring.generator.service;


import com.spring.generator.domain.DomainGenerator;
import com.spring.generator.domain.VariableEntry;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class DomainService {
    public void createJavaFile(DomainGenerator jsonModel) throws IOException {
        // Generate Java code from the JSON model// You can set a default class name or use one from JSON
        String javaCode = generateJavaCode(jsonModel, jsonModel.getEntityName());

        // Specify the directory where you want to save the Java file
        String filePath = jsonModel.getPath() +"/domain/"+ jsonModel.getEntityName() + ".java";

        // Write the Java code to the file
        Files.write(Paths.get(filePath), javaCode.getBytes());
    }

    public String generateJavaCode(DomainGenerator jsonModel, String className) {

        StringBuilder javaCode = new StringBuilder("package domain").append(";\n");
        javaCode.append("\n");
        javaCode.append("import java.time.LocalDateTime").append(";\n");
        javaCode.append("import javax.persistence.*").append(";\n");
        javaCode.append("@Entity").append("\n");
        javaCode.append("@Table").append("\n");
        javaCode.append("public class " + className + " {\n");

        
        if(jsonModel.isPrimaryKey()){
            javaCode.append("    @Id").append("\n");
            javaCode.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)").append("\n");
            javaCode.append("   @Column").append("\n");
            javaCode.append("   private Integer id").append(";\n");
        }

        for (VariableEntry variable : jsonModel.getVariables()) {
            if(variable.isNestedClass()){
                javaCode.append("    @").append(variable.getRelationShip()).append("\n");
                javaCode.append("    @JoinColumn").append("\n");
            }else{
            javaCode.append("    @Column").append("\n");
            }
            javaCode.append("    private ").append(variable.getVarType()).append(" ").append(variable.getVarName()).append(";\n");
            javaCode.append("\n");
        }
        // Generate constructor
        if(jsonModel.isConstructor()){
            javaCode.append("\n    public ").append(className).append("(");

            for (int i = 0; i < jsonModel.getVariables().size(); i++) {
                VariableEntry variable = jsonModel.getVariables().get(i);
                javaCode.append(variable.getVarType()).append(" ").append(variable.getVarName());

                if (i < jsonModel.getVariables().size() - 1) {
                    javaCode.append(", ");
                }
            }

            javaCode.append(") {\n");
            for (VariableEntry variable : jsonModel.getVariables()) {
                javaCode.append("        this.").append(variable.getVarName()).append(" = ").append(variable.getVarName()).append(";\n");
            }

            javaCode.append("    }\n");
        }
        if(jsonModel.isGetter() || jsonModel.isSetter()){
            if (jsonModel.isSetter()) {
                javaCode.append("\n    public void set").append("Id").append("(")
                        .append("Integer").append(" ").append("id").append(") {\n");
                javaCode.append("        this.").append("id").append(" = ").append("id").append(";\n");
                javaCode.append("    }\n");
            }
        if (jsonModel.isGetter()) {
                javaCode.append("\n    public ").append("Integer").append(" get").append("Id").append("() {\n");
                javaCode.append("        return ").append("id").append(";\n");
                javaCode.append("    }\n");
            }
        for (VariableEntry variable : jsonModel.getVariables()) {
                String capitalizedVarName = variable.getVarName().substring(0, 1).toUpperCase() + variable.getVarName().substring(1);
        if (jsonModel.isSetter()) {
                javaCode.append("\n    public void set").append(capitalizedVarName).append("(")
                        .append(variable.getVarType()).append(" ").append(variable.getVarName()).append(") {\n");
                javaCode.append("        this.").append(variable.getVarName()).append(" = ").append(variable.getVarName()).append(";\n");
                javaCode.append("    }\n");
            }
        if (jsonModel.isGetter()) {
                javaCode.append("\n    public ").append(variable.getVarType()).append(" get").append(capitalizedVarName).append("() {\n");
                javaCode.append("        return ").append(variable.getVarName()).append(";\n");
                javaCode.append("    }\n");
            }
        }
    }
        javaCode.append("}");

        return javaCode.toString();
    }
}