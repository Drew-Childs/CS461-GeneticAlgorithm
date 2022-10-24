package org.cs461.GeneticAlgorithm.components;

import java.util.ArrayList;

public class Course {
    public String courseName;
    public String instructor;
    public Room room;
    public int time;


    public Course(String courseName, String instructor, String building, int roomNumber, int roomSize, int time) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.room = new Room(building, roomNumber, roomSize);
        this.time = time;
    }
}
