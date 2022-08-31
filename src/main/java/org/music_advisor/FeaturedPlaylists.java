package org.music_advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

public class FeaturedPlaylists implements RequestAndResponse {

    private static final String FEATURED_PLAYLISTS = "/v1/browse/featured-playlists";
    private static final FeaturedPlaylists featuredPlaylists = new FeaturedPlaylists();

    public static void getFeaturedPlaylists() {

        String response = RequestAndResponse.getRequest(Main.API_SERVER_PATH + FEATURED_PLAYLISTS);
        featuredPlaylists.getJsonObjectAsString(response);

    }

    @Override
    public void getJsonObjectAsString(String response) {
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
        for (Playlist playlist : listOfPlaylists) {
            System.out.println(playlist);
        }
    }
}
