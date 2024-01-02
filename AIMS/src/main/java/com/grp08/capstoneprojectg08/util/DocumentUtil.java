package com.grp08.capstoneprojectg08.util;

import org.bson.Document;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class DocumentUtil {
    public static Document fromUrlToMongoDocument(URL url) {
        try{
            URI uri = url.toURI();
            Document doc = new Document();
            String query = uri.getQuery();
            String[] params = query.split("&");
            Map<String, String> map = new HashMap<>();
            for(String param : params){
                String[] keyValue = param.split("=");
                map.put(keyValue[0], keyValue[1]);
            }
            for(Map.Entry<String, String> entry : map.entrySet()){
                doc.append(entry.getKey(), entry.getValue());
            }
            return doc;
        } catch (URISyntaxException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
