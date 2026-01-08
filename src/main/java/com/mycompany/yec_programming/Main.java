/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.yec_programming;

/**
 *
 * @author Katelyn Hopkins
 * 
 * Using results (in a csv file) from a public google form filled in by the public, 
 * read and report potential delays
 */

import java.util.*;
import java.io.*;

public class Main {
    private static ArrayList<Line> lines = new ArrayList<>();
    public static void main(String[] args) {
        String file = "";
        System.out.println("Hello! Welcome to the TTC delay checker");
        System.out.println("Please make sure to add all the lines needed and update the csv file.");
        while(true){
            Scanner key = new Scanner(System.in);
            System.out.println("What would you like to do?");
            System.out.println("1. Read results");
            System.out.println("2. Add a new line");
            System.out.println("3. Edit an existing line");
            System.out.println("4. Exit");
            System.out.print("Enter the number corresponding to your desired task: ");
            int input = key.nextInt();
            
            switch(input){
                case 1:
                    readResults(file);
                    break;
                case 2:
                    addLine();
                    break;
                case 3:
                    editLine();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }
   
    }
    
    public static void readResults(String filename){ //TODO
        /*
        FILE FORMAT:
        Timestamp,Line,First Station,Second Station,Time Taken
        */
        try {
            Scanner sc = new Scanner(new File(filename));
            sc.nextLine(); //remove junk
            //read file
            while(sc.hasNext()){
                String[] str = sc.nextLine().split(","); //read each element, seperated by commas
                String[] strippedStr = new String[str.length];
                
                for (int i = 0; i < str.length; i++){
                    //each element is encased in "", but we don't want that, so this line removes it
                    strippedStr[i] = str[i].replace("\"", "");
                }
                
                Line line = new Line(-1, new HashMap<String, Float>());
                for(Line i : lines){
                    if(i.getLineNum() == Integer.parseInt(strippedStr[1])){
                        float ogTime = i.getTime(strippedStr[2], strippedStr[3]);
                        float actualTime = Float.parseFloat(strippedStr[4]);
                        if(ogTime < actualTime + 3){
                            System.out.printf("Delay: between %s to %s, a delay of %.2f minutes.", strippedStr[2], strippedStr[3], actualTime - ogTime);
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    
    public static void addLine(){
        System.out.println("\n:: Creating a new Line ::");
        Scanner key = new Scanner(System.in);
        System.out.print("Enter the line number: ");
        int lineNum = Integer.parseInt(key.nextLine());
        
        boolean exists = false;
        for(Line i : lines){
            if(i.getLineNum() == lineNum){
                exists = true;
            }
        }
        
        if(exists){
            System.out.println("This line number is already in use...would you like to edit line " + lineNum + " instead? [y/n]: ");
            switch (key.nextLine().toLowerCase()){
                case "y":
                   editLine(lineNum);
                   return;
                case "n":
                    System.out.println("Okay...returning to title screen.\n");
                    return;
                default:
                    System.out.println("Not one of the given options...returning to title screen.\n");
                    return;
            }
        }
        else{
            System.out.print("Enter the number of stations: ");
            int numStats = Integer.parseInt(key.nextLine());
            HashMap<String, Float> stations = new HashMap<>();
            for(int i = 0; i < numStats; i++){
                System.out.print("Station " + (i+1) + " name: ");
                String s = key.nextLine();
                System.out.print("Time to get to station in minutes (from first stattion, where time is 0): ");
                float t = Float.parseFloat(key.nextLine());
                stations.put(s, t);
            }
        
            lines.add(new Line(lineNum, stations));
            System.out.println(":: Line Successfully Added! ::\n");
        }
    }
    
    public static void editLine(){
        System.out.println("\n:: Editing a Line ::");
        Scanner key = new Scanner(System.in);
        System.out.print("Enter the line number for the line you wish to edit: ");
        int lineNum = Integer.parseInt(key.nextLine());
        Line line = new Line(-1, new HashMap<String, Float>());
        int index = -1;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getLineNum() == lineNum){
                line = lines.get(i);
                index = i;
            }
        }
        
        if(index < 0){
            System.out.println("Line does not exist, please create a new one.");
            System.out.println("Returning to title screen...\n");
            return;
        }
        
        System.out.println("What would you like to do? ");
        System.out.println("1. Edit the line number");
        System.out.println("2. Delete this line");
        System.out.print("Enter the number corresponding to your desired task: ");
        switch (Integer.parseInt(key.nextLine())){
            case 1:
                System.out.print("What is the new number?: ");
                int n = Integer.parseInt(key.nextLine());
                System.out.print("Changing line number...");
                line.setLineNum(n);
                break;
            case 2:
                System.out.println("Deleting Selected Line...");
                lines.remove(line);
                System.out.println(":: Line deleted! ::\n");
                break;
            default: 
                System.out.println("Not a valid number...returning to title screen\n");
                return;
        }
    }
    
    public static void editLine(int lineNum){
        System.out.println("\n:: Editing a Line ::");
        Scanner key = new Scanner(System.in);

        Line line = new Line(-1, new HashMap<String, Float>());
        int index = -1;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getLineNum() == lineNum){
                line = lines.get(i);
                index = i;
            }
        }
        
        System.out.println("What would you like to do? ");
        System.out.println("1. Edit the line number");
        System.out.println("2. Delete this line");
        System.out.print("Enter the number corresponding to your desired task: ");
        switch (Integer.parseInt(key.nextLine())){
            case 1:
                System.out.print("What is the new number?: ");
                int n = Integer.parseInt(key.nextLine());
                System.out.print("Changing line number...");
                line.setLineNum(n);
                break;
            case 2:
                System.out.println("Deleting Selected Line...");
                lines.remove(line);
                System.out.println(":: Line deleted! ::\n");
                break;
            default: 
                System.out.println("Not a valid number...returning to title screen\n");
                return;
        }
    }
}
