package com.company;

//Joseph Starr, CS202, Program #4
//This is the BST class object. It adds to, removes from, removes all, and retrieves.
//It contains a root object of the node class, a class object that recieves the data
//to be added to the tree, and an array of strings used to create the tree as well

//import javax.swing.tree.TreeNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Tree {
    private NodeTree root = null;
    FixData fixed;
    String[] data;
    NodeTree temp;

    //This is the copy constructor for the tree class
    public Tree(Tree toCopy){
        this.root = copyTree(toCopy.root, this.root);
    }

    //this is the recursive copy constructor for the tree
    private NodeTree copyTree(NodeTree toCopyRoot, NodeTree root){
        if(toCopyRoot == null){
            root = null;
            return root;
        }
        root = new NodeTree(toCopyRoot);
        root.connectLeft(copyTree(toCopyRoot.returnLeft(), root.returnLeft()));
        root.connectRight(copyTree(toCopyRoot.returnRight(), root.returnRight()));
        return root;
    }

    //This constructor turns the List of string arrays into an array ofstrings and then calls the
    //method to add to/create the tree
    public Tree(ArrayList<String> toAdd) {
        this.data = toAdd.toArray(new String[toAdd.size()]);
        this.addTree();
    }

    //this method adds the the tree by itterating through the
    //array of strings that contains the data for an airport in each
    //index. (each index is an airport with its cooresponding data)
    public void addTree() {
        for (int i = 0; i < this.data.length; ++i) {
            this.fixed = new FixData(this.data[i]);
            this.addNode(this.root, null, this.fixed);
        }
    }

    //This method calls the recursive method to add to the BST.  It itsself
    //is called in the above method
    public void addNode(NodeTree root, ArrayList<String> toAdd, final FixData fixed) {
        this.root = createTree(root, this.data, fixed);
    }

    //This is the recursive method to add to the BST.  The order of addition and
    //traversal is based on the ID ('PDX', 'LAX', etc) of the airports being added
    private NodeTree createTree(NodeTree root, String[] data, final FixData fixed) {
        if (root == null) {
            root = new NodeTree(fixed, data);
            return root;
        } else if (fixed.getID().compareTo(root.dataID()) < 0) {
            root.connectLeft(createTree(root.returnLeft(), data, fixed));
        } else if (fixed.getID().compareTo(root.dataID()) > 0) {
            root.connectRight(createTree(root.returnRight(), data, fixed));
        } else if (fixed.getID().compareTo(root.dataID()) == 0) {
            return root;
        }
        return root;
    }

    //This is the wrapper method for the display method
    public void Display() {
        this.DisplayRec(root);
    }

    //This is the recursive method for the BST.  It calls the display method
    //for each airport in the BST node as well as the display method for the
    //LLL of adjacent airports
    private void DisplayRec(NodeTree root) {
        if (root == null) {
            return;
        } else {
            root.Display();
            System.out.print("\n\n\n\n");
        }
        DisplayRec(root.returnLeft());
        DisplayRec(root.returnRight());
    }

    //This method retrieves the deisred tree node
    public NodeTree retrieveOne(NodeTree root, String toRetrieve) {
        if (root == null)
            return null;
        if (root.dataID().equalsIgnoreCase(toRetrieve)) {
            return root;
        }
        root.connectLeft(retrieveOne(root.returnLeft(), toRetrieve));
        root.connectRight(retrieveOne(root.returnRight(), toRetrieve));
        return null;
    }

    //This method deletes an airport in the BSt, and its LLL,
    //based on the ID ('PDX', 'LAX' ,etc) specified by the user.
    //It is the wrapper for the recursive method to do so
    public void deleteOne(String toDelete) {
        this.root = deleteOneWrap(this.root, toDelete);
    }

    //This is the recursive method for the above wrapper method
    private NodeTree deleteOneWrap(NodeTree root, String toDelete) {
        if (root == null)
            return root;
        if (root.dataID().equalsIgnoreCase(toDelete)) {
            if (root.returnLeft() == null && root.returnRight() == null) {
                root.deleteList();
                root = null;
            } else if (root.returnLeft() == null && root.returnRight() != null) {
                NodeTree temp = root.returnRight();
                root.deleteList();
                root = temp;
            } else if (root.returnLeft() != null && root.returnRight() == null) {
                NodeTree temp = root.returnLeft();
                root.deleteList();
                root = temp;
            } else if (root.returnLeft() != null && root.returnRight() != null) {
                root = findIOS(root);
            }
            return root;
        }
        deleteOneWrap(root.returnLeft(), toDelete);
        deleteOneWrap(root.returnRight(), toDelete);
        return root;
    }

    //This method recursively finds the in order successor
    //for the above method
    private NodeTree findIOS(NodeTree root) {
        if (root.returnLeft() == null) {
            NodeTree temp = root;
            root = root.returnRight();
            return temp;
        }
        findIOS(root.returnLeft());
        return root;
    }

    //This is the wrapper method to delete the entire BST
    public void deleteAll() {
        this.root = deleteAllWrap(this.root);
    }

    //This is the recursive method to delete the entire BST
    private NodeTree deleteAllWrap(NodeTree root) {
        if (root == null)
            return root;
        deleteAllWrap(root.returnLeft());
        deleteAllWrap(root.returnRight());
        root = null;
        return root;
    }

    //The next two methods are used to methods set the departure and arrival airports
    public NodeTree findStartFinish(String home, String dest, int min, int max){
        if(this.root == null){
            return null;
        } else {
            findStartFinish(this.root, home);
            findStartFinish(this.root, dest);
            if(this.temp == null){
                System.out.print("The departure city entered is not available \n\n");
                return null;
            } else if(this.temp.returnLeft() == null){
                System.out.print("The arrival city entered is not available \n\n");
                return null;
            }
            setConnections(this.root, this.temp, this.temp.returnLeft(), min, max);
            return this.temp;
        }
    }

    private void findStartFinish(NodeTree root, String dest){
        if(root == null){
            return;
        } else if(root.compareID(dest)){
            if(this.temp == null)
                this.temp = new NodeTree(root);
            else{
                NodeTree temp2 = new NodeTree(root);
                this.temp.connectLeft(temp2);
            }
            return;
        }
        findStartFinish(root.returnLeft(), dest);
        findStartFinish(root.returnRight(), dest);
        return;
    }

    //The next two methods create a flight plan checking the adjacnet options with teh minimum
    //and maximum flight parameters
    public void setConnections(NodeTree home, NodeTree dest, int min, int max){
       setConnections(this.root, home, dest, min, max);
    }

    public void setConnections(NodeTree root, NodeTree home, NodeTree dest, int min, int max) {
        if (root == null) {
            //this.temp = null;
            return;
        }if((root.isAdjacent(home) && root.compareWait(min, max)) && (root.isAdjacent(dest) && root.compareWait(min, max))){
            NodeTree temp = new NodeTree(root);
            temp.connectLeft(home.returnLeft());
            home.connectLeft(temp);
            //setFlight(this.temp, temp2);
        } else {
            setConnections(root.returnLeft(), home, dest, min, max);
            setConnections(root.returnRight(), home, dest, min, max);
        }
    }

    private void setFlight(NodeTree head, NodeTree toAdd){
        if(head == null){
            return;
        } else if(head.returnLeft() == null){
            toAdd.connectLeft(head);
            head = toAdd;
            return;
        }
        setFlight(head.returnLeft(), toAdd);
    }
}


/*****************************************************************************
 ** *******************Code for safe keeping**********************************
 ****************************************************************************/

/*public void addNodeWrap(ArrayList<String> toAdd) {
        this.addNode(this.root, toAdd, null);
}*/

/*private void displayNext(NodeTree root){
    if(root == null){
        return;
    } else {
        root.Display();
    }
    displayNext(root.returnNext());
}

private NodeTree createList(NodeTree root, NodeTree head, final FixData fixed) {
    if (head == null) {
        if(pickAdjacent(root, fixed)) {
            head = new NodeTree(fixed);
            head.connectNext(null);
        }
        return head;
    }
    root.connectNext(createList(root, head.returnNext(), fixed));
    return head;
}*/


/*private boolean pickAdjacent(NodeTree root, final FixData fixed) {
    if (root == null || fixed == null) {
        return false;
    } else if (root.dataID().equalsIgnoreCase(fixed.getID())){
        return false;
    }
    if (root.returnType().equalsIgnoreCase(fixed.getType())) {
        return true;
    } else if ((root.returnRank() == 1) && (root.returnRank() == fixed.getRa())) {
        return true;
    } else {
        return false;
    }
}*/


/*public Tree(Tree toCopy) {
    this.root = copyTree(toCopy.root);
}

public NodeTree copyTree(NodeTree addRoot) {
    if (addRoot == null) {
        this.root = null;
        return this.root;
    } else {
        this.root = new NodeTree(addRoot);
    }
    addRoot.connectLeft(copyTree(addRoot.returnLeft()));
    addRoot.connectRight(copyTree(addRoot.returnRight()));
    return addRoot;
}*/


/*if (fixed == null) {
    this.data = toAdd.toArray(new String[toAdd.size()]);
    for (int i = 0; i < this.data.length; ++i) {
        this.fixed = new FixData(this.data[i]);
        this.root = createTree(this.root, this.data, this.fixed);
    }
} else {
    this.root = createTree(root, this.data, fixed);
}*/
