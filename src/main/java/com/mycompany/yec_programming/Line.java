/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yec_programming;

/**
 *
 * @author Katelyn Hopkins
 */

import java.util.HashMap;

public class Line {
    private int lineNum;
    private HashMap<String, Float> stations;
    //using the first station of the line as reference, give time to get to the stations from there 
    //ex Station 0: 0min | Station 1: 2min | Station 2: 4min (2+2min) 
    public Line(int lineNum, HashMap<String, Float> stations){
        this.lineNum = lineNum;
        this.stations = stations;
    }
    public int getLineNum(){ return lineNum; }
    public HashMap<String, Float> getStationsMap(){ return stations; }
    public void setLineNum(int lineNum) { this.lineNum = lineNum; }
    public void setStationsMap(HashMap<String, Float> stations){ this.stations = stations; }
    public float getTime(String s1, String s2){ return stations.get(s2)-stations.get(s1); }
    
}
