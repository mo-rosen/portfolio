package com.kenzie.oregontrail;

/*
Carpenter is a child class of Traveler with one additional method.

Rules
    - The default name should be "Carpenter"
    - Carpenters are hungry folks! They consume more food when the eat() method is called

Constructors (calls super)
    - empty constructor
    - constructor that takes a string name

Methods
    - When eat() is called, the carpenter eats 2 food
        - if the Carpenter tries to eat when food is 0, isHealthy is false
        - if the Carpenter tries to eat when food is 1 or 2, food goes to 0, they stay healthy


 */
public class Carpenter extends Traveler {

    public Carpenter(){
        super(); // public Carpenter(firstPersonalToThisOne){super(thenCallOriginals);
        this.name = "Carpenter";
        this.food = 1;
        this.isHealthy = true;
    }
    public Carpenter(String name){
        super();
        this.name = name;
        this.food = 1;
        this.isHealthy = true;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getFood() {
        return super.getFood();
    }

    @Override
    public void setFood(int food) {
        super.setFood(food);
    }

    @Override
    public boolean isHealthy() {
        return super.isHealthy();
    }

    @Override
    public void setHealthy(boolean healthy) {
        super.setHealthy(healthy);
    }

    @Override
    public int trap() {
        return super.trap();
    }

    @Override
    public int eat() {
//         super.eat();        DONT NEED SUPER HERE!!!!!!

        if (food >= 2){
            food -= 2; // make if statement only going down to zero?
        }
        else if (food == 1){
            food = 0;
        }
        else if (food == 0){
            isHealthy = false;
        }
        return food;
    }


    public boolean tryToFixWagon(Wagon candidate,Traveler helper){

        if (isHealthy && helper.isHealthy){
            eat();
            candidate.fixWagon();
        }
        else {
            System.out.println(Script.SHOOT); // PROPERLY CALLED?
            return false;
        }
        return true;
    }

    /*
     - tryToFixWagon, The carpenter and another traveler try to fix the wagon.

            - takes a wagon and another traveler as parameters.

            - Before the wagon can be fixed, the carpenter must eat.
        - After eating, both travelers must be healthy in order to fix the wagon.

    The wagon is fixed if -
            - The carpenter can successfully eat
            - Both travelers are healthy
            - Use wagon.fixWagon() (this should already be written for you)

    The wagon can't be fixed if -
            - The carpenter is not healthy after eating
            - The other traveler isn't healthy
            - If the wagon isn't fixed, print the SHOOT value of the Script enum

*/





}
