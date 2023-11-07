package com.kenzie.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CustomHttpClient {

    public static String returnStatement = "";
    public static HttpClient client = HttpClient.newHttpClient();


    //TODO: Write sendGET method that takes URL and returns response
    public static String sendGET(String URLString) {
        //** Start of GET request algorithm

        URI uri = URI.create(URLString);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200 || statusCode == 201 || statusCode == 202) {
                return httpResponse.body();
            } else {
//                System.out.println("HTTP RESPONSE FAILURE!");
                returnStatement = "HTTP RESPONSE FAILURE!";
                return returnStatement;
            }
        }
        catch (IOException | InterruptedException e) {
            returnStatement = "EXCEPTION CAUGHT! POSSIBLE CONNECTION ERROR. \n FROM: 'sendGET' -->> ***" + e.getMessage() + "***";
        }
        return returnStatement;
    }

    public static ClueDTO deserialize(String json) throws JsonProcessingException {

            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<ClueDTO> typeRefClueDTO = new TypeReference<>(){};

            return objectMapper.readValue(json, typeRefClueDTO);
    }
}