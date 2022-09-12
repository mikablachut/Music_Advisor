# Music Advisor
* [Description](#description)
* [Technologies](#technologies)
* [Setup](#setup)
* [Running the application in a console](#running-the-application-in-a-console)

## Description

<details>
<summary>Click here to see general information about <b>Project</b>!</summary>

Web application using Spotify API. It allows the user to view newly released albums and playlists. Suggests playlists based on the music category selected by user.

The idea for project cames from Java Beckend Developer track in [JetBrains Academy](https://www.jetbrains.com/academy/).

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

Make sure you have [git](https://git-scm.com/) installed. To run the application you have tu use your own Spotify client_id and Spotify client_secret. To obtained it, you must go to  the [Spotify Web site for developers](https://developer.spotify.com/dashboard/login) and create your application. To create an application, you should select Dashboard tab on the site, log in to Spotify, and click the button Create an App. Than paste your client_id and client_secret to:

```java
public class Authorization {
    public static String REDIRECT_URI = "http://localhost:8080";
    public static String CLIENT_ID = ""; //spotify client_id
    public static String CLIENT_SECRET = "";//spotify client_secret
    public static String ACCESS_TOKEN = "";
    public static String ACCESS_CODE = "";
```

Next add the http://localhost:8080 to the whitelist of redirect_uri in your application settings on the Spotify site (Dashboard -> your app -> edit settings -> redirect URIs).

### Gradle

<b>Java 17 is required for this step.</b>

Clone repository and enter its folder:

```
git clone https://github.com/dominikablachut/Music_Advisor.git
cd Music_Advisor
```

Now you can run the app using Gradle:

```
gradle run
```

We can also run the application using traditional way using ```java -jar``` command but for that, we need to generate jar of out application.  
Let see those steps for Gradle:

```
gradle build
java -jar .\app\build\libs\app.jar
```

## Running the application in a console

To use the application, user have to confirm authorization via Spotify.

Once the program starts, user should enter the ```auth``` command. The application will display link(user have to use it) to request the access code. When the user confirm authorization, the server return the following text to the browser:

```Got the code. Return back to your program.```

After the code is received, the aplication will send a http request to obtain access_token. If the request sucssed, user will be able to enter the following requests in a console:
- featured — displays a list of Spotify-featured playlists with their links fetched from API;
- new — displays a list of new albums with artists and links on Spotify;
- categories — displays a list of all available categories on Spotify (just their names);
- playlists C_NAME, where C_NAME is the name of category. The list contains playlists of this category and their links on Spotify;
- exit - shuts down the application.

The application displays 5 position per page. User can navigate through the pages using the commands ```next``` (see the next page) and ```prev``` (see the previous page).

If the user rejects authorization,  the server return the following text to the browser:

``` Authorization code not found. Try again.```

In that case user will not be able to use the aplication functionality.

<details>
<summary><b>Below is an output example of the described program.</b></summary>

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
  



