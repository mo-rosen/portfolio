package com.kenzie.oregontrail;

/*
Child classes - Carpenter and Ranger
*/


/*
Variables (set to not public) -
    - name, a string set to the default of "Jane"
    - food, a number with a default value of 1
    - isHealthy, a boolean that shows if the traveler is sick or not

Constructors
    - empty constructor
    - constructor that takes a string name

Methods
    - getters and setters for the class variables
        (pay attention to boolean getter and setter names)
    - trap(), increases the food by 3
    - eat(),
        - decreases the food variable by 1
        - If the traveler tries to eat and food is at 0, they are no longer healthy
 */

public class Traveler {

    protected String name = "Jane";

    protected int food = 1;

    protected boolean isHealthy = true; // change to false or true?



    public Traveler(){
        this.name = "Jane";
        this.food = 1;
        this.isHealthy = true;
    }

    public Traveler(String name){
        this.name = name;
        this.food = 1;
        this.isHealthy = true;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }



    public int trap(){
//        this.food = food; // not needed

        food += 3;

        return food;
    }

    public int eat() {
//        this.food = food; // not needed

//        this.isHealthy = isHealthy; // not needed

        if (food >= 1){
            food -= 1; // make if statement only going down to zero?
//        isHealthy = true; // **do not need since isHealthy is defaulted to "true"**
    }
        else {
            isHealthy = false;
        }
        return food;
    }
}