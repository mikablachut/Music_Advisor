package org.music_advisor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class NewReleases {
    private static final String NEW_RELEASES = "/v1/browse/new-releases";

    public void getNewReleases() {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + Authorization.ACCESS_TOKEN)
                .uri(URI.create(Main.API_SERVER_PATH + NEW_RELEASES))
                .GET()
                .build();



    }


}
