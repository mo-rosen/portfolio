package com.kenzie.app;

import org.apache.commons.io.input.BOMInputStream;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class ClueGame {

    public static int players = 10 ; //Application.playerList.size();
    public static String[][] gameBoard;


    public static String[][] freshBoard(String[] names) {
        try {
            int counter = 0;

//            System.out.println("\n'names' ARRAY LOOKS LIKE: " + Arrays.toString(names) + "\nPRINTING FROM 'freshBoard' \n");

            //todo:
            //      FIRST ARRAY: (PLAYERS) ==>> 0  1  2  3  4
            //      SECOND ARRAY:  (SCORE) || 0 [] [] [] [] []
            //                             vv 1 [] [] [] [] []
            //                                2 [] [] [] [] []
            //                                3 [] [] [] [] []
            //                                4 [] [] [] [] []
            gameBoard = new String[names.length][players]; // REMEMBER: 'names' .length NOT 'fields'
            for (int i = 0; i < gameBoard.length; i++) {
                    gameBoard[i][0] = "Player: " + names[counter];
                    counter++;
            }
        }
        catch (Exception e) {
            System.out.println(" \n'freshBoard' ERROR! \n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()) + "\n");
        }
        return gameBoard;
    }

    public static String[][] updateBoard(String[][] board, String[] scores) {
        // todo: NEED TO HAVE SCORES SET ALREADY

        try {
            int counter = 0;

            //todo:
            //      FIRST ARRAY: (PLAYERS) ==>> 0  1  2  3  4
            //      SECOND ARRAY:  (SCORE) || 0 [] [] [] [] []
            //                             vv 1 [] [] [] [] []
            //                                2 [] [] [] [] []
            //                                3 [] [] [] [] []
            //                                4 [] [] [] [] []

            // ADD SCORES
//            for (String[] row : board) {
//            for (int i = 0; i < board.length; i++) {
                for (int i = 0; i < board.length; i++) {
                    board[i][1] = "Score: " + scores[counter];
                        counter++;
//                    if (board[i][1] == null) {
//                        board[i][1] = "Score: " + String.valueOf(0);
//                    }
//                    else {
//                        board[i][1] = "Score: " + scores[counter];
//                        counter++;
//                    }
                }
//            }
        }
        catch (Exception e) {
            System.out.println("'updateBoard' ERROR " + e.getMessage());
        }
        return board;
    }

    public static void printGameBoard(String[][] board) throws NullPointerException {

        try {
            //todo:
            //      FIRST ARRAY: (PLAYERS) ==>> 0  1  2  3  4
            //      SECOND ARRAY:  (SCORE) || 0 [] [] [] [] []
            //                             vv 1 [] [] [] [] []
            //                                2 [] [] [] [] []
            //                                3 [] [] [] [] []
            //                                4 [] [] [] [] []

            // PRINT
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != null) {
                        if (board[i][j].contains("Score")) {// && !board[i][j].contains("0")
                            System.out.println(board[i][j] + "\n");
                        }
                        else {
                            System.out.println(board[i][j]);
                        }

//                        todo:  TEST
//                        System.out.println(" **Index [i][j] is: [" + i + "][" + j +"]\n");

                    }
                    else {
                        System.out.print("");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("'printGameBoard' ERROR " + Arrays.toString(e.getStackTrace()));
        }
    }
}
