package com.kenzie.oregontrail;

/*
Class Variables
    - isBroken, if the wagon is broken
    - capacity, the number of seats on the wagon
    - passengers, a collection of all passengers
        - it can only hold up to 'capacity' number of passengers
        - You may choose to use a primitive array or ArrayList (choose wisely!)


Constructors
    - empty constructor,
        - sets the capacity to 10
        - sets isBroken to false
        - creates an empty passenger list of size 10
    - constructor that takes one int
        - sets the int to the wagon capacity
        - sets isBroken to false
        - creates an empty passenger list of size capacity

Methods
    - standard getters and setters
    - setCapacity() should remove passengers if the capacity becomes smaller. Remove passengers from the back.
    - addTraveler(), adds a traveler to an empty seat.
            - If no seats are empty, do not add the traveler
            - Jane always sits in front. If the passenger's name is "Jane", add to the front of the passenger array
    - shouldQuarantine(), returns true if any passenger is sick
    - totalFood(), sum of the total food of all passengers
    - isThereRoom(), returns true if there are empty seats on the wagon
    - allAboard(), initialize the wagon with passengers. This will take 3 parameters -
        - The number of Travelers
        - The number of Carpenters
        - The number of Rangers
        - Add until the Wagon is full. Do not add more travelers than the wagon capacity
        - Load rangers first, then carpenters, then travelers

Pre-Made Methods
    - fixWagon(), sets isBroken to false
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Wagon {
    private boolean isBroken;

    private int capacity;

//    private Traveler[] passengers = new Traveler[capacity];
    private ArrayList<Traveler> passengers = new ArrayList<>(capacity);

    public boolean isBroken() {
        return this.isBroken;
    }

    public void breakWagon() {
        this.isBroken = true;
    }

    public void fixWagon() {
        this.isBroken = false;
    }



    public Wagon() {
        isBroken = false;
        capacity = 10;
        ArrayList<Traveler> passengers = new ArrayList<>(10); // this should be needed (1st) constructor but double check passengers?
    }

            //    public Traveler[] Wagon(){     // this is a method not constructor..?
            //        this.isBroken = false;
            //        capacity = 10;
            //
            //    return passengers;
            //    }

    public Wagon(int capacity) {
        this.capacity = capacity;
        isBroken = false;
        ArrayList<Traveler> passengers = new ArrayList<>(capacity); // this should be needed (1st) constructor but double check passengers?
    }

            //    public int Wagon(int passengers){          // this is a method not constructor..?
            //        this.isBroken = true;
            //        this.capacity = capacity;
            //       // this.passengers = capacity;
            //    return capacity;
            //    }


    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    public int getCapacity() {
        return capacity;
    }

//    //- setCapacity() should remove passengers if the capacity becomes smaller. Remove passengers from the back.
    public void setCapacity(int capacity) {
        this.capacity = capacity;
        if (capacity < 10 && passengers.size() != 0){ // convert and convert back again??
            passengers.remove(passengers.size() - 1);
        }
    }

    public ArrayList<Traveler> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Traveler> passengers) {
        this.passengers = passengers;
    }

    // - addTraveler(), adds a traveler to an empty seat.
    //            - If no seats are empty, do not add the traveler
    //            - Jane always sits in front. If the passenger's name is "Jane", add to the front of the passenger array
    public boolean addTraveler(Traveler traveler) {

        if (isThereRoom()) {
//            for (Traveler traveler2 : passengers) {
                if (traveler.getName().equals("Jane")) {
                    passengers.remove(traveler);
                    passengers.add(0, traveler);
                } else {
                    passengers.add(traveler);
            }
                return true;
            //}
        }
        return false;
    }


    //    - shouldQuarantine(), returns true if any passenger is sick
    public boolean shouldQuarantine(){

        for (Traveler traveler : passengers){
            if (!traveler.isHealthy){
                return true;
            }
//            else { return false;}
        }
//        for (int i = 0; i < passengers.size(); i++) {
//            if (!i.isHealthy) {
//                return true; //specify each traveler? or use indexes of array? also maybe use isHealthy again and this. for less confusion?
//            }
//        }
        return false; // <<-- EDIIIIT???
    }


    //    - totalFood(), sum of the total food of all passengers
    public int totalFood(){

        int total = 0;

        for (Traveler traveler : passengers){
            total += traveler.food;
        }
        return total;
    }


    //    - isThereRoom(), returns true if there are empty seats on the wagon
    public boolean isThereRoom(){

        if (capacity <= passengers.size()){
            return false; // create variable (additional boolean?) to store result/status and then
                         // return (outside of if statement)?
        }
        return true;
    }


    // - allAboard(), initialize the wagon with passengers. This will take 3 parameters -
    //        - The number of Travelers
    //        - The number of Carpenters
    //        - The number of Rangers
    //        - Add until the Wagon is full. Do not add more travelers than the wagon capacity
    //        - Load rangers first, then carpenters, then travelers
    public void allAboard(int travelers, int carpenters, int rangers){

        for (int i = 0; i < rangers; i++) {
                addTraveler(new Ranger());
                //rangers++;
        }
        for (int i = 0; i < carpenters; i++) {
                addTraveler(new Carpenter());
                //carpenters++;
        }
        for (int i = 0; i < travelers; i++) {
                addTraveler(new Traveler());
                //travelers++;
        }
    }
}
