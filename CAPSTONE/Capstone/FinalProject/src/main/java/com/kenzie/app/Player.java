package com.kenzie.app;

public class Player {

    private String name = "";
    private int score = 0;
    private int cluesGiven = 0;


    public Player(String name, String score) {
        this.name = name;
        this.score = Integer.parseInt(score);
    }

    public Player(String playersName) {
        name = playersName;
    }

    public Player() {

    }

    public int getCluesGiven() {
        return cluesGiven;
    }

    public void setCluesGiven(int cluesGiven) {
        this.cluesGiven = cluesGiven;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", cluesGiven=" + cluesGiven +
                '}';
    }
}