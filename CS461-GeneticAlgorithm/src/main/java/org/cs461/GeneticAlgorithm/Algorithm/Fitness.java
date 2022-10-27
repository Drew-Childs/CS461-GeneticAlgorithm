package org.cs461.GeneticAlgorithm.Algorithm;

import org.cs461.GeneticAlgorithm.Components.Course;
import org.cs461.GeneticAlgorithm.Components.Room;
import org.cs461.GeneticAlgorithm.Components.Time;
import org.cs461.GeneticAlgorithm.Enums.Professor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Fitness {
    private ArrayList<Course> courses;
    private Double fitnessScore;

    public Double calcFitness(ArrayList<Course> courses) {
        this.courses = courses;
        this.fitnessScore = 0.0;

        this.fitnessScore += calcRoomTimeFitness();
        this.fitnessScore += calcRoomSizeFitness();
        this.fitnessScore += calcInstructorQualifiedFitness();
        this.fitnessScore += calcInstructorLoadFitness();
        this.fitnessScore += calcTimeFitness();

        return this.fitnessScore;
    }

    private double calcRoomTimeFitness() {
        Double score = 0.0;

        // check if course is scheduled at same time and room as another class
        for (Course course : courses) {
            for (Map.Entry<Room, Time> schedule : GeneticAlgorithm.roomSchedule.entrySet()) {
                if (course.room.bulding == schedule.getKey().bulding && course.room.roomNumber == schedule.getKey().roomNumber) {
                    schedule.getValue().times.get(course.time).add(course.courseName);
                    if (schedule.getValue().times.get(course.time).size() > 1) {
                        score -= 0.5;
                    }
                }
            }
        }
        return score;
    }

    private double calcRoomSizeFitness() {
        Double score = 0.0;

        // check if course is too small or larger than expected capacity
        for (Course course : courses) {
            if (course.room.roomSize < course.expectedEnrollment) {
                score -= 0.5;
            }
            else if (course.room.roomSize > course.expectedEnrollment * 6) {
                score -= 0.4;
            }
            else if (course.room.roomSize > course.expectedEnrollment * 3) {
                score -= 0.2;
            }
            else {
                score += 0.3;
            }
        }

        return score;
    }

    private double calcInstructorQualifiedFitness() {
        Double score = 0.0;

        // check if taught by preferred, backup, or any instructor
        for (Course course : courses) {
            if (GeneticAlgorithm.courseProfessorPreferences.get(course.courseName).preferredProfessors.contains(course.instructor)) {
                score += 0.5;
            }
            else if (GeneticAlgorithm.courseProfessorPreferences.get(course.courseName).backupProfessors.contains(course.instructor)) {
                score += 0.2;
            }
            else {
                score -= 0.1;
            }
        }

        return score;
    }

    private double calcInstructorLoadFitness() {
        Double score = 0.0;

        // check how many classes instructor is scheduled for
        for (Course course : courses) {
            for (Map.Entry<Professor, Time> schedule : GeneticAlgorithm.professorSchedule.entrySet()) {
                if (course.instructor == schedule.getKey()) {
                    schedule.getValue().times.get(course.time).add(course.courseName);
                }
            }
        }

        for (Map.Entry<Professor, Time> schedule : GeneticAlgorithm.professorSchedule.entrySet()) {
            Integer classesTaught = 0;

            for (Map.Entry<Integer, ArrayList<Object>> time : schedule.getValue().times.entrySet()) {
                if (time.getValue().size() == 1) {
                    score += 0.2;
                }
                else if (time.getValue().size() > 1){
                    score -= 0.2;
                }

                classesTaught += time.getValue().size();
            }

            if (classesTaught > 4) {
                score -= 0.5;
            }
            else if ((classesTaught ==1 || classesTaught == 2) && schedule.getKey() != Professor.Xu) {
                score -= 0.4;
            }
        }

        return score;
    }

    private double calcTimeFitness() {
        // if CS 191 or CS 101
        //     assign fitness based on how far apart or close together classes are
        // course criteria 1-4, 6, 7

        // if CS 191 or CS 101 or professor with any consecutive class
        //     assign deduction if consecutive class is in different building
        // course criteria 5

        return 1.0;
    }
}
