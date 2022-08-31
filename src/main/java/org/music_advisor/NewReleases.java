package org.music_advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

public class NewReleases implements RequestAndResponse {
    private static final String NEW_RELEASES = "/v1/browse/new-releases";
    private static final NewReleases newReleases = new NewReleases();

    public static void getNewReleases() {
        String response = RequestAndResponse.getRequest(Main.API_SERVER_PATH + NEW_RELEASES);
        newReleases.getJsonObjectAsString(response);
    }

    @Override
    public void getJsonObjectAsString(String response) {
        List<Album> listOfAlbums = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject albums = jsonObject.getAsJsonObject("albums");

        for (JsonElement item : albums.getAsJsonArray("items")) {
            Album album = new Album();
            album.setAlbumTitle(item.getAsJsonObject().get("name").toString().replace("\"", ""));
            StringBuilder artists = new StringBuilder("[");

            for (JsonElement artist : item.getAsJsonObject().getAsJsonArray("artists")) {
                if (!artists.toString().endsWith("[")) {
                    artists.append(", ");
                }
                artists.append(artist.getAsJsonObject().get("name"));
            }
            album.setListOfArtist(artists.append("]").toString().replaceAll("\"", ""));
            album.setLink(item.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").toString()
                          .replace("\"",""));
            listOfAlbums.add(album);
        }
        for (Album album : listOfAlbums) {
            System.out.println(album);
        }
    }
}
