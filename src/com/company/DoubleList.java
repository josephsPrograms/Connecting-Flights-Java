package com.company;

//Joseph Starr, CS202, Program #5
//This module is the doubly linked list module

import javax.swing.tree.TreeNode;
import java.io.*;
import java.util.Scanner;

public class DoubleList {
    private NodeTree head = null;
    private int minLay;
    private int maxLay;
    private String current;
    private String destination;
    private Airport type;
    private Scanner input;

    //This constructor copies from a tree to get its data
    public DoubleList(Tree options, String departure, String dest, int min, int max){
        this.input = new Scanner(System.in);
        this.current = departure;
        this.destination = dest;
        this.minLay = min;
        this.maxLay = max;
    }

    //This constructor copies data from a treej
    public DoubleList(Tree options){
        this.minLay = 0;
        this.maxLay = 0;
        this.current = null;
        this.destination = null;
        this.input = new Scanner(System.in);
        this.travelPlan();
        this.createTravelPlan(options);
    }

    /*private NodeTree returnPrevious(){
        return this.previous;
    }

    public void connectPrevious(NodeTree toConnect){
        this.previous = toConnect;
    }*/

    //This method recieves the start and end location for the flight the user wants
    public void travelPlan(){
        System.out.print("Enter in your departure location\n");
        this.current = input.nextLine();
        //System.out.print(this.current + "\n");
        System.out.print("Enter in your desired arrival location \n");
        this.destination = input.nextLine();
        //System.out.print(this.destination + "\n");
        System.out.print("Enter in the minimum layover time \n");
        this.minLay = input.nextInt();
        System.out.print("Enter in the maximum layover time \n");
        this.maxLay = input.nextInt();
    }

    //This
    public void createTravelPlan(Tree options){
        this.head = options.findStartFinish(this.current, this.destination, this.minLay, this.maxLay);
        this.connectPreviousList(this.head, this.head);
    }

    private NodeTree createList(NodeTree head, NodeTree toAdd){
        if(head == null) {
            head = new NodeTree(toAdd);
            return head;
        }
        head.connectLeft(createList(head.returnLeft(), toAdd.returnLeft()));
        return head;
    }

    //This method connects the previous pointer which is used as the rught
    //pointer of a  BST where the next pointer is used as the left pointer
    private void connectPreviousList(NodeTree head, NodeTree headPrevious){
        if(head == null){
            return;
        }
        head.connectRight(headPrevious);
        connectPreviousList(head.returnLeft(), headPrevious);
        return;
    }

    public void display(){
        displayList(this.head);
    }

    private void displayList(NodeTree head){
        if(head == null){
            return;
        }
        head.displayPlan();
        displayList(head.returnLeft());
    }
}
