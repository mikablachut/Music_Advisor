package org.music_advisor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "new" -> newMusic();
                case "featured" -> featuredMusic();
                case "categories" -> musicCategories();
                case "playlists Mood" -> playlistsMood();
                case "exit" -> {
                    System.out.println("---GOODBYE!---");
                    return;
                }
                default -> System.out.println("Command not exist!");
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
}