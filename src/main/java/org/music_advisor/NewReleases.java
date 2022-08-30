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

public class NewReleases implements Request {
    private static final String NEW_RELEASES = "/v1/browse/new-releases";
    private static final NewReleases newReleases = new NewReleases();

    public static void getNewReleases() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = Request.getRequest(Main.API_SERVER_PATH + NEW_RELEASES);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            newReleases.getJsonObjectAsString(response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error response");
        }
    }

    @Override
    public void getJsonObjectAsString(HttpResponse<String> response) {
        List<Album> listOfAlbums = new ArrayList<>();
        String responseInJson = response.body();
        JsonObject jsonObject = JsonParser.parseString(responseInJson).getAsJsonObject();
        JsonObject albums = jsonObject.getAsJsonObject("albums");

        for (JsonElement item : albums.getAsJsonArray("items")) {
            Album album = new Album();
            album.setAlbumTitle(item.getAsJsonObject().get("name").toString().replace("\"", ""));

            for (JsonElement artist : item.getAsJsonObject().getAsJsonArray("artists")) {
                String[] arrayOfArtist;
                arrayOfArtist = new String[]{artist.getAsJsonObject().get("name").toString()
                        .replace("\"", "")};
                album.setListOfArtist(arrayOfArtist);
            }
            album.setLink(item.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").toString()
                          .replace("\"",""));
            listOfAlbums.add(album);
        }
        for (Album album : listOfAlbums) {
            System.out.println(album);
        }
    }
}
