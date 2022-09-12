package org.music_advisor.view;

import org.music_advisor.controller.*;
import org.music_advisor.view.View;

import java.util.Scanner;

public class Main {
    private static final Authorization authorization = new Authorization();
    public static String SERVER_PATH = "";
    public static String API_SERVER_PATH = "";
    public static String PAGE = "";

    public static void main(String[] args) {
        getServerPath(args);
        View view = new View(Integer.parseInt(PAGE));
        menu(view);
    }

    public static void menu(View view) {
        Scanner scanner = new Scanner(System.in);
        boolean authGranted = false;

        while (true) {
            String input = scanner.nextLine();
            String option = input.split(" ")[0];
            switch (option) {
                case "auth":
                    if(authorization.getAccessCode() && authorization.getAccessToken()) {
                        authGranted = true;
                    }
                    break;
                case "new":
                    if (authGranted) {
                        view.displayObjectOnPage(NewReleases.getNewReleases());
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "featured":
                    if (authGranted) {
                        view.displayObjectOnPage(FeaturedPlaylists.getFeaturedPlaylists());
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "categories":
                    if (authGranted) {
                        view.displayObjectOnPage(Categories.getCategories());
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "playlists":
                    if (authGranted) {
                        String categoryName = input.split(" ",2)[1];
                        view.displayObjectOnPage(PlaylistsCategory.getPlaylistsByCategory(categoryName));
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "next":
                    view.nextPage();
                    break;
                case "prev":
                    view.previousPage();
                    break;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    return;
                default:
                    System.out.println("Command not exist!");
            }
        }
    }

    public static void getServerPath(String[] args) {
        if (args.length > 0 && args[0].equals("-access")) {
            SERVER_PATH = args[1];
        } else {
            SERVER_PATH = "https://accounts.spotify.com";
        }

        if (args.length > 0 && args[2].equals("-resource")) {
            API_SERVER_PATH = args[3];
        } else {
            API_SERVER_PATH = "https://api.spotify.com";
        }

        if (args.length > 0 && args[4].equals("-page")) {
            PAGE = args[5];
        } else {
            PAGE = "5";
        }
    }
}