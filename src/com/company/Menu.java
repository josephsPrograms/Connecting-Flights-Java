package com.company;

//Joseph Starr, CS202, Program #5
//This module is the menu class.  it controls the entire program
//creating the tree with the data read in and calling the appropriate
//tree methods when the user chooses the cooresponding menu choice

import java.io.*;
import java.util.Scanner;
import com.company.Airport;
import com.company.TextReader;


public class Menu {
    private int choice;
    private Scanner input;
    private Tree createdTree;
    private TextReader program;//this is the object that reads in the data from the ecternal datafile
                               //and creates a tree from it
    private DoubleList plan;

    //This is the constructot that creates the input stream object and tree
    public Menu(){
        input = new Scanner(System.in);
        choice = 0;
        try{
            //The following object is the tree that is returned from the TextReader object
            program = new TextReader("InternationalAirportData.txt", "AmericanAirportData.txt");
            createdTree = program.readData(null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //This is the method that is the main menu of the Menu class and the entire program.
    //It recursively calls its self and recieves the user's desire menu choice until the user
    //chooses to terminate the program
    public void menuChoice(){
        String toDelete;
        System.out.print("(1) See ariports \n");
        System.out.print("(2) Set Flight \n");
        System.out.print("(3) Terminate program and delete all \n");
        choice = input.nextInt();
        while(choice < 1 || choice > 3){
            System.out.print("You must enter a number between 1 and 3, please enter one again \n");
            choice = input.nextInt();
        }
        if(this.choice == 3) {
            return;
        } else if(this.choice == 1){
            this.createdTree.Display();
            this.menuChoice();
        } else if(this.choice == 2){
            plan = new DoubleList(createdTree);
            System.out.print("Flight Plan: \n\n");
            plan.display();
            this.menuChoice();
        } else {
            return;
        }
    }
}
