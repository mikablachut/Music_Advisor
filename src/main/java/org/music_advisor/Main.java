package org.music_advisor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean authGranted = false;
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "auth":
                    authorization();
                    authGranted = true;
                    break;
                case "new":
                    if (authGranted) {
                        newMusic();
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    break;
                case "featured":
                    if (authGranted) {
                        featuredMusic();
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

    public static void newMusic() {
        System.out.println("---NEW RELEASES---");
        System.out.println("""
                Mountains [Sia, Diplo, Labrinth]
                Runaway [Lil Peep]
                The Greatest Show [Panic! At The Disco]
                All Out Life [Slipknot]""");

    }

    public static void featuredMusic() {
        System.out.println("---FEATURED---");
        System.out.println("""
                Mellow Morning
                Wake Up and Smell the Coffee
                Monday Motivation
                Songs to Sing in the Shower""");
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

    public static void authorization() {
        String link = "https://accounts.spotify.com/authorize?client_id=0d24c09ed0a24bd8b1158f94bce26278&redirect_uri=" +
                      "http://localhost:8080&response_type=code\n" +
                      "---SUCCESS---";
        System.out.println(link);
    }
}