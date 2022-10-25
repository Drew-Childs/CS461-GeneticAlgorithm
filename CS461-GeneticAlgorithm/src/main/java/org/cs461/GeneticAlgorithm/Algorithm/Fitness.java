package org.cs461.GeneticAlgorithm.Algorithm;

import org.cs461.GeneticAlgorithm.Components.Course;

public class Fitness {
    private Course course;

    public void calcFitness(Course course) {
        this.course = course;

        this.course.fitnessScore += calcRoomTimeFitness();
        this.course.fitnessScore += calcRoomSizeFitness();
        this.course.fitnessScore += calcInstructorQualifiedFitness();
        this.course.fitnessScore += calcInstructorLoadFitness();
        this.course.fitnessScore += calcTimeFitness();
    }

    public double calcRoomTimeFitness() {
        // check if course is scheduled at same time and room as another class
        // criteria 2

        return 0.0;
    }

    public double calcRoomSizeFitness() {
        // check if course is too small or larger than expected capacity
        // criteria 3

        return 0.0;
    }

    public double calcInstructorQualifiedFitness() {
        // check if taught by preferred, backup, or any instructor
        // criteria 4
        // criteria 5
        // criteria 6

        return 0.0;
    }

    public double calcInstructorLoadFitness() {
        // check how many classes instructor is scheduled for
        // criteria 7

        return 0.0;
    }

    public double calcTimeFitness() {
        // if CS 191 or CS 101
        //     assign fitness based on how far apart or close together classes are
        // course criteria 1-4, 6, 7

        // if CS 191 or CS 101 or professor with any consecutive class
        //     assign deduction if consecutive class is in different building
        // course criteria 5

        return 0.0;
    }
}
