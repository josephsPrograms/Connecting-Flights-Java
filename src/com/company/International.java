package com.company;

//Jospeh Starr, CS202, Program #4
//This module is one of the two derived classes in the hierarchy.
//It represents the international aiports in the external data files

import com.company.Airport;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class International extends Airport {
    private Scanner in;
    private String type = "International";
    private int delay;
    private int privateFlights;
    private String topEntertainment;

    //This is the constructor that copies the data passed in into its fields
    //It also invokes the parent classes constructor
    public International(final FixData fixed) {
        super(fixed);
        this.getInfo(fixed);
    }

    public International(International toCopy) {
        super(toCopy);
        this.delay = toCopy.delay;
        this.privateFlights = toCopy.privateFlights;
        this.topEntertainment = toCopy.topEntertainment;
    }

    @Override
    public boolean compareType(String toCompare){
        if(this.type.equalsIgnoreCase(toCompare)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o){
       if(this == o)
           return true;
       if(!(o instanceof Domestic)){
           return false;
       }
       International d = (International) o;
       return super.AirportID.equals(d.AirportID);
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

    //The next three methods overide the base classes matching abstract methods
    @Override
    public int waitSum() {
        return (LayoverTime + delay);
    }

    @Override
    public String returnType() {
        return this.type;
    }

    @Override
    public String idReturn() {
        return super.AirportID;
    }

    //This is the display finctoin for this class that first invokes the base
    //classes display function
    public void Display() {
        super.Display();
        System.out.print("Current layover time: " + this.waitSum() + " Hours \n");
        /*System.out.print("Number of private flight options: " + privateFlights + '\n');
        System.out.print("Top Layover Entertainment: " + topEntertainment + '\n');*/
    }

    public void displayImportant(){
        super.displayImportant();
        System.out.print("Current layover time: " + this.waitSum() + " Hours \n");
    }

    private void getInfo(FixData fixed) {
        super.GetInfo(fixed);
        this.delay = fixed.getDel();
        this.privateFlights = fixed.getprivate();
        this.topEntertainment = fixed.getTop();
    }
}
