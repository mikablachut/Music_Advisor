package org.music_advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Categories implements Request {
    private static final String CATEGORIES = "/v1/browse/categories";

    private static final Categories categories = new Categories();

    public static void getCategories() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = Request.getRequest(Main.API_SERVER_PATH + CATEGORIES);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            categories.getJsonObjectAsString(response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error response");
        }
    }

    @Override
    public void getJsonObjectAsString(HttpResponse<String> response) {
        List<String> listOfCategories = new ArrayList<>();
        String responseInJson = response.body();
        JsonObject jsonObject = JsonParser.parseString(responseInJson).getAsJsonObject();
        JsonObject playlists = jsonObject.getAsJsonObject("categories");

        for (JsonElement item : playlists.getAsJsonArray("items")) {
            listOfCategories.add(item.getAsJsonObject().get("name").toString().replace("\"", ""));
        }
        for (String category : listOfCategories) {
            System.out.println(category);
        }
    }
}
