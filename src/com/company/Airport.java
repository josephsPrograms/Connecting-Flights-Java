package com.company;

//Joseph Starr, CS202, Program #4
//This module is the abstract base class for the hierarchy in this program.
//It recieves its data from a class object that recieves the data from an
//external text file.


import java.io.*;
import java.util.Scanner;

abstract class Airport {
    protected String AirportID;
    private String State;//Will represent country for international aiports
    private String City;
    protected int LayoverTime;
    private int Rank;

    public Airport() {
    }

    public Airport(final FixData fixed) {//This method copies data from another class object
        this.Rank = fixed.getRa();
        this.AirportID = fixed.getID();
        this.State = fixed.getSt();
        this.City = fixed.getCi();
        this.LayoverTime = fixed.getLay();
    }

    //The next three methods are abstract functions only implemented in the derived classes
    public abstract String idReturn();

    public abstract String returnType();

    public abstract int waitSum();

    public abstract boolean compareType(String toCompare);

    public abstract boolean compareWait(int min, int max);

    //This is the constructor called with the data to be copied as an argument
    public Airport(Airport toCopy) {
        this.Rank = toCopy.Rank;
        this.AirportID = toCopy.AirportID;
        this.State = toCopy.State;
        this.City = toCopy.City;
        this.LayoverTime = toCopy.LayoverTime;
    }

    public boolean compareID(String toCompare){
        if(this.AirportID.equals(toCompare)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean compareRank(int toCompare){
        if(this.Rank == toCompare){
            return true;
        } else {
            return false;
        }
    }

    //This method copies data from a class object
    public void GetInfo(final FixData fixed) {
        this.Rank = fixed.getRa();
        this.AirportID = fixed.getID();
        this.State = fixed.getSt();
        this.City = fixed.getCi();
        this.LayoverTime = fixed.getLay();
    }

    //This is the display method for this class
    protected void Display() {
        System.out.print("Airport: " + this.AirportID + '\n');
        //System.out.print("  Airport Rank: " + Rank + '\n');
        //System.out.print("  City and State/Country and City: " + City + ", " + State + '\n');
        //System.out.print("  Average Layover Time: " + LayoverTime + " hours" + '\n');
    }

    protected void displayImportant(){
        System.out.print("Airport: " + this.AirportID + '\n');
    }

    //This method returns the airport's ID used for comparisons in the tree
    public String returnID() {
        return this.AirportID;
    }

    public int returnRank() {
        return this.Rank;
    }
}
