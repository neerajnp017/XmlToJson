package com.assignment.XML_to_JSON.Utility;

import com.assignment.XML_to_JSON.Exception.CustomException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class XmlToJsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static String convertXmlToJson(String xmlInput) {
        try {
            if (xmlInput == null || xmlInput.isBlank()) {
                throw new CustomException("Invalid XML Input: XML data is empty", "XML_EMPTY");
            }

            JsonNode rootNode = xmlMapper.readTree(xmlInput);
            int totalScore = calculateTotalMatchScore(rootNode);

            ObjectNode resultBlock = (ObjectNode) rootNode.path("ResultBlock");
            ObjectNode matchSummary = new ObjectNode(JsonNodeFactory.instance);
            matchSummary.put("TotalMatchScore", totalScore);

            resultBlock.set("MatchSummary", matchSummary);

            log.info("Successfully converted XML to JSON with TotalMatchScore: {}", totalScore);

            ObjectNode finalJson = objectMapper.createObjectNode();
            finalJson.set("Response", rootNode);

            return objectMapper.writeValueAsString(finalJson);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error converting XML to JSON: {}", e.getMessage(), e);
            throw new CustomException("XML to JSON conversion failed: " + e.getMessage(), "XML_PARSE_ERROR");
        }
    }

    private static int calculateTotalMatchScore(JsonNode rootNode) {
        int totalScore = 0;
        JsonNode matchArray = rootNode.path("ResultBlock").path("MatchDetails").path("Match");
        if (matchArray == null || matchArray.isMissingNode())
            throw new CustomException("Invalid XML structure", "400");

        if (matchArray.isArray()) {
            for (JsonNode matchNode : matchArray) {
                JsonNode scoreNode = matchNode.get("Score");
                if (scoreNode.isMissingNode()) {
                    throw new CustomException("Invalid XML structure: 'Score' tag is missing.", "MISSING_SCORE_TAG");
                }

                if (scoreNode != null) {
                    totalScore += scoreNode.asInt(0);
                }
            }
        }
        return totalScore;
    }
}

