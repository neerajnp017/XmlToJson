package com.assignment.XML_to_JSON.Service.impl;

import org.springframework.stereotype.Service;

import com.assignment.XML_to_JSON.Exception.CustomException;
import com.assignment.XML_to_JSON.Service.XmlToJsonService;
import com.assignment.XML_to_JSON.Utility.XmlToJsonUtil;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
// @AllArgsConstructor
@RequiredArgsConstructor
@Log4j2
public class XmlToJsonServiceImpl implements XmlToJsonService{

    @Override
    public String convertXmlToJson(String xmlInput) {
        try {
            log.info("Converting XML to JSON...");
            return XmlToJsonUtil.convertXmlToJson(xmlInput);
        } 
        catch(CustomException e){
            throw e;
        }
        catch (Exception e) {
            log.error("Error processing XML: {}", e.getMessage());
            throw new CustomException("Failed to process XML", "Failed to parse");
        }
    }
    

}
