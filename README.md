# Music Advisor
* [Description](#description)
* [Technologies](#technologies)
* [Setup](#setup)
* [Running the application in a console](#running-the-application-in-a-console)

## Description

<details>
<summary>Click here for general information about the <b>Project</b>!</summary>

Web application that uses the Spotify API. It allows the user to view newly released albums and playlists. It suggests playlists based on the music category selected by the user.

The idea for project comes from [JetBrains Academy](https://www.jetbrains.com/academy/) Java Beckend Developer track.

</details>

## Technologies

<ul>
  <li>Java 17</li>
  <li>GSON</li>
  <li>OAuth</li>
  <li>REST API</li>
  <li>Gradle</li>
  <li>JUnit</li>
</ul>

## Setup

Make sure you have [git](https://git-scm.com/) installed. 

### Gradle

<b>Java 17 is required for this step.</b>

Clone the repository and enter its folder:

```
git clone https://github.com/dominikablachut/Music_Advisor.git
cd Music_Advisor
```

To run the application you have to use Spotify client_id and client_secret. To obtained it, you need to go to  the [Spotify Web site for developers](https://developer.spotify.com/dashboard/login) and create the application. To create the app, you have to select Dashboard tab on the site, log into Spotify and click the Create an App button. Use the client_id and client_secret in the code:

```java
public class Authorization {
    public static String REDIRECT_URI = "http://localhost:8080";
    public static String CLIENT_ID = ""; //spotify client_id
    public static String CLIENT_SECRET = "";//spotify client_secret
    public static String ACCESS_TOKEN = "";
    public static String ACCESS_CODE = "";
```

Next add the http://localhost:8080 to the whitelist of redirect_uri setting in the application settings on the Spotify site (Dashboard -> your app -> edit settings -> redirect URIs).

Now you can run the app using Gradle:

```
gradle run
```

Application can also be started using ```java -jar``` command but for that, we need to generate jar file.  
Steps for this scenario:

```
gradle build
java -jar .\app\build\libs\app.jar
```

## Running the application in a console

To use the application, user have to confirm authorization via Spotify.

Once the program starts, user should enter the ```auth``` command. The application will display a link(user have to use it) to request the access code. After the user confirms the authorization, the server returns the following text to the browser:

```Got the code. Return back to your program.```

After reveiving the code, the aplication will send an http request to obtain access_token. If the request is successful, the user will be able to enter the following commands in the console:
- featured — displays a list of Spotify-featured playlists with their links fetched from API;
- new — displays a list of new albums with artists and links on Spotify;
- categories — displays a list of all available categories on Spotify (just their names);
- playlists C_NAME, where C_NAME is the name of category. The list contains playlists of this category and their links on Spotify;
- exit - shuts down the application.

The application displays 5 position per page. User can navigate through the pages using the commands ```next``` (see the next page) and ```prev``` (see the previous page).

<details>
<summary><b>Below is an example output of the described program.</b></summary>

The greater-than symbol followed by a space ```(> )``` represents the user input. Note that it's not part of the input.

```
> new
Please, provide access for application.
> auth
use this link to request the access code:
https://accounts.spotify.com/authorize?client_id=a19ee7dbfda443b2a8150c9101bfd645&redirect_uri=http://localhost:8080&response_type=code
waiting for code...
code received
Making http request for access_token...
Success!
> new
OT ALL HEROES WEAR CAPES
[Metro Boomin, Travis Scott, 21 Savage]
https://open.spotify.com/album/1zNr37qd3iZJ899byrTkcj

I Used To Know Her - Part 2 - EP
[H.E.R.]
https://open.spotify.com/album/46imFLgb9fR1Io6EoPYeQh

The Last Rocket
[Takeoff]
https://open.spotify.com/album/5XRCcUfwtLNQflDd9cfz4U

Interstate Gospel
[Pistol Annies]
https://open.spotify.com/album/0IXxmmlfSQxgJNWnNjHhgJ

El Mal Querer
[ROSALÍA]
https://open.spotify.com/album/355bjCHzRJztCzaG5Za4gq

---PAGE 1 OF 5---
> prev
No more pages.
> next
Mountains
[Sia, Diplo, Labrinth]
https://open.spotify.com/album/3dB0bCgmpEgCSr3aU1bOtv

Pussy Is God
[King Princess]
https://open.spotify.com/album/4UzCY6ikiEN4rgY26I4jg0

Shootin Shots (feat. Ty Dolla $ign & Tory Lanez)
[Trey Songz, Ty Dolla $ign]
https://open.spotify.com/album/6Erhbwa5HmDwuzYacUpLPr

Runaway
[Lil Peep]
https://open.spotify.com/album/38sesm68q3lg21o6Lpzslc

RESET
[Moneybagg Yo]
https://open.spotify.com/album/547DJFUYOl2SBYJbo2jZX1

---PAGE 2 OF 5---
> categories
Top Lists
Mood
Chill
Hip-Hop
Electronic/Dance
---PAGE 1 OF 10---
> next
Kids & Family
Rock
Indie
Happy Holidays
Workout
---PAGE 2 OF 10---
> exit
```

</details>
  



