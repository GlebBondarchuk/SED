package com.bsu.sed.utils;

import com.bsu.sed.model.persistent.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author gbondarchuk
 */
public class JsonUtils {
    private JsonUtils() {
    }

    /**
     * Convert list of Users to Json string.
     *
     * @param users List of users.
     * @return String as json.
     */
    public static String toJson(List<User> users) {
        JsonArray jsonArray = new JsonArray();
        for(User user : users) {
            JsonObject json = new JsonObject();
            json.addProperty("id", user.getId());
            json.addProperty("name", user.getName());
            json.addProperty("role", user.getRole().name());
            json.addProperty("login", user.getLogin());
            json.addProperty("disabled", user.isDisabled());
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }
}
