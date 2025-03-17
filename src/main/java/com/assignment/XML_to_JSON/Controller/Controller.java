package com.assignment.XML_to_JSON.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.XML_to_JSON.Service.XmlToJsonService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private XmlToJsonService xmlToJsonService;


    @PostMapping("/convert")
    public ResponseEntity<String> convertXmlToJson(@RequestBody String xmlInput) {
        String jsonOutput = xmlToJsonService.convertXmlToJson(xmlInput);
        return ResponseEntity.ok(jsonOutput);
    }
}
