package org.music_advisor.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.music_advisor.model.Playlist;
import org.music_advisor.view.Main;

import java.util.ArrayList;
import java.util.List;

public class FeaturedPlaylists implements RequestAndResponse<Playlist> {

    private static final String FEATURED_PLAYLISTS = "/v1/browse/featured-playlists";
    private static final FeaturedPlaylists featuredPlaylists = new FeaturedPlaylists();

    public static String getFeaturedPlaylists() {

        String response = RequestAndResponse.getRequest(Main.API_SERVER_PATH + FEATURED_PLAYLISTS);
        List<Playlist> listOfFeaturedPlaylists = featuredPlaylists.getJsonObjectAsString(response);
        StringBuilder result = new StringBuilder();
        for (Playlist playlist : listOfFeaturedPlaylists) {
            result.append(playlist.getName()).append("\n").append(playlist.getLink()).append("\n").append("\n");
        }
        return result.toString();
    }
    @Override
    public List<Playlist> getJsonObjectAsString(String response) {
        List<Playlist> listOfPlaylists = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject playlists = jsonObject.getAsJsonObject("playlists");

        for (JsonElement item : playlists.getAsJsonArray("items")) {
            Playlist playlist = new Playlist();
            playlist.setName(item.getAsJsonObject().get("name").toString().replace("\"", ""));

            playlist.setLink(item.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").toString()
                    .replace("\"",""));
            listOfPlaylists.add(playlist);
        }
        return listOfPlaylists;
    }
}
