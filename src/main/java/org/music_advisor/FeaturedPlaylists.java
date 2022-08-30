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

public class FeaturedPlaylists implements Request {

    private static final String FEATURED_PLAYLISTS = "/v1/browse/featured-playlists";
    private static final FeaturedPlaylists featuredPlaylists = new FeaturedPlaylists();

    public static void getFeaturedPlaylists() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = Request.getRequest(Main.API_SERVER_PATH + FEATURED_PLAYLISTS);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            featuredPlaylists.getJsonObjectAsString(response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error response");
        }
    }

    @Override
    public void getJsonObjectAsString(HttpResponse<String> response) {
        List<Playlist> listOfPlaylists = new ArrayList<>();
        String responseInJson = response.body();
        JsonObject jsonObject = JsonParser.parseString(responseInJson).getAsJsonObject();
        JsonObject playlists = jsonObject.getAsJsonObject("playlists");

        for (JsonElement item : playlists.getAsJsonArray("items")) {
            Playlist playlist = new Playlist();
            playlist.setName(item.getAsJsonObject().get("name").toString().replace("\"", ""));

            playlist.setLink(item.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").toString()
                    .replace("\"",""));
            listOfPlaylists.add(playlist);
        }
        for (Playlist playlist : listOfPlaylists) {
            System.out.println(playlist);
        }
    }
}
