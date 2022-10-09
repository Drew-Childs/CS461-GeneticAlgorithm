package org.cs461.GeneticAlgorithm.components;

import java.util.ArrayList;

public class Instructor {
    String name;
    ArrayList<Room> rooms;
    ArrayList<Course> courses;
    Time availableTimes;

    public Instructor() {
        availableTimes = new Time();
    }
}
