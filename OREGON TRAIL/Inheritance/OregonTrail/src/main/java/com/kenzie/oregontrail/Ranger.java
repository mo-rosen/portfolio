package com.kenzie.oregontrail;

import java.util.Random;

/*
Ranger is a child class of Traveler with two additional methods.

Rules
    - The default name should be "Ranger"
    - Starts with 4 food

Constructors (calls super)
    - empty constructor
    - constructor that takes a string name

Methods
    - trap(), increases food supply by 4
    - forageMedicine(), heals another traveler
            - takes another traveler as a parameter
            - foraging is hard! the ranger can't always find medicine.
                - there is only a 50% chance that the traveler parameter is healed

 */

public class Ranger extends Traveler {

    public Ranger() {
        super();
        this.name = "Ranger";
        this.food = 4;
        this.isHealthy = true;
    }

    public Ranger(String name) {
        super(); // "name" in parentheses?
        this.name = name;
        this.food = 4;
        this.isHealthy = true;
    }

//    @Override                             // DO NOT NEED GETTERS AND SETTERS!!!... STOOOOPID
//    public String getName() {
//        return super.getName();
//    }
//
//    @Override
//    public void setName(String name) {
//        super.setName(name);
//    }
//
//    @Override
//    public int getFood() {
//        return super.getFood();
//    }
//
//    @Override
//    public void setFood(int food) {
//        super.setFood(food);
//    }
//
//    @Override
//    public boolean isHealthy() {
//        return super.isHealthy();
//    }
//
//    @Override
//    public void setHealthy(boolean healthy) {
//        super.setHealthy(healthy);
//    }

    @Override
    public int trap() {
        food += 4; // make 1 not 4? (in addition to the original added 3?)
        return food;
    }


//    public int eat() {
//
//        if (food >= 1){
//            food -= 1; // make if statement only going down to zero?
//        }
//        else {
//            isHealthy = false;
//        }
//        return food;
//    }
    public void forageMedicine(Traveler traveler, int number){

    }

    public void forageMedicine(Traveler traveler){ // this was added (copied and pasted from slack hw channel

        int random = (int)(Math.random() * 100) + 1;

//        boolean success;

        if (random <= 50){
            isHealthy = false;
        }
//        else {
//            success = false;
//        }
//        if (success == false){
//            traveler.isHealthy;
//        }
    }

    public void cook(Traveler traveler, int food){

        if (this.food >= food){
            this.food -= food;
            traveler.food += food;
        }
    }
/*
- cook()
            - take a traveler
            - takes an integer amount of food that the traveler requests
            - if ranger has less food than requested, no food should be transferred
            - if the ranger has enough food, the food is transferred
                - the ranger's food should decrease
                - traveler parameter's food should increase
 */


}
