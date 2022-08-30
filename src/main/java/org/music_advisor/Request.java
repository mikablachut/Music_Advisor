package org.music_advisor;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface Request {
    static HttpRequest getRequest(String path) {
        return HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Authorization.ACCESS_TOKEN)
                .uri(URI.create(path))
                .GET()
                .build();
    }
    void getJsonObjectAsString(HttpResponse<String> response);
}
