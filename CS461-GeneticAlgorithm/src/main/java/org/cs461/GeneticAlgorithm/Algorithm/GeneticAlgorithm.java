package org.cs461.GeneticAlgorithm.Algorithm;

import org.cs461.GeneticAlgorithm.Components.Course;
import org.cs461.GeneticAlgorithm.Components.Preferences;
import org.cs461.GeneticAlgorithm.Components.Room;
import org.cs461.GeneticAlgorithm.Components.Time;
import org.cs461.GeneticAlgorithm.Enums.Building;
import org.cs461.GeneticAlgorithm.Enums.ClassName;
import org.cs461.GeneticAlgorithm.Enums.Professor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GeneticAlgorithm {
    // master lists to refer back to to reset or whatnot
    public static ArrayList<Course> courseMasterList;
    public static ArrayList<Room> roomMasterList;
    public static ArrayList<Professor> professorMasterList;

    // master list of preferences
    public static Map<ClassName, Preferences> courseProfessorPreferences;

    // may need to create overloaded equals function so I can see if a given room is equal
    public static Map<Room, Time> roomSchedule;

    public static ArrayList<ArrayList<Course>> schedulePopulation;
    private Fitness fitness;


    public GeneticAlgorithm() {
        GeneticAlgorithm.courseMasterList = new ArrayList<>();
        GeneticAlgorithm.roomMasterList = new ArrayList<>();
        GeneticAlgorithm.professorMasterList = new ArrayList<>();

        GeneticAlgorithm.courseProfessorPreferences = new HashMap<>();
        GeneticAlgorithm.roomSchedule = new HashMap<>();
        GeneticAlgorithm.schedulePopulation = new ArrayList<>();

        fitness = new Fitness();
    }


    public void execute() {
        setup();

        // iterate until at least 100 generations and fitness target is met
        //     calculate fitness of each score
        //     perform crossover

        // print/return final results
    }

    public void crossover() {
        // softmax normalization
        // sort results
        // create cdf
        // choose members that are above random threshold

        // reproduce members that pass the vibe check
        // pass each member to mutate function
    }

    public void mutate() {
        // generate random number to mutate off of
        // modify members accordingly
    }

    public void setup() {
        // populating course master list
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS101A, null, null, null, null, null, 50));
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS101B, null, null, null, null, null, 50));
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS191A, null, null, null, null, null, 50));
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS191B, null, null, null, null, null, 50));
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS201, null, null, null, null, null, 50));
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS291, null, null, null, null, null, 50));
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS303, null, null, null, null, null, 60));
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS304, null, null, null, null, null, 25));
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS394, null, null, null, null, null, 20));
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS449, null, null, null, null, null, 60));
        GeneticAlgorithm.courseMasterList.add(new Course(ClassName.CS451, null, null, null, null, null, 100));

        // populating room master list
        GeneticAlgorithm.roomMasterList.add(new Room(Building.Katz, 3, 45));
        GeneticAlgorithm.roomMasterList.add(new Room(Building.FH, 216, 30));
        GeneticAlgorithm.roomMasterList.add(new Room(Building.Royall, 206, 75));
        GeneticAlgorithm.roomMasterList.add(new Room(Building.Royall, 201, 50));
        GeneticAlgorithm.roomMasterList.add(new Room(Building.FH, 310, 108));
        GeneticAlgorithm.roomMasterList.add(new Room(Building.Haag, 201, 60));
        GeneticAlgorithm.roomMasterList.add(new Room(Building.Haag, 301, 75));
        GeneticAlgorithm.roomMasterList.add(new Room(Building.MNLC, 325, 450));
        GeneticAlgorithm.roomMasterList.add(new Room(Building.Bloch, 119, 60));

        // populating professor master list
        GeneticAlgorithm.professorMasterList.add(Professor.Gharibi);
        GeneticAlgorithm.professorMasterList.add(Professor.Gladbach);
        GeneticAlgorithm.professorMasterList.add(Professor.Hare);
        GeneticAlgorithm.professorMasterList.add(Professor.Nait_Adbesselam);
        GeneticAlgorithm.professorMasterList.add(Professor.Shah);
        GeneticAlgorithm.professorMasterList.add(Professor.Song);
        GeneticAlgorithm.professorMasterList.add(Professor.Uddin);
        GeneticAlgorithm.professorMasterList.add(Professor.Xu);
        GeneticAlgorithm.professorMasterList.add(Professor.Zaman);
        GeneticAlgorithm.professorMasterList.add(Professor.Zein_el_Din);

        // populating preferred professor master list
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS101A, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Gharibi, Professor.Hare, Professor.Zein_el_Din), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam)));
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS101B, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Gharibi, Professor.Hare, Professor.Zein_el_Din), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam)));
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS191A, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Gharibi, Professor.Hare, Professor.Zein_el_Din), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam)));
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS191B, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Gharibi, Professor.Hare, Professor.Zein_el_Din), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam)));
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS201, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Hare, Professor.Zein_el_Din), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam, Professor.Song)));
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS291, new Preferences(Arrays.asList(Professor.Gharibi, Professor.Hare, Professor.Zein_el_Din, Professor.Song), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam, Professor.Shah, Professor.Xu)));
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS303, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Zein_el_Din, Professor.Hare), Arrays.asList(Professor.Zaman, Professor.Song, Professor.Shah)));
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS304, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Hare, Professor.Xu), Arrays.asList(Professor.Zaman, Professor.Song, Professor.Shah, Professor.Nait_Adbesselam, Professor.Uddin, Professor.Zein_el_Din)));
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS394, new Preferences(Arrays.asList(Professor.Xu, Professor.Song), Arrays.asList(Professor.Nait_Adbesselam, Professor.Zein_el_Din)));
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS449, new Preferences(Arrays.asList(Professor.Xu, Professor.Song, Professor.Shah), Arrays.asList(Professor.Zein_el_Din, Professor.Uddin)));
        GeneticAlgorithm.courseProfessorPreferences.put(ClassName.CS451, new Preferences(Arrays.asList(Professor.Xu, Professor.Song, Professor.Shah), Arrays.asList(Professor.Zein_el_Din, Professor.Uddin, Professor.Nait_Adbesselam, Professor.Hare)));

        // populating roomSchedule list
        for(Room room : roomMasterList) {
            roomSchedule.put(room, new Time());
        }

        // generate first generation
        for(int i = 0; i < 500; i++) {
            ArrayList<Course> schedule;

            for(/*course in course list*/) {
                // randomly assign instructor, room, and time
                // make sure expected enrollment is carried over
            }
            // add back to schedulePopulation
        }

        // TODO: verify courses are added to schedulePopulation correctly and are truly random
    }

    public void populateCourseProfessorPreferences() {

    }
}
