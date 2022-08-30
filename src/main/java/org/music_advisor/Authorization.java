package org.music_advisor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class Authorization {
    public static String REDIRECT_URI = "http://localhost:8080";
    public static String CLIENT_ID = "0d24c09ed0a24bd8b1158f94bce26278"; //spotify client_id
    public static String CLIENT_SECRET = "aba1b82fee54406d82183ec893ad4aff";//spotify client_secret
    public static String ACCESS_TOKEN = "";
    public static String ACCESS_CODE = "";

    public boolean getAccessCode() {
        System.out.println("use this link to request the access code:");
        String link = Main.SERVER_PATH + "/authorize?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI +
                      "&response_type=code";
        System.out.println(link);

        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);

            server.createContext("/",
                    exchange -> {
                        String query = exchange.getRequestURI().getQuery();
                        String request;
                        if (query != null && query.contains("code")) {
                            ACCESS_CODE = query.substring(5);
                            request = "Got the code. Return back to your program.";
                        } else {
                            request = "Authorization code not found. Try again.";
                        }
                        exchange.sendResponseHeaders(200, request.length());
                        exchange.getResponseBody().write(request.getBytes());
                        exchange.getResponseBody().close();
                    }
            );
            server.start();
            System.out.println("waiting for code...");
            while (ACCESS_CODE.length() == 0) {
                Thread.sleep(10);
            }
            server.stop(5);
            System.out.println("code received");
            return true;
        } catch (IOException | InterruptedException e) {
            System.out.println("Server error");
            return false;
        }
    }

    public boolean getAccessToken() {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", "Basic " +
                        Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes()))
                .uri(URI.create(Main.SERVER_PATH + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code" + "&code=" + ACCESS_CODE
                        + "&redirect_uri=" + REDIRECT_URI))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response != null) {
                ACCESS_TOKEN = response.body();
                System.out.println(ACCESS_TOKEN);
                JsonObject token = JsonParser.parseString(ACCESS_TOKEN).getAsJsonObject();
                ACCESS_TOKEN = token.get("access_token").getAsString();
                System.out.println("---SUCCESS---");
                return true;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error response");
        }
        return false;
    }
}

