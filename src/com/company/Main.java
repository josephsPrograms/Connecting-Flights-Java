package com.company;

//Joseph Starr, CS202, Program #4.
//This module is the main class object.  It contains the menu object
//That runs the entire program.  It contains the tree as a data member and
//a menu;

import java.io.*;
import java.util.Scanner;

import com.company.Airport;


public class Main {

    public static void main(String[] args) {
        Menu runProgram = new Menu();
        runProgram.menuChoice();
    }
}
