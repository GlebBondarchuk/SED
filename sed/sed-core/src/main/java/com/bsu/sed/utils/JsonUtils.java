package com.bsu.sed.utils;

import com.bsu.sed.dto.LineChartStatisticsDto;
import com.bsu.sed.dto.PieChartStatisticsDto;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.persistent.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

/**
 * @author gbondarchuk
 */
public class JsonUtils {

    public static final String ROWS = "rows";
    public static final String TOTAL = "total";

    private JsonUtils() {
    }

    /**
     * Convert list of Users to Json string.
     *
     * @param users List of users.
     * @return String as json.
     */
    public static String usersToJson(List<User> users, long total) {
        JsonArray jsonArray = new JsonArray();
        for (User user : users) {
            JsonObject json = new JsonObject();
            json.addProperty("id", user.getId());
            json.addProperty("name", user.getName());
            json.addProperty("role", user.getRole().name());
            json.addProperty("login", user.getLogin());
            json.addProperty("email", user.getEmail());
            json.addProperty("disabled", user.isDisabled());
            jsonArray.add(json);
        }
        return valuesToJson(jsonArray, total);
    }

    /**
     * Convert list of SystemAttributes to Json string.
     *
     * @param systemAttributes List of systemAttributes.
     * @return String as json.
     */
    public static String systemAttributesToJson(List<SystemAttribute> systemAttributes) {
        JsonArray jsonArray = new JsonArray();
        for (SystemAttribute systemAttribute : systemAttributes) {
            JsonObject json = new JsonObject();
            json.addProperty("displayName", systemAttribute.getDisplayName());
            json.addProperty("value", systemAttribute.getValue());
            json.addProperty("description", systemAttribute.getDescription());
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }

    public static String documentsToJson(List<Document> documents, long total) {
        JsonArray jsonArray = new JsonArray();
        for (Document document : documents) {
            Role role = document.getRole();
            JsonObject json = new JsonObject();
            json.addProperty("id", document.getId());
            json.addProperty("name", document.getName());
            json.addProperty("role", role == null ? "" : role.toString());
            json.addProperty("path", document.getPath());
            json.addProperty("createdDate", document.getCreatedDate().toString());
            json.addProperty("contentType", document.getContentType());
            User creator = document.getCreator();
            json.addProperty("creator", creator == null ? "" : creator.getName());
            jsonArray.add(json);
        }
        return valuesToJson(jsonArray, total);
    }

    public static String contentsToJson(List<Content> contents, long total) {
        JsonArray jsonArray = new JsonArray();
        for (Content content : contents) {
            JsonObject json = new JsonObject();
            json.addProperty("id", content.getId());
            json.addProperty("name", content.getName());
            json.addProperty("updateDate", content.getUpdateDate().toString());
            json.addProperty("contentType", content.getContentType());
            jsonArray.add(json);
        }
        return valuesToJson(jsonArray, total);
    }

    public static String newsUrlToJson(List<NewsUrl> urls, long total) {
        JsonArray jsonArray = new JsonArray();
        for (NewsUrl url : urls) {
            JsonObject json = new JsonObject();
            json.addProperty("id", url.getId());
            json.addProperty("url", url.getUrl());
            json.addProperty("disabled", url.isDisabled());
            json.addProperty("image", url.getImageUrl());
            json.addProperty("searchWords", url.getSearchWords());
            json.addProperty("lastSearch", url.getLastSearch().toString());
            jsonArray.add(json);
        }
        return valuesToJson(jsonArray, total);
    }

    public static String newsToJson(List<News> values, long total) {
        JsonArray jsonArray = new JsonArray();
        for (News news : values) {
            JsonObject json = new JsonObject();
            json.addProperty("id", news.getId());
            User creator = news.getCreator();
            json.addProperty("creator", creator != null ? news.getCreator().getName() : "");
            json.addProperty("name", news.getContent().getName());
            json.addProperty("createdDate", news.getCreatedDate().toString());
            jsonArray.add(json);
        }
        return valuesToJson(jsonArray, total);
    }

    public static String textToJson(List<Text> values, long total) {
        JsonArray jsonArray = new JsonArray();
        for (Text text : values) {
            JsonObject json = new JsonObject();
            json.addProperty("id", text.getId());
            json.addProperty("text", text.getText());
            json.addProperty("key", text.getKey().toString());
            json.addProperty("updateDate", text.getUpdateDate().toString());
            jsonArray.add(json);
        }
        return valuesToJson(jsonArray, total);
    }

    private static String valuesToJson(JsonArray array, long total) {
        JsonObject root = new JsonObject();
        root.addProperty(TOTAL, total);
        root.add(ROWS, array);
        return root.toString();
    }

    public static String toLineChartStatisticsJson(List<LineChartStatisticsDto> values) {
        JsonArray jsonArray = new JsonArray();
        for (LineChartStatisticsDto dto : values) {
            JsonArray element = new JsonArray();
            element.add(new JsonPrimitive(dto.getDate().getTime()));
            element.add(new JsonPrimitive(dto.getAmount()));
            jsonArray.add(element);
        }
        return jsonArray.toString();
    }

    public static String toPieChartStatisticsJson(List<PieChartStatisticsDto> values) {
        JsonArray jsonArray = new JsonArray();
        for (PieChartStatisticsDto dto : values) {
            JsonObject json = new JsonObject();
            json.addProperty("label", dto.getCountry());
            json.addProperty("data", dto.getAmount());
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }
}
