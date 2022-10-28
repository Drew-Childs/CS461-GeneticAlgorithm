package org.cs461.GeneticAlgorithm.Algorithm;

import org.cs461.GeneticAlgorithm.Components.Course;
import org.cs461.GeneticAlgorithm.Components.Room;
import org.cs461.GeneticAlgorithm.Components.Time;
import org.cs461.GeneticAlgorithm.Enums.Building;
import org.cs461.GeneticAlgorithm.Enums.ClassName;
import org.cs461.GeneticAlgorithm.Enums.Professor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Fitness {
    private ArrayList<Course> courses;
    private Double fitnessScore;
    public static Map<Room, Time> roomSchedule;
    public static Map<Professor, Time> professorSchedule;

    public Double calcFitness(ArrayList<Course> courses) {
        this.courses = courses;
        this.fitnessScore = 0.0;

        // resetting and populating roomSchedule list
        roomSchedule = new HashMap<>();
        for (Room room : GeneticAlgorithm.roomMasterList) {
            roomSchedule.put(room, new Time());
        }

        // resetting and populating professorSchedule list
        professorSchedule = new HashMap<>();
        for (Professor professor : GeneticAlgorithm.professorMasterList) {
            professorSchedule.put(professor, new Time());
        }

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
            for (Map.Entry<Room, Time> schedule : roomSchedule.entrySet()) {
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
            for (Map.Entry<Professor, Time> schedule : professorSchedule.entrySet()) {
                if (course.instructor == schedule.getKey()) {
                    schedule.getValue().times.get(course.time).add(course.courseName);
                }
            }
        }

        for (Map.Entry<Professor, Time> schedule : professorSchedule.entrySet()) {
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
        Double score = 0.0;

        // initializing to random value or else I cannot use the variable later (even though it will be reassigned)
        Integer cs101aTime = 0;
        Integer cs101bTime = 0;
        Integer cs191aTime = 0;
        Integer cs191bTime = 0;

        // initializing to random value or else I cannot use the variable later (even though it will be reassigned)
        Building cs101aBuilding = Building.FH;
        Building cs101bBuilding = Building.FH;
        Building cs191aBuilding = Building.FH;
        Building cs191bBuilding = Building.FH;

        // gathering time and building for cs 101 and 191 sections
        for (Course course : courses) {
            if (course.courseName == ClassName.CS101A) {
                cs101aTime = course.time;
                cs101aBuilding = course.room.bulding;
            }
            else if (course.courseName == ClassName.CS101B) {
                cs101bTime = course.time;
                cs101bBuilding = course.room.bulding;
            }
            else if (course.courseName == ClassName.CS191A) {
                cs191aTime = course.time;
                cs191aBuilding = course.room.bulding;
            }
            else if (course.courseName == ClassName.CS191B) {
                cs191bTime = course.time;
                cs191bBuilding = course.room.bulding;
            }
        }


        // if more than 4 hours apart, only timeslots that would be feasible are 10 and 3
        if (Math.abs(cs101aTime - cs101bTime) > 4) {
            score += 0.5;
        }
        else if (cs101aTime == cs101bTime) {
            score -= 0.5;
        }

        // if more than 4 hours apart, only timeslots that would be feasible are 10 and 3
        if (Math.abs(cs191aTime - cs191bTime) > 4) {
            score += 0.5;
        }
        else if (cs191aTime == cs191bTime) {
            score -= 0.5;
        }


        // assigning bonus if section of 101 and 191 are in consecutive time slots, but deducting if they're in Katz or Bloch
        if (Math.abs(cs101aTime - cs191aTime) == 1) {
            if ((cs101aBuilding == Building.Bloch && cs191aBuilding == Building.Katz) || (cs101aBuilding == Building.Katz && cs191aBuilding == Building.Bloch)) {
                score -= 0.4;
            }
            else {
                score += 0.5;
            }
        }
        else if (Math.abs(cs101aTime - cs191aTime) == 2) {
            score += 0.25;
        }
        else if (Math.abs(cs101aTime - cs191aTime) == 0) {
            score -= 0.25;
        }

        if (Math.abs(cs101aTime - cs191bTime) == 1) {
            if ((cs101aBuilding == Building.Bloch && cs191bBuilding == Building.Katz) || (cs101aBuilding == Building.Katz && cs191bBuilding == Building.Bloch)) {
                score -= 0.4;
            }
            else {
                score += 0.5;
            }
        }
        else if (Math.abs(cs101aTime - cs191bTime) == 2) {
            score += 0.25;
        }
        else if (Math.abs(cs101aTime - cs191bTime) == 0) {
            score -= 0.25;
        }

        if (Math.abs(cs101bTime - cs191aTime) == 1) {
            if ((cs101bBuilding == Building.Bloch && cs191aBuilding == Building.Katz) || (cs101bBuilding == Building.Katz && cs191aBuilding == Building.Bloch)) {
                score -= 0.4;
            }
            else {
                score += 0.5;
            }
        }
        else if (Math.abs(cs101bTime - cs191aTime) == 2) {
            score += 0.25;
        }
        else if (Math.abs(cs101bTime - cs191aTime) == 0) {
            score -= 0.25;
        }

        if (Math.abs(cs101bTime - cs191bTime) == 1) {
            if ((cs101bBuilding == Building.Bloch && cs191bBuilding == Building.Katz) || (cs101bBuilding == Building.Katz && cs191bBuilding == Building.Bloch)) {
                score -= 0.4;
            }
            else {
                score += 0.5;
            }
        }
        else if (Math.abs(cs101bTime - cs191bTime) == 2) {
            score += 0.25;
        }
        else if (Math.abs(cs101bTime - cs191bTime) == 0) {
            score -= 0.25;
        }


        for (Map.Entry<Professor, Time> schedule : professorSchedule.entrySet()) {
            Boolean previousSlot = false;
            for (Map.Entry<Integer, ArrayList<Object>> time : schedule.getValue().times.entrySet()) {
                if (time.getValue().size() > 0) {
                    if (previousSlot) {
                        score += 0.5;
                    }
                    previousSlot = true;
                }
                else {
                    previousSlot = false;
                }
            }
        }

        return score;
    }
}
