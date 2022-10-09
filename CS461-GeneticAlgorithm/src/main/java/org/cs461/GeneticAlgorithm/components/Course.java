package org.cs461.GeneticAlgorithm.components;

import java.util.ArrayList;

public class Course {
    public ArrayList<String> preferredInstructors;
    public ArrayList<String> backUpInstructors;
    public int expectedEnrollment;
    public String courseName;

    // unsure about room still and if it is relevant here?
    public Room room;


    public Course(ArrayList<String> preferredInstructors, ArrayList<String> backUpInstructors, int expectedEnrollment, String courseName) {
        this.preferredInstructors = preferredInstructors;
        this.backUpInstructors = backUpInstructors;
        this.expectedEnrollment = expectedEnrollment;
        this.courseName = courseName;
    }
}
