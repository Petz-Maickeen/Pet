package br.com.petz.pet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

public class JsonUtil {
    public static String toJson ( Object obj) throws JsonProcessingException {
        return new Gson().toJson(obj);
    }
}
