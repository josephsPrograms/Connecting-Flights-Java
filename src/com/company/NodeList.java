package com.company;

//Joseph Starr, CS202, Program #5
//This module is the LLL for the adjacent/connectable airports within each node of
//the BST aiport nodes

public class NodeList {
    private NodeList next = null;
    private Airport type = null;

    public NodeList(NodeList toCopy){
        if(toCopy.returnType().equalsIgnoreCase("Domestic")){
            Domestic d = (Domestic) toCopy.type;
            type = new Domestic(d);
        }else{
            International i = (International) toCopy.type;
            type = new International(i);
        }
    }

    public NodeList(NodeTree toCopy){
        if(toCopy.returnType().equalsIgnoreCase("Domestic")){
            Domestic d = (Domestic) toCopy.returnData();
            type = new Domestic(d);
        }else{
            International i = (International) toCopy.returnData();
            type = new International(i);
        }
    }

    public Airport returnAiport(){
        return this.type;
    }

    //This constructor recieves the same data the tree recieves but only adds new nodes
    //with adjacent/connectbale airports if they are connectable from the tree node this
    //LLL is contained in
    public NodeList(final FixData fixed) {
        this.next = null;
        if (fixed.getType().equalsIgnoreCase("Domestic"))
            this.type = new Domestic(fixed);
        else
            this.type = new International(fixed);
    }

    public NodeList returnNext() {
        return this.next;
    }

    //This method returns the type of the airport this class contians
    public String returnType() {
        return this.type.returnType();
    }

    public void connectNext(NodeList toConnect) {
        next = toConnect;
    }

    //This method is the display method for the LLL nodes only needing to call
    //the display method for the airport it contains
    public void Display() {
        this.type.Display();
    }

    //The next two methods return the needed data to the tree for comparisons when adding or deleting
    //from the tree
    public int returnRank() {
        return this.type.returnRank();
    }

    public String dataID() {
        return this.type.returnID();
    }

    public boolean compareID(String toCompare){
        if(this.type.compareID(toCompare)){
            return true;
        } else {
            return false;
        }
    }

    public boolean compareWait(int min, int max){
        if(this.type.compareWait(min, max)){
            return true;
        } else {
            return false;
        }
    }
}

