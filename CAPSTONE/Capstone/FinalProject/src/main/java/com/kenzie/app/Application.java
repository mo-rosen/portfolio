package com.kenzie.app;

// import necessary libraries
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.kenzie.app.ClueDTO.Category;

import java.sql.SQLOutput;
import java.util.*;
import java.util.jar.JarOutputStream;

public class Application {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!


   todo:
    1) Use an HTTP GET request to get a clue list from the API
    2) Present a single clue and its category to the user
    3) Wait for the user to respond and save their answer
    4) Determine if the user's answer was correct. A correct answer increases the user score by (1) point. An incorrect answer does not increase the score
    5) Display a message indicating whether the the answer was correct or incorrect. If the answer is incorrect, display the correct answer.
    6) Display the user's score
    7) Continue the game until 10 clues have been given to the user
    8) After the 10th clue, print the user's final score
*/
    public static ArrayList<ClueDTO> clueList = new ArrayList<>();
    public static ArrayList<Player> playerList = new ArrayList<>();
    public static ArrayList<String> positiveMessages = new ArrayList<>();
    public static boolean gamePlayedMarker;
    public static ClueDTO staticClueDTO = new ClueDTO();
    public static int players = 0;
    public static int randomPositive = 0;
    public static int score = 0;
    public static Scanner scanner = new Scanner(System.in);
    public static String correctAnswer = "";
    public static String newURL = "https://jservice.xyz/api/random-clue?valid=true"; // NOT "https://jservice.io/" OR "jservice.kenzie.academy"
    public static String playerName = "";
    public static String playerName2 = "";
    public static String playerName3 = "";
    public static String playerName4 = "";
    public static String userResponse = "";
    public static String[] names;
    public static String[] scores;
    public static String[][] board;

    public static String capitalize(String str) { // UPDATED FROM 'Player' RETURN TYPE AND PARAM 'Player player'

        str = str.toLowerCase();

        char ch = str.charAt(0);
        String newStr = String.valueOf(ch);
        newStr = newStr.toUpperCase();

        return newStr + str.substring(1);
    }

    public static boolean checkClue(ClueDTO clueDTO) {

        // TEST
//        System.out.println("RUNNING 'checkClue'");

        if (clueList.contains(clueDTO)) {
            return false;
        }
        if (clueDTO.getQuestion().length() <= 2) {
            return false;
        }
        return true;
    }

    public static String cleanUpUserResponse(String str) { // throws NullPointerException

        try {

                while (str.isBlank() || str.isEmpty()) {
                    System.out.println("Ummm your answer looks a little empty (or blank) over there.");
                    System.out.println("TRY AGAIN!");
                    str = scanner.nextLine();
                }

                char[] splitStr = str.toCharArray();

            for (int i = 0; i < splitStr.length; i++) {
                if (splitStr[i] == '"') {
                    splitStr[i] = '.';
                }
            }
            str = String.valueOf(splitStr);

            str = str.toLowerCase();

            str = str.replace("'", "");
            str = str.replace(".", "");
            str = str.replace(",", "");
            str = str.replace("!", "");
            str = str.replace("?", "");
            str = str.replace("-", "");
            str = str.replace("(", "");
            str = str.replace(")", "");

            str = capitalize(str.trim());
        }
        catch (Exception e) {
            return "ooh looks like that wasn't a valid answer. \nTry again!\n'cleanUpUserResponse' ERROR' \n" + e.getMessage();
        }
        return str;
    }

    public static void goodbye() {
        System.out.println("\n\n~~~~~" +
                "~~~~~~~~~~~~~~~~~~~~~~" +
                "~~~~~~~~~~~~\n" +
                "\n       **" +
                "*vio" +
                "lentl" +
                "y so" +
                "b" +
                "s**" +
                "*        \n\nI" +
                "t" +
                "'s b" +
                "een n" +
                "ice kno" +
                "wing you. Goo" +
                "dby" +
                "e! :(\n\n~~~" +
                "~~~~~~~~~~~~~~~~~" +
                "~~~~~~~~~~~~~~~~~~~\n");
    }

    public static void intro() throws JsonProcessingException {

        // MAKE PLAYER
        Player player = new Player();

        /*

         */

        // RESET 'playerList'
        for (int i = 0; i < playerList.size(); i++) {
            playerList.remove(playerList.get(i));
        }

        // QUESTION
        System.out.println("\n So you like games huh? \n I got you :) \n What do they call you?");

        // RESPONSE, CLEAN AND SET NAME
        player.setName(cleanUpUserResponse(scanner.nextLine()));

        // ADD PLAYER TO LIST
        playerList.add(player);

        // SINGLE OR MULTIPLAYER?
        System.out.println("\n So " + player.getName() + ", is it just you playing?");
        userResponse = scanner.nextLine().toLowerCase();
        cleanUpUserResponse(userResponse);

        if (!userResponse.equals("no") && !userResponse.equals("yes")) {
            System.out.println("Oh no! It looks like that answer was invalid. \n Please input 'yes' or 'no'");
        }
        else if (userResponse.equals("yes")) {

            // INCREMENT NUM PLAYERS INDICATOR
            players++;

            // RUN GAME
            for (int i = 0; i < playerList.size(); i++) {
                while (playerList.get(i).getCluesGiven() < 11) {
                    startGame();
                }
            }
            // todo: PRINTING FINAL SCORE FOR ONE PLAYER DIRECTLY IN MAIN
//            // NAMES ArrayList to String[]
//            names = nameList(playerList);
//
//            // SCORES ArrayList to String[]
//            scores = scoreList(playerList);
//
//            // GENERATE SINGLE PLAYER BOARD
//            board = ClueGame.updateBoard(ClueGame.freshBoard(nameList(playerList)), scores);
        }
        else {
            multiplayer();

            // NAMES ArrayList to String[]
            names = nameList(playerList);

            // SCORES ArrayList to String[]
            scores = scoreList(playerList);

            // GENERATE FINAL GAMEBOARD FROM MULTIPLAYER PLAYED GAME
            board = ClueGame.updateBoard(ClueGame.freshBoard(nameList(playerList)), scores);

            // TODO: TEST
//            System.out.println("END OF 'intro' HAS BEEN REACHED");
        }
    }

    public static void multiplayer() throws JsonProcessingException {

        // TEST
//        System.out.println("PRINTING FROM 'multiPlayer' \n 'playerList': " + playerList.toString() + "and size is: " + playerList.size());

        // HOW MANY?
        System.out.println("\nAwesome! Up to 4 people can play. How many will it be?");
        String numPlayersResponse = scanner.nextLine();
        cleanUpUserResponse(numPlayersResponse);

        try {
            players = Integer.parseInt(numPlayersResponse);
        }
        catch (Exception e) {
            System.out.println("Yikes! that's kinda invalid :/");
        }

        if (players <= 4 && players >= 1) {

            switch (players) {
                case 2:
                    System.out.println("\n#2 Who else is playing?");
                    playerName2 = scanner.nextLine();
                    playerName2 = cleanUpUserResponse(playerName2);

                    Player player = new Player(playerName2);
                    player.setName(playerName2);
                    playerList.add(player);

                    for (int i = 0; i < playerList.size(); i++) {
                        while (playerList.get(i).getCluesGiven() < 11) {
                            startGame();
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n#3 Who else is playing?");
                    playerName2 = scanner.nextLine();
                    playerName2 = cleanUpUserResponse(playerName2);

                    Player player1 = new Player(playerName2);
                    player1.setName(playerName2);

                    System.out.println("\n#3 Who else is playing?");
                    playerName3 = scanner.nextLine();
                    playerName3 = cleanUpUserResponse(playerName3);

                    Player player2 = new Player(playerName3);
                    player2.setName(playerName3);

                    playerList.add(player1);
                    playerList.add(player2);

                    for (int i = 0; i < playerList.size(); i++) {
                        while (playerList.get(i).getCluesGiven() < 11) {
                            startGame();
                        }
                    }
                    break;

                case 4:
                    // SECOND PLAYER
                    System.out.println("\n#4 Who else is playing?");
                    playerName2 = scanner.nextLine();
                    playerName2 = cleanUpUserResponse(playerName2);

                    Player player3 = new Player(playerName2);
                    player3.setName(playerName2);

                    // THIRD PLAYER
                    System.out.println("\n#4 Who else is playing?");
                    playerName3 = scanner.nextLine();
                    playerName3 = cleanUpUserResponse(playerName3);

                    Player player4 = new Player(playerName3);
                    player4.setName(playerName3);

                    // FOURTH PLAYER
                    System.out.println("\n#4 Who else is playing?");
                    playerName4 = scanner.nextLine();
                    playerName4 = cleanUpUserResponse(playerName4);

                    Player player5 = new Player(playerName4);
                    player5.setName(playerName4);

                    playerList.add(player3);
                    playerList.add(player4);
                    playerList.add(player5);

                    for (int i = 0; i < playerList.size(); i++) {
                        while (playerList.get(i).getCluesGiven() < 11) {
                            startGame();
                        }
                    }
                    break;

                default:
                    System.out.println("\nOops doesnt look like that was a number 1 through 4. \n Try again!");
            }
        }
        else {
            System.out.println("\nHmmm doesn't look like that was a number between 1 and 4. Try again!");
        }
    }

    public static String[] nameList(ArrayList<Player> list) throws IndexOutOfBoundsException {

        try {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).getName());

               // OMIT ', '
                if (list.size() > 1 && list.get(i) != list.get(list.size() -1)) {
                    sb.append(", ");
                }
            }
            String str = sb.toString();
            names = str.split(",");
        }
        catch (Exception e) {
            System.out.println("PRINTING FROM 'nameList' --> " + e.getMessage());
        }
        return names;
    }

    public static void newGame(ArrayList<Player> playerList, ArrayList<ClueDTO> clueList) {

        // RESET 'playerList'
        for (int i = 0; i < playerList.size(); i++) {
            playerList.remove(playerList.get(i));
        }

        // RESET 'clueList'
        for (int i = 0; i < clueList.size(); i++) {
            clueList.remove(clueList.get(i));
        }

        // TEST
        System.out.println("PRINTING FROM 'newGame' \n'playerList' LOOKS LIKE: " + playerList.toString());
    }

    public static ClueDTO onlyNew() throws JsonProcessingException {

        staticClueDTO = CustomHttpClient.deserialize(CustomHttpClient.sendGET(newURL));

        return staticClueDTO;
    }

    public static void playAgain() throws JsonProcessingException {

        System.out.println("New game?");
        userResponse = cleanUpUserResponse(scanner.nextLine());

        if (userResponse.equalsIgnoreCase("yes")) {
            newGame(playerList, clueList);
            intro();
            if (playerList.size() > 1) {
                ClueGame.printGameBoard(board);
            }
            else {
                System.out.println("YOUR FINAL SCORE IS: " + score);
            }
        }
        else {
            goodbye();
            gamePlayedMarker = false;
        }

        /*
        String exitKey = "";
        String newPlayers = "";
        int exitKeyNum = 0;
        int newPlayersNum = 0;

            System.out.println("Would you like to play again?\nEnter 1 for 'yes' or 2 for 'no'.");
            exitKey = scanner.nextLine();
            exitKey = cleanUpUserResponse(exitKey);
            exitKeyNum = Integer.parseInt(exitKey);

            switch (exitKeyNum) {
                case 1:
                    // ANOTHER GAME
                    System.out.println("Cool! Are the same folks playing?\nEnter 3 for 'New Players' or 4 for 'Same Players'.");
                    newPlayers = scanner.nextLine();
                    newPlayers = cleanUpUserResponse(newPlayers);
                    newPlayersNum = Integer.parseInt(newPlayers);

                    if (newPlayersNum == 3) {

                        // NEW PLAYERS
                        intro();
                    }
                    else {
                        // SAME PLAYERS
                        try {
                            for (int i = 0; i < playerList.size(); i++) {

                                while (playerList.get(i).getCluesGiven() < 11) {

                                    startGame();
                                }
                            }

                            // todo: TEST
                            System.out.println("PRINTING FROM PLAYAGAIN\nPLAYERLIST LOOKS LIKE: " + playerList.toString());

                        } catch (Exception e) {
                            System.out.println("PRINTING FROM 'playAgain'\n" + e.getMessage());
                        }
                    }

                case 2:
                    // QUIT
                    if (exitKey.equalsIgnoreCase("no")) {
                        goodbye();
                    }
                        else {
                            for (int i = 0; i < playerList.size(); i++) {

                                while (playerList.get(i).getCluesGiven() < 11) {

                                    startGame();
                                }
                            }
                        }
                    }

         */

            /*
//            // todo: MOST RECENTLY UPDATED
//            // GAME COMPLETE MARKER / RESET
//            if (playerList.get(0).getCluesGiven() == 11){
//                for (int i = 0; i < playerList.size(); i++) {
//                    reset(playerList.get(i));
//                }
//
//                // ESTABLISH EXIT KEY
//                System.out.println("Would you like to play again?");
//                exitKey = scanner.nextLine();
//                cleanUpUserResponse(exitKey);
//
//                // NEW PLAYERS OR NOT
//                if (exitKey.equals("yes")) {
//                    System.out.println("Are the same folks playing?");
//                    newPlayers = scanner.nextLine();
//                    cleanUpUserResponse(newPlayers);
//
//                    if (newPlayers.equalsIgnoreCase("yes")) {
//
//                        // FRESH GAME
//                        for (int i = 0; i < playerList.size(); i++) {
//                            while (playerList.get(i).getCluesGiven() < 11) {
//
//                                // TEST
//                                System.out.println("PRINTING FROM PLAYAGAIN");
//                                startGame(playerList);
//                            }
//                        }
//                        //startGame(playerList);
//                    }
//                    else if (newPlayers.equalsIgnoreCase("no")) {
//
//                        // FRESH GAME WITH NEW PEOPLE
//                        // (WILL RESET 'playerList' AND 'clueList')
////                newGame(playerList, clueList);
//                        intro();
//                    }
//                }
//            }
             */

    }

    public static void programResponse(Player player){

        positiveMessages.add("Nice work!");
        positiveMessages.add("You got it right!");
        positiveMessages.add("Congrats that's right!");
        positiveMessages.add("That's a good answer.. and the right one!");
        //todo: ADD MORE RESPONSES?

        // UPDATE AND CLEAN 'userResponse'
        userResponse = cleanUpUserResponse(userResponse);

        try {

            // INCREMENT 'cluesGiven'
            player.setCluesGiven(player.getCluesGiven() + 1);

            // CHECK ANSWER
            if (rightAnswer(userResponse)) {

                // GENERATE RANDOM NUMBER
                randomNum();

                // ADD POINT
                player.setScore(player.getScore() + 1);
                score++;

                // TEST
//                System.out.println("PRINTING FROM 'programResponse'\n 'player's score is: " + player.getScore());

                // CORRECT ANSWER
                System.out.println(positiveMessages.get(randomPositive) + "\n You've earned 1 point!");
                if (player.getScore() > 2) {
                    System.out.println("WOWWW it's like you know the answers! :o");
                }
                System.out.println("\n Your score is: " + player.getScore());

                /*
                 |||  SHOULD NOT USE THIS UNLESS THERE ARE ENOUGH POSITIVE RESPONSES TO
                 VVV      GENERATE THROUGH 10 CORRECT ANSWERS
                 positiveMessages.remove(positiveMessages.get(randomPositive));
                */

            } else {
                // INCORRECT ANSWER
                System.out.println("\nPhooey wrong answer :/ \nThe correct answer is '" + correctAnswer + "'\n");

                // TEST
//                System.out.println(player.getName() + "'s clues: " + player.getCluesGiven());
//                System.out.println("^^^ CALLED FROM 'programResponse'\n|||");
            }
        }
        catch (Exception e) {
            System.out.println("'programResponse' --> " + e.getMessage());
        }
    }

    public static int randomNum() {
        return randomPositive = (int)(Math.random() * (positiveMessages.size() - 1) + 1);
    }

    public static void reset(Player player) {

            // RESET SCORES
        player.setScore(0);

            // DEFAULT 'cluesGiven' to 1
        player.setCluesGiven(1);

        gamePlayedMarker = false;

        // TEST
//        System.out.println("\n*******************\n*** 'reset' RAN ***\n" +
//                "******************* \nHERE IS WHAT THE 'playerList' LOOKS LIKE RN: " +
//                playerList.toString());
    }

    public static boolean rightAnswer(String answer) {

        // TEST
//        System.out.println("\n 'answer' is: " + answer + " \n AND \n 'correctAnswer' is: '" + correctAnswer + "' \n --> PRINTING FROM 'rightAnswer' \n");

        if (!correctAnswer.contains(answer)) {
            return false;
        }
        return true;
    }

    public static String[] scoreList(ArrayList<Player> list) {

        try {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).getScore());

                // OMIT ', '
                if (list.size() > 1 && list.get(i) != list.get(list.size() -1)) {
                    sb.append(", ");
                }
            }
            String str = sb.toString();
            scores = str.split(",");

            // TEST
//            System.out.println("PRINTING FROM 'scoreList'\n 'scores' LOOKS LIKE: " + Arrays.toString(scores));
        }
        catch (Exception e) {
            System.out.println("PRINTING FROM 'scoreList' --> " + e.getMessage());
        }
        return scores;
    }

    public static void startGame() throws JsonProcessingException, IndexOutOfBoundsException {

        try {

            // ACCESS EACH PLAYER
            for (int i = 0; i < playerList.size(); i++) {

                // TEST
//               testMethod(playerList);
//                System.out.println(playerList.get(i).getName() + "'s clues: " + playerList.get(i).getCluesGiven());
//                System.out.println("^^^ CALLED FROM 'startGame'\n|||");

                // RESET (ONLY IF APPLICABLE)
                // (CLUE IS INCREMENTED IN 'programResponse')
                if (playerList.get(i).getCluesGiven() == 0 || playerList.get(i).getCluesGiven() == 11) {

                    // todo:
                    reset(playerList.get(i));// (SETTING 'cluesGiven' TO 1 IN RESET)

                }
                // UPDATE CLUE
                staticClueDTO = CustomHttpClient.deserialize(CustomHttpClient.sendGET(newURL));

                // CLEAN CLUE OF TAGS
                staticClueDTO.setQuestion(staticClueDTO.getQuestion().replace("/<br />", ""));
                staticClueDTO.setQuestion(staticClueDTO.getQuestion().replace("<br />", ""));

                // CONFIRM CLUE IS NEW
                if (!checkClue(staticClueDTO)) {
                    onlyNew();
                }

                // UPDATE ANSWER
                correctAnswer = cleanUpUserResponse(staticClueDTO.getAnswer());

                    // FIRST Q
                    if (playerList.get(i).getCluesGiven() == 1) {
                        System.out.println("\n Okay " + playerList.get(i).getName() +
                                ", let's play! \n I'll ask you a random question and show you the category. \n If you answer correctly, you get a point! \n " +
                                "This game will play until 10 clues have been given. \n");
                    }
                    // FOLLOWING Q
                    else {
                        System.out.println("\n Okay " + playerList.get(i).getName() + ", next question! \n");
                    }

                    //TODO: IMPLEMENT TIMING?

                    // GIVE CLUE
                    System.out.println("THIS IS CLUE NUMBER: " + playerList.get(i).getCluesGiven());
                    System.out.println("Category: " + staticClueDTO.getCategory().getTitle());
                    System.out.println("Question: " + staticClueDTO.getQuestion());


                // todo: TESTS (ANSWER!)
                    System.out.println("THIS CLUE'S ID IS: " + staticClueDTO.getId());
                    System.out.println("\n PSST I'LL TELL YOU THE ANSWER...\n" + staticClueDTO.getAnswer());

                    // USER ANSWER
                    userResponse = cleanUpUserResponse(scanner.nextLine());

                    // RIGHT OR WRONG
                    programResponse(playerList.get(i));

                    // ADD USED CLUE TO LIST
                    clueList.add(staticClueDTO);
            }
        }
        catch (Exception e) {
        System.out.println("CLUE ERROR IN 'startGame' \n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        // MARKER TO SEE IF GAME HAS BEEN PLAYED
        gamePlayedMarker = true;

        // TEST
//        System.out.println("PRINTING 'names': " + Arrays.toString(names) + " --> END OF 'startGame' HAS BEEN REACHED");
    }

    public static void main(String[] args) throws JsonProcessingException {
        //Write main execution code here

        try {

            // POPULATE clueList
            ClueDTO emptyClue = new ClueDTO();
            clueList.add(emptyClue);

            while (!gamePlayedMarker) {

                // TEST
                System.out.println("PRINTING FROM MAIN");
                intro();
            }
            if (playerList.size() > 1) {
                ClueGame.printGameBoard(board);
            }
            else {
                System.out.println("\n YOUR FINAL SCORE IS: " + score);
            }

            // OPTION FOR NEW GAME AND/OR NEW PEOPLE
            while (gamePlayedMarker) {
                playAgain();
            }
        }
        catch (Exception e) {
            System.out.println("PRINTING FROM 'main' --> " + e.getMessage());
        }
        // TEST
//        System.out.println("END OF 'main' HAS BEEN REACHED");
    }

}