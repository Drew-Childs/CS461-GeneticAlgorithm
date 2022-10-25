package org.cs461.GeneticAlgorithm.Components;

import org.cs461.GeneticAlgorithm.Enums.Building;
import org.cs461.GeneticAlgorithm.Enums.ClassName;
import org.cs461.GeneticAlgorithm.Enums.Professor;

public class Course {
    public ClassName courseName;
    public Professor instructor;
    public Room room;
    public Integer time;
    public Integer expectedEnrollment;
    public double fitnessScore;


    public Course(ClassName courseName, Professor instructor, Building building, Integer roomNumber, Integer roomSize, Integer time, Integer expectedEnrollment) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.room = new Room(building, roomNumber, roomSize);
        this.time = time;
        this.expectedEnrollment = expectedEnrollment;
        this.fitnessScore = 0;
    }
}
