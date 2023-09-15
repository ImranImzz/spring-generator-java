package com.spring.generator.service;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.generator.domain.DomainGenerator;

import com.spring.generator.service.ControllerService;

@Service
public class GeneratorService {

    @Autowired
    DomainService domainService;

    @Autowired
    RepositoryService repositoryServiceService;

    @Autowired
    ServiceGenService serviceGenSevice;

    @Autowired
    ServiceImplService serviceImpl;

    @Autowired
    ControllerService controllerService;

    public File createJavaFile(DomainGenerator jsonModel) throws IOException {


        
        String entityClassName = jsonModel.getEntityName();
        String repoClassName = entityClassName+"Repository";
        String serviceClassname = entityClassName+"Service";
        String serviceImplClassName =  serviceClassname+"Impl";
        String controllerClassName = entityClassName+"RestController";

        String entityClass = domainService.generateJavaCode(jsonModel, entityClassName);
        String repositoryClass = repositoryServiceService.generateRepositoryCode(jsonModel, repoClassName);
        String serviceClass = serviceGenSevice.generateServiceCode(jsonModel, serviceClassname);
        String serviceImplClass = serviceImpl.generateServiceImplCode(jsonModel, serviceImplClassName, repoClassName, serviceClassname);
        String controllerClass = controllerService.generateControllerCode(jsonModel, controllerClassName);

        String entityfilePath = "generators/"+ entityClassName + ".java";
        String repositoryfilePath = "generators/"+ repoClassName + ".java";
        String servicefilePath = "generators/"+ serviceClassname + ".java";
        String serviceImplfilePath = "generators/"+ serviceImplClassName + ".java";
        String controllerfilePath = "generators/"+ controllerClassName+".java";
        
        File makeFolder = new File("generators");
        boolean isFolderCreated = makeFolder.mkdirs();
        if(isFolderCreated)
        Files.write(Paths.get(entityfilePath), entityClass.getBytes());
        Files.write(Paths.get(repositoryfilePath), repositoryClass.getBytes());
        Files.write(Paths.get(servicefilePath), serviceClass.getBytes());        
        Files.write(Paths.get(serviceImplfilePath), serviceImplClass.getBytes());
        Files.write(Paths.get(controllerfilePath), controllerClass.getBytes());
        return makeFolder;

    }
}
