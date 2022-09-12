package music_advisor.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import music_advisor.models.Album;
import music_advisor.view.App;
import java.util.ArrayList;
import java.util.List;

public class NewReleases implements RequestAndResponse<Album> {
    private static final String NEW_RELEASES = "/v1/browse/new-releases";
    private static final NewReleases newReleases = new NewReleases();

    public static String getNewReleases() {
        String response = RequestAndResponse.getRequest(App.API_SERVER_PATH + NEW_RELEASES);
        List<Album> listOfAlbums = newReleases.getJsonObjectAsString(response);
        StringBuilder result = new StringBuilder();
        for (Album album : listOfAlbums) {
            result.append(album.getAlbumTitle()).append("\n").append(album.getListOfArtist()).append("\n")
                    .append(album.getLink()).append("\n").append("\n");
        }
        return result.toString();
    }

    @Override
    public List<Album> getJsonObjectAsString(String response) {
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
        return listOfAlbums;
    }
}
