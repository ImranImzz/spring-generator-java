package com.spring.generator.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.spring.generator.domain.DomainGenerator;

@Service
public class RepositoryService {
    public void createJavaFile(DomainGenerator jsonModel) throws IOException {

        String javaCode = generateRepositoryCode(jsonModel, jsonModel.getEntityName());

        String filePath = jsonModel.getPath() + "/persistance/" + jsonModel.getEntityName() + "Repository.java";

        Files.write(Paths.get(filePath), javaCode.getBytes());
    }

    public String generateRepositoryCode(DomainGenerator json, String className) {
        StringBuilder javaCode = new StringBuilder("package persistance").append(";\n");
        javaCode.append("\n");
        javaCode.append("import java.util.List;\n");
        javaCode.append("import org.springframework.data.jpa.repository.JpaRepository;\n");
        javaCode.append("import org.springframework.dao.DataAccessException;\n");
        javaCode.append("import org.springframework.stereotype.Repository;\n");
        javaCode.append("\n");

        javaCode.append("@Repository\n");
        javaCode.append("public interface " + className +" extends JpaRepository<" + json.getEntityName() + ", Integer>"
                + " {\n");
        if (json.isPrimaryKey()) {
            javaCode.append("   "+json.getEntityName() + " findById(Integer id);\n");
        }
        javaCode.append("   List<"+ json.getEntityName() +"> findAll();\n");
        javaCode.append("}");
        return javaCode.toString();
    }
}
