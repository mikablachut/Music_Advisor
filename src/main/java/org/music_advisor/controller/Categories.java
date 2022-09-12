package org.music_advisor.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.music_advisor.view.Main;

import java.util.ArrayList;
import java.util.List;

public class Categories implements RequestAndResponse<String> {
    private static final String CATEGORIES = "/v1/browse/categories";

    private static final Categories categories = new Categories();

    public static String getCategories() {
        String response = RequestAndResponse.getRequest(Main.API_SERVER_PATH + CATEGORIES);
        List<String> listOfCategories = categories.getJsonObjectAsString(response);
        StringBuilder result = new StringBuilder();
        for (String category : listOfCategories) {
            result.append(category).append("\n").append("\n");
        }
        return result.toString();
    }

    @Override
    public List<String> getJsonObjectAsString(String response) {
        List<String> listOfCategories = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject playlists = jsonObject.getAsJsonObject("categories");

        for (JsonElement item : playlists.getAsJsonArray("items")) {
            listOfCategories.add(item.getAsJsonObject().get("name").toString().replace("\"", ""));
        }
        return listOfCategories;
    }
}
