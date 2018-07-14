package com.company;

//Jospeh Starr, CS202, Program #4
//This module is the class object that reads in the data from the external data
//files and creates a tree out of it, returning the tree to be used.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextReader {
    private BufferedReader read1;
    private BufferedReader read2;
    private ArrayList<String> dataList;

    //The next three contructors create buffer readers to read in from the data files, store the data in
    //a list of string arrays, and create a try by calling the last method
    public TextReader(BufferedReader readIn, BufferedReader readIn2) {
        this.read1 = readIn;
        this.read2 = readIn2;
    }

    public TextReader(String fileName, String fileName2) throws FileNotFoundException {
        this(new FileReader(fileName), new FileReader(fileName2));
    }

    public TextReader(FileReader fileReader1, FileReader fileReader2) {
        this(new BufferedReader(fileReader1), new BufferedReader(fileReader2));
    }

    public ArrayList<String> returnList() {
        return this.dataList;
    }

    //This method turns the lists of data from the text files into a list of String Arrays used by the BSt,
    //then creates the BST, and returns the created BST
    public Tree readData(Tree toAddTo) {
        List<String> listTemp = read1.lines().collect(Collectors.toList());
        List<String> listTemp2 = read2.lines().collect(Collectors.toList());
        List<String> newList = Stream.concat(listTemp.stream(), listTemp2.stream()).collect(Collectors.toList());
        this.dataList = new ArrayList<String>(newList);
        Tree tempTree = new Tree(this.dataList);
        return tempTree;
    }
}
