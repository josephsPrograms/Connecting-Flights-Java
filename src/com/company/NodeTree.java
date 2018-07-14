package com.company;

//Joseph Starr, CS202, Program #4
//This module is the nodes class for the BST.  It contains the
//Aiport hierarchy as a field and a LLL of adjacent/connectable aiports

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;


public class NodeTree {
    private NodeTree left;
    private NodeTree right;
    private Airport data;
    private NodeList head;

    public NodeTree(NodeTree toCopy){
        if(toCopy.returnType().equalsIgnoreCase("Domestic")){
            Domestic d = (Domestic) toCopy.data;
            data = new Domestic(d);
        }else{
            International i = (International) toCopy.data;
            data = new International(i);
        }
       //this.head = copyList(toCopy.head, this.head);
    }

    public NodeTree(final NodeList toCopy){
        if(toCopy.returnType().equalsIgnoreCase("Domestic")){
           Domestic d = (Domestic)  toCopy.returnAiport();
           this.data = new Domestic(d);
        } else {
            International i = (International) toCopy.returnAiport();
            this.data = new International(i);
        }
    }

    /*private NodeList copyList(NodeList headToCopy, NodeList head){
        if(headToCopy == null){
            head = null;
            return head;
        }
        head = new NodeList(headToCopy);
        head.connectNext(copyList(headToCopy.returnNext(), head.returnNext()));
        return head;
    }*/

    //This is the constructor for the BST node class.  It
    //takes the data to be added to its airport field as well
    //as an array of strings to use to create the LLL
    public NodeTree(final FixData fixed, String[] data) {
        this.left = null;
        this.right = null;
        this.head = null;
        if (fixed.getType().equalsIgnoreCase("Domestic"))
            this.data = new Domestic(fixed);
        else
            this.data = new International(fixed);
        this.createList(data);
    }

    public NodeTree returnLeft() {
        return this.left;
    }

    public void connectLeft(NodeTree toConnect) {
        this.left = toConnect;
    }

    public NodeTree returnRight() {
        return this.right;
    }

    public void connectRight(NodeTree toConnect) {
        this.right = toConnect;
    }

    //This method returns the Aiport type it contains used by the BST
    //to compare types
    public String returnType() {
        return this.data.returnType();
    }

    //This method is used to tell if the aiport to be added to the LLL
    //of adjacent airpot is adjacent to the current one
    public boolean pickAdjacent(final FixData fixed) {
        if (this.data == null || fixed == null) {
            return false;
        } else if (this.data.compareID(fixed.getID())) {
            return false;
        } else if (this.data.compareType(fixed.getType())) {
            return true;
        } else if (this.data.compareRank(fixed.getRa())){
            return true;
        } else {
            return false;
        }
    }

    public Airport returnData(){
        return this.data;
    }

    public void creareListDouble(NodeTree toAdd){
        this.head = createListDouble(this.head, toAdd);
    }


    private NodeList createListDouble(NodeList head, NodeTree toAdd){
        if(head == null){
            head = new NodeList(toAdd);
            return head;
        }
        head.connectNext(createListDouble(head.returnNext(), toAdd));
        return head;
    }

    //This method creates the LLL of adjacent aiports.  It is the
    //wrapper method
    private void createList(String[] toAdd) {
        FixData fixedList;
        for (int i = 0; i < toAdd.length; ++i) {
            fixedList = new FixData(toAdd[i]);
            if (pickAdjacent(fixedList))
                this.head = createListWrap(this.head, fixedList);
            else
                fixedList = null;
        }
    }

    //This is the recursive method to create the LLL
    private NodeList createListWrap(NodeList head, FixData fixedList) {
        if (head == null) {
            head = new NodeList(fixedList);
            head.connectNext(null);
            return head;
        }
        head.connectNext(createListWrap(head.returnNext(), fixedList));
        return head;
    }

    //This method displays the airport it contains which is the
    //Airport represented in the BST as well as the LLL of airports
    //adjacent
    public void Display() {
        this.data.Display();
        System.out.print("POSSIBLE CONNECTING FLIGHTS: \n\n");
        this.DisplayList(this.head);
    }

    public void DisplayList(NodeList head) {
        if (head == null) {
            return;
        }
        System.out.print("      ");
        head.Display();
        DisplayList(head.returnNext());
    }

    public void displayPlan(){
        this.data.displayImportant();
    }

    //This method is the wrapper meethod to delete the LLL of
    //adjacent airports when this node in the BST is removed
    public void deleteList() {
        this.head = deleteListWrap(this.head);
    }

    public NodeList deleteListWrap(NodeList head) {
        if (head == null)
            return head;
        deleteListWrap(head.returnNext());
        head = null;
        return head;
    }

    //The next two methods return data needed by the BST to compare in the BST
    //for addition and removal
    public int returnRank() {
        return this.data.returnRank();
    }

    public String dataID() {
        return this.data.returnID();
    }

    //The next two methods are used to help find the home and destination
    //airports
    public NodeList findDest(NodeTree home, NodeTree dest, int min, int max){
        NodeList temp = findDestWrap(this.head, home, dest, min, max);
        return temp;
    }

    public NodeList findDestWrap(NodeList head, NodeTree home, NodeTree dest, int min, int max){
        if(head == null){
            return null;
        }
        if(head.compareID(dest.dataID())){
            return head;
        } else if(head.returnRank() >= home.returnRank() && head.compareWait(min, max)){
            return head;
        }
        findDestWrap(head.returnNext(), home, dest, min, max);
        return head;
    }

    /*************************************************************
     *                        The next methods are used to       *
     *                        compare data in the hierarchy      *
     * ***********************************************************/

    public boolean compareRank(int toCompare){
        if(this.data.compareRank(toCompare)){
            return true;
        } else {
            return false;
        }
    }

    public int waitSum(){
        return this.data.waitSum();
    }

    public boolean compareType(String toCompare){
        if(this.data.compareType(toCompare)){
            return true;
        } else {
            return false;
        }
    }

    public boolean compareWait(int min, int max){
        if(this.data.compareWait(min, max)){
            return true;
        } else {
            return false;
        }
    }

    public boolean compareID(String toCompare){
        if(this.data.compareID(toCompare)){
            return true;
        } else {
            return false;
        }
    }

    //The next two methods check to see if the passed in node
    //is adjacent to the current node
    public boolean isAdjacent(NodeTree location){
        return isAdjacent(this.head, location);
    }

    private boolean isAdjacent(NodeList head, NodeTree location){
       if(head == null){
           return false;
       }
       if(head.compareID(location.dataID())){
           return true;
       }
       return isAdjacent(head.returnNext(), location);
    }
}





