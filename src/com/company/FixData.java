package com.company;

//Joseph Starr, CS202, Program #4
//This module is the class object that is used to recieve the data from the eternal data file
//and give its contents to the hierarchy

public class FixData {
    private int ra;
    private String ID;
    private String Ci;
    private String St;
    private int lay;
    private int del;
    private int privateF;
    private String topE;
    private boolean we;
    private int bag;
    private String type;

    public int getRa() {
        return this.ra;
    }

    public String getID() {
        return this.ID;
    }

    public String getCi() {
        return this.Ci;
    }

    public String getSt() {
        return this.St;
    }

    public int getLay() {
        return this.lay;
    }

    public int getDel() {
        return this.del;
    }

    public int getprivate() {
        return this.privateF;
    }

    public String getTop() {
        return this.topE;
    }

    public Boolean getWe() {
        return this.we;
    }

    public int getBag() {
        return this.bag;
    }

    public String getType() {
        return this.type;
    }

    public FixData(String toFix) {
        splitString(toFix);
    }

    //This method turns the string in the argument into an array of strings where each index is part of
    //the string being passed in up to the next delimeter
    private void splitString(String toSplit) {
        String[] splitString = toSplit.split("\\|");
        this.ra = Integer.parseInt(splitString[0]);
        this.ID = splitString[1];
        this.Ci = splitString[2];
        this.St = splitString[3];
        this.lay = Integer.parseInt(splitString[4]);
        if (splitString.length == 7) {
            this.type = "Domestic";
            this.we = Boolean.parseBoolean(splitString[5]);
            this.bag = Integer.parseInt(splitString[6]);
        } else if (splitString.length == 8) {
            this.type = "International";
            this.del = Integer.parseInt(splitString[5]);
            this.privateF = Integer.parseInt(splitString[6]);
            this.topE = splitString[7];
        } else {
            System.out.print("Something is wrong \n");
        }
    }

    //This display method is only used for testing purposes
    public void Display() {
        System.out.print("Rank: " + this.ra + "\n" + "ID: " + this.ID + "\n" + "City: " + Ci + "\n" + "State: " + St + "\n");
        System.out.print("Layover: " + lay + "\n");
        //System.out.print("Delay time: " + this.del +"\n" + "Private: " + this.privateF + "\n" + "Top: " + this.topE + "\n\n");
        System.out.print("Weather delay: " + this.we + "\n" + "Baggage: " + this.bag + "\n\n");
    }
}
