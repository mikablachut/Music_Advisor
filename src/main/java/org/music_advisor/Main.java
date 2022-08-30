package org.music_advisor;

import java.util.Scanner;

public class Main {
    private static Authorization authorization = new Authorization();
    public static String SERVER_PATH = "";

    public static String API_SERVER_PATH = "";

    public static void main(String[] args) {
        getServerPath(args);
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean authGranted = false;
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "auth":
                    if(authorization.getAccessCode() && authorization.getAccessToken()) {
                        authGranted = true;
                    }
                    break;
                case "new":
                    if (authGranted) {
                        NewReleases.getNewReleases();
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "featured":
                    if (authGranted) {
                        FeaturedPlaylists.getFeaturedPlaylists();
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "categories":
                    if (authGranted) {
                        musicCategories();
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "playlists Mood":
                    if (authGranted) {
                        playlistsMood();
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    return;
                default:
                    System.out.println("Command not exist!");
            }
        }
    }

    public static void musicCategories() {
        System.out.println("---CATEGORIES---");
        System.out.println("""
                Top Lists
                Pop
                Mood
                Latin""");
    }

    public static void playlistsMood() {
        System.out.println("---MOOD PLAYLISTS---");
        System.out.println("""
                Walk Like A Badass
                Rage Beats
                Arab Mood Booster
                Sunday Stroll
                """);
    }

    public static void getServerPath(String[] args) {
        if(args.length > 0 && args[0].equals("-access") && args[2].equals("-resource")) {
            SERVER_PATH = args[1];
            API_SERVER_PATH = args[3];
        } else {
            SERVER_PATH = "https://accounts.spotify.com";
            API_SERVER_PATH = "https://api.spotify.com";
        }
    }
}