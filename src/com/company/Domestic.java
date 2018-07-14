package com.company;

//Joseph Starr, CS202, Program #4
//This module contains one of the two derived classes.  This class
//represents the domestic airports read in from an external data file and
//copied into this class object


import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Domestic extends Airport {
    private Scanner ReadIn;
    private String type = "Domestic";
    private boolean weather;
    private int baggage;

    //This is the constructor that invokes the base class constructor
    //and calls the method to copy the data for this class from the argument class
    public Domestic(final FixData fixed) {
        super(fixed);
        this.GetInfo(fixed);
    }

    //This is the copy constructor
    public Domestic(Domestic toCopy) {
        super(toCopy);
        this.weather = toCopy.weather;
        this.baggage = toCopy.baggage;
    }

    @Override
    public boolean compareWait(int min, int max){
        int sum = this.waitSum();
        if(sum > min && sum < max){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean compareType(String toCompare){
        if(this.type.equals(toCompare)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Domestic)) {
            return false;
        }
        Domestic d = (Domestic) o;
        return super.AirportID.equalsIgnoreCase(d.AirportID);
    }

    //The next three methods overide the base classes matching abstract methods
    @Override
    public int waitSum() {
        if (weather) {
            return 100;
        } else {
            return LayoverTime;
        }
    }

    @Override
    public String returnType() {
        return this.type;
    }

    @Override
    public String idReturn() {
        return super.AirportID;
    }

    public void GetInfo(final FixData fixed) {
        super.GetInfo(fixed);
        this.weather = fixed.getWe();
        this.baggage = fixed.getBag();
    }

    //This is the display function for this class.  It first invokes the base classes
    //display function
    public void Display() {
        super.Display();
        /*System.out.print("  Real time layover information: \n");
        if (this.waitSum() == 100) {
            System.out.print("  Due to weather, all aircraft are grounded until further notice \n");
        } else {
            System.out.print("  Layover time: " + this.waitSum() + " hours \n");
        }
        if (weather)
            System.out.print("  Airport currently experiences weather delays \n");
        System.out.print("  Baggage Weight Allowed Per Customer: " + baggage + " pounds. \n");*/
    }

    public void displayImportant(){
        super.displayImportant();
        System.out.print("  Real time layover information: \n");
        if (this.waitSum() == 100) {
            System.out.print("  Due to weather, all aircraft are grounded until further notice \n");
        } else {
            System.out.print("  Layover time: " + this.waitSum() + " hours \n");
        }
    }

}
