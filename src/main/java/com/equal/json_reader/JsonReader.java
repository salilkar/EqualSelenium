package com.equal.json_reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonReader {
    public List<String> extractParameterValues(String fileLocation, String parameterName) {

        JSONParser parser = new JSONParser();
        List<String> result = new ArrayList<String>();

        try {
            Object obj = parser.parse(new FileReader(fileLocation));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray companyList = (JSONArray) jsonObject.get(parameterName);
            Iterator<String> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                result.add(iterator.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String extractParameterValue(String fileLocation, String parameterName) {

        JSONParser parser = new JSONParser();
        String result = null;

        try {
            Object obj = parser.parse(new FileReader(fileLocation));
            JSONObject jsonObject = (JSONObject) obj;
            result = (String) jsonObject.get(parameterName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}


