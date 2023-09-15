package com.spring.generator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.generator.domain.DomainGenerator;
import com.spring.generator.domain.VariableEntry;

@Service
public class ControllerService {

    public String generateControllerCode(DomainGenerator json, String className) {
        String entityName = json.getEntityName();
        String repositoryName = json.getEntityName().toLowerCase()+"Repository";
        String variableClassName = json.getEntityName().toLowerCase();

        StringBuilder javaCode = new StringBuilder("package com.dunamis.surveyEngine.web.rest;").append("\n");

        // Import Begin
        javaCode.append("import com.dunamis.surveyEngine.domain.*;").append("\n");
        javaCode.append("import com.dunamis.surveyEngine.domain.*;").append("\n");
        javaCode.append("import com.dunamis.surveyEngine.persistence.*;").append("\n");
        javaCode.append("import com.dunamis.surveyEngine.service.*;").append("\n");
        javaCode.append("").append("\n");
        javaCode.append("import java.io.IOException;").append("\n");
        javaCode.append("import java.util.List;").append("\n");
        javaCode.append("import javax.servlet.http.HttpServletRequest;").append("\n");
        javaCode.append("import org.springframework.beans.factory.annotation.Autowired;").append("\n");
        javaCode.append("import org.springframework.stereotype.Controller;").append("\n");
        javaCode.append("import org.springframework.web.bind.WebDataBinder;").append("\n");
        javaCode.append("import org.springframework.web.bind.annotation.InitBinder;").append("\n");
        javaCode.append("import org.springframework.web.bind.annotation.PathVariable;").append("\n");
        javaCode.append("import org.springframework.web.bind.annotation.RequestAttribute;").append("\n");
        javaCode.append("import org.springframework.web.bind.annotation.RequestBody;").append("\n");
        javaCode.append("import org.springframework.web.bind.annotation.RequestMapping;").append("\n");
        javaCode.append("import org.springframework.data.domain.PageRequest;").append("\n");
        javaCode.append("import org.springframework.web.bind.annotation.RequestMethod;").append("\n");
        javaCode.append("import org.springframework.web.bind.annotation.RequestParam;").append("\n");
        javaCode.append("import org.springframework.web.bind.annotation.RequestPart;").append("\n");
        javaCode.append("import org.springframework.web.bind.annotation.ResponseBody;").append("\n");
        javaCode.append("import org.springframework.web.multipart.MultipartFile;").append("\n");
        javaCode.append("import org.springframework.data.domain.Page;").append("\n");
        javaCode.append("import org.springframework.data.domain.Pageable;").append("\n");
        javaCode.append("import org.springframework.data.domain.Sort;").append("\n");
        javaCode.append("import org.springframework.data.domain.Sort.Direction;").append("\n");
        javaCode.append("import org.springframework.http.HttpStatus;").append("\n");
        javaCode.append("import org.springframework.http.MediaType;").append("\n");
        javaCode.append("import org.springframework.http.ResponseEntity;").append("\n");
        // Import End

        javaCode.append("").append("\n");

        // Controller Declaration Begin
        javaCode.append("@Controller(\"" + className+"\")").append("\n");
        javaCode.append("public class " + className +" {").append("\n");
        javaCode.append("").append("\n");

        // Field Declaration Start
        javaCode.append("    @Autowired").append("\n");
        javaCode.append("    private " + entityName + "Repository " + variableClassName + "Repository;").append("\n");
        javaCode.append("").append("\n");
        javaCode.append("    @Autowired").append("\n");
        javaCode.append("    private " + entityName + "Service " + variableClassName + "Service;").append("\n");
        // Field Declaration End

        // PUT Endpoint Begin
        javaCode.append("").append("\n");
        javaCode.append("    @RequestMapping(value = \"/" + entityName + "\", method = RequestMethod.PUT)").append("\n");
        javaCode.append("    @ResponseBody").append("\n");
        javaCode.append("    public " + entityName + " save" + entityName + "(@RequestBody " + entityName + " " + variableClassName + ") {").append("\n");
        javaCode.append("    " + variableClassName + "Service.save" + entityName + "(" + variableClassName + ");").append("\n");
        javaCode.append("        return " + repositoryName + ".findById(" + variableClassName + ".getId());").append("\n");
        javaCode.append("    }").append("\n");
        // PUT Endpoint End
        javaCode.append("").append("\n");

        // POST Endpoint Begin
        javaCode.append("    @RequestMapping(value = \"/" + entityName + "\", method = RequestMethod.POST)").append("\n");
        javaCode.append("    @ResponseBody").append("\n");
        javaCode.append("    public " + entityName + " new" + entityName + "(@RequestBody " + entityName + " " + variableClassName + ") {").append("\n");
        javaCode.append("    " + variableClassName + "Service.save" + entityName + "(" + variableClassName + ");").append("\n");
        javaCode.append("        return " + repositoryName + ".findById(" + variableClassName + ".getId());").append("\n");
        javaCode.append("    }").append("\n");
        // PUT Endpoint End
        javaCode.append("").append("\n");

        // GET ALL Endpoint Begin
        javaCode.append("    @RequestMapping(value = \"/" + entityName + "\", method = RequestMethod.GET)").append("\n");
        javaCode.append("    @ResponseBody").append("\n");
        javaCode.append("    public List<" + entityName + "> list" + entityName + "s() {").append("\n");
        javaCode.append("        return new java.util.ArrayList<" + entityName + ">(" + variableClassName + "Service.findAll());").append("\n");
        javaCode.append("    }").append("\n");
        // GET ALL Endpoint End
        javaCode.append("").append("\n");

        // GET ONE Endpoint Begin
        javaCode.append("    @RequestMapping(value = \"/" + entityName + "/{" + variableClassName + "_id}\", method = RequestMethod.GET)").append("\n");
        javaCode.append("    @ResponseBody").append("\n");
        javaCode.append("    public " + entityName + " load" + entityName + "(@PathVariable Integer " + variableClassName + "_id) {").append("\n");
        javaCode.append("        return " + variableClassName + "Service.findById(" + variableClassName + "_id);").append("\n");
        javaCode.append("    }").append("\n");
        // GET ONE Endpoint End
        javaCode.append("").append("\n");

        // DELETE Endpoint Begin
        javaCode.append("    @RequestMapping(value = \"/" + entityName + "/Delete/{" + variableClassName + "_id}\", method = RequestMethod.GET)").append("\n");
        javaCode.append("    @ResponseBody").append("\n");
        javaCode.append("    public Boolean delete" + entityName + "(@PathVariable Integer " + variableClassName + "_id) {").append("\n");
        javaCode.append("        return " + variableClassName + "Service.delete" + entityName + "(" + variableClassName + "_id);").append("\n");
        javaCode.append("    }").append("\n");
        // DELETE Endpoint End
        javaCode.append("").append("\n");


        javaCode.append("}").append("\n");
        // Controller Declaration End

        return javaCode.toString();
    }
}