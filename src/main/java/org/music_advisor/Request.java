package org.music_advisor;

import java.net.URI;
import java.net.http.HttpRequest;

public interface Request {
    static HttpRequest getRequest(String path) {
        return HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Authorization.ACCESS_TOKEN)
                .uri(URI.create(path))
                .GET()
                .build();
    }
}
