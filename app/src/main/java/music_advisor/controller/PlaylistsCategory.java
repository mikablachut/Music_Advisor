package music_advisor.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import music_advisor.models.Playlist;
import music_advisor.view.App;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsCategory implements RequestAndResponse<Playlist> {
    private static final String PLAYLISTS_CATEGORY = "/v1/browse/categories";
    private static final String PLAYLIST = "/v1/browse/categories/";
    private static final PlaylistsCategory playlistCategory = new PlaylistsCategory();

    public static String getPlaylistsByCategory(String categoryName) {
        List<Playlist> listOfPlaylists = new ArrayList<>();
        String response = RequestAndResponse.getRequest(App.API_SERVER_PATH + PLAYLISTS_CATEGORY);
        String idCategory = "Unknown category name.";
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject playlists = jsonObject.getAsJsonObject("categories");

        for (JsonElement item : playlists.getAsJsonArray("items")) {
            if (item.getAsJsonObject().get("name").toString().replaceAll("\"", "").equals(categoryName)) {
                idCategory = item.getAsJsonObject().get("id").toString().replaceAll("\"", "");
                break;
            }
        }

        if (idCategory.equals("Unknown category name.")) {
            System.out.println(idCategory);
        } else {
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .header("Authorization", "Bearer " + Authorization.ACCESS_TOKEN)
                    .uri(URI.create(App.API_SERVER_PATH + PLAYLIST + idCategory + "/playlists"))
                    .GET()
                    .build();

            try {
                HttpResponse<String> responseCategory = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                if (responseCategory.statusCode() > 200) {
                    JsonObject jsOb = JsonParser.parseString(response).getAsJsonObject();
                    JsonObject error = jsOb.getAsJsonObject("error");
                    System.out.println(error.get("message").getAsString());
                } else if (responseCategory.body().contains("Test unpredictable error message")) {
                    System.out.println("Test unpredictable error message");
                } else {
                    listOfPlaylists = playlistCategory.getJsonObjectAsString(responseCategory.body());
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Error response");
            }
        }

        StringBuilder result = new StringBuilder();
        for (Playlist playlist : listOfPlaylists) {
            result.append(playlist.getName()).append("\n").append(playlist.getLink()).append("\n").append("\n");
        }
        return result.toString();
    }

    @Override
    public List<Playlist> getJsonObjectAsString(String response) {
        List<Playlist> listOfCategories = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject playlists = jsonObject.getAsJsonObject("playlists");

        for (JsonElement item : playlists.getAsJsonArray("items")) {
            Playlist playlist = new Playlist();
            playlist.setName(item.getAsJsonObject().get("name").toString().replaceAll("\"",""));
            playlist.setLink(item.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").toString()
                    .replaceAll("\"", ""));
            listOfCategories.add(playlist);
        }
        return listOfCategories;
    }
}
