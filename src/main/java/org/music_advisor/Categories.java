package org.music_advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

public class Categories implements RequestAndResponse {
    private static final String CATEGORIES = "/v1/browse/categories";

    private static final Categories categories = new Categories();

    public static void getCategories() {
        String response = RequestAndResponse.getRequest(Main.API_SERVER_PATH + CATEGORIES);
        categories.getJsonObjectAsString(response);
    }

    @Override
    public void getJsonObjectAsString(String response) {
        List<String> listOfCategories = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject playlists = jsonObject.getAsJsonObject("categories");

        for (JsonElement item : playlists.getAsJsonArray("items")) {
            listOfCategories.add(item.getAsJsonObject().get("name").toString().replace("\"", ""));
        }
        for (String category : listOfCategories) {
            System.out.println(category);
        }
    }
}
