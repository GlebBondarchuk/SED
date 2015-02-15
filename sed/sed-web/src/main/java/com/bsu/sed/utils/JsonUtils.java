package com.bsu.sed.utils;

import com.bsu.sed.model.persistent.SystemAttribute;
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
    public static String usersToJson(List<User> users) {
        JsonArray jsonArray = new JsonArray();
        for(User user : users) {
            JsonObject json = new JsonObject();
            json.addProperty("id", user.getId());
            json.addProperty("name", user.getName());
            json.addProperty("role", user.getRole().name());
            json.addProperty("login", user.getLogin());
            json.addProperty("email", user.getEmail());
            json.addProperty("disabled", user.isDisabled());
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }

    /**
     * Convert list of SystemAttributes to Json string.
     *
     * @param systemAttributes List of systemAttributes.
     * @return String as json.
     */
    public static String systemAttributesToJson(List<SystemAttribute> systemAttributes) {
        JsonArray jsonArray = new JsonArray();
        for(SystemAttribute systemAttribute : systemAttributes) {
            JsonObject json = new JsonObject();
            json.addProperty("displayName", systemAttribute.getDisplayName());
            json.addProperty("value", systemAttribute.getValue());
            json.addProperty("description", systemAttribute.getDescription());
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }
}
