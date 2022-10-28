package org.cs461.GeneticAlgorithm.Algorithm;

import org.cs461.GeneticAlgorithm.Components.Course;
import org.cs461.GeneticAlgorithm.Components.Preferences;
import org.cs461.GeneticAlgorithm.Components.Room;
import org.cs461.GeneticAlgorithm.Components.Time;
import org.cs461.GeneticAlgorithm.Enums.Building;
import org.cs461.GeneticAlgorithm.Enums.ClassName;
import org.cs461.GeneticAlgorithm.Enums.Professor;

import java.util.*;

public class GeneticAlgorithm {
    // master lists to refer back to to reset or whatnot
    public static ArrayList<Course> courseMasterList;
    public static ArrayList<Room> roomMasterList;
    public static ArrayList<Professor> professorMasterList;

    // master list of preferences
    public static Map<ClassName, Preferences> courseProfessorPreferences;

    public static Map<ArrayList<Course>, Double> schedulePopulation;
    private Fitness fitness;


    public GeneticAlgorithm() {
        courseMasterList = new ArrayList<>();
        roomMasterList = new ArrayList<>();
        professorMasterList = new ArrayList<>();

        courseProfessorPreferences = new HashMap<>();
        schedulePopulation = new HashMap<>();

        fitness = new Fitness();
    }


    public void execute() {
        setup();

        for (Map.Entry<ArrayList<Course>, Double> schedule : schedulePopulation.entrySet()) {
            schedule.setValue(fitness.calcFitness(schedule.getKey()));
        }

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
        courseMasterList.add(new Course(ClassName.CS101A, null, null, null, null, null, 50));
        courseMasterList.add(new Course(ClassName.CS101B, null, null, null, null, null, 50));
        courseMasterList.add(new Course(ClassName.CS191A, null, null, null, null, null, 50));
        courseMasterList.add(new Course(ClassName.CS191B, null, null, null, null, null, 50));
        courseMasterList.add(new Course(ClassName.CS201, null, null, null, null, null, 50));
        courseMasterList.add(new Course(ClassName.CS291, null, null, null, null, null, 50));
        courseMasterList.add(new Course(ClassName.CS303, null, null, null, null, null, 60));
        courseMasterList.add(new Course(ClassName.CS304, null, null, null, null, null, 25));
        courseMasterList.add(new Course(ClassName.CS394, null, null, null, null, null, 20));
        courseMasterList.add(new Course(ClassName.CS449, null, null, null, null, null, 60));
        courseMasterList.add(new Course(ClassName.CS451, null, null, null, null, null, 100));

        // populating room master list
        roomMasterList.add(new Room(Building.Katz, 3, 45));
        roomMasterList.add(new Room(Building.FH, 216, 30));
        roomMasterList.add(new Room(Building.Royall, 206, 75));
        roomMasterList.add(new Room(Building.Royall, 201, 50));
        roomMasterList.add(new Room(Building.FH, 310, 108));
        roomMasterList.add(new Room(Building.Haag, 201, 60));
        roomMasterList.add(new Room(Building.Haag, 301, 75));
        roomMasterList.add(new Room(Building.MNLC, 325, 450));
        roomMasterList.add(new Room(Building.Bloch, 119, 60));

        // populating professor master list
        professorMasterList.add(Professor.Gharibi);
        professorMasterList.add(Professor.Gladbach);
        professorMasterList.add(Professor.Hare);
        professorMasterList.add(Professor.Nait_Adbesselam);
        professorMasterList.add(Professor.Shah);
        professorMasterList.add(Professor.Song);
        professorMasterList.add(Professor.Uddin);
        professorMasterList.add(Professor.Xu);
        professorMasterList.add(Professor.Zaman);
        professorMasterList.add(Professor.Zein_el_Din);

        // populating preferred professor master list
        courseProfessorPreferences.put(ClassName.CS101A, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Gharibi, Professor.Hare, Professor.Zein_el_Din), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam)));
        courseProfessorPreferences.put(ClassName.CS101B, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Gharibi, Professor.Hare, Professor.Zein_el_Din), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam)));
        courseProfessorPreferences.put(ClassName.CS191A, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Gharibi, Professor.Hare, Professor.Zein_el_Din), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam)));
        courseProfessorPreferences.put(ClassName.CS191B, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Gharibi, Professor.Hare, Professor.Zein_el_Din), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam)));
        courseProfessorPreferences.put(ClassName.CS201, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Hare, Professor.Zein_el_Din), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam, Professor.Song)));
        courseProfessorPreferences.put(ClassName.CS291, new Preferences(Arrays.asList(Professor.Gharibi, Professor.Hare, Professor.Zein_el_Din, Professor.Song), Arrays.asList(Professor.Zaman, Professor.Nait_Adbesselam, Professor.Shah, Professor.Xu)));
        courseProfessorPreferences.put(ClassName.CS303, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Zein_el_Din, Professor.Hare), Arrays.asList(Professor.Zaman, Professor.Song, Professor.Shah)));
        courseProfessorPreferences.put(ClassName.CS304, new Preferences(Arrays.asList(Professor.Gladbach, Professor.Hare, Professor.Xu), Arrays.asList(Professor.Zaman, Professor.Song, Professor.Shah, Professor.Nait_Adbesselam, Professor.Uddin, Professor.Zein_el_Din)));
        courseProfessorPreferences.put(ClassName.CS394, new Preferences(Arrays.asList(Professor.Xu, Professor.Song), Arrays.asList(Professor.Nait_Adbesselam, Professor.Zein_el_Din)));
        courseProfessorPreferences.put(ClassName.CS449, new Preferences(Arrays.asList(Professor.Xu, Professor.Song, Professor.Shah), Arrays.asList(Professor.Zein_el_Din, Professor.Uddin)));
        courseProfessorPreferences.put(ClassName.CS451, new Preferences(Arrays.asList(Professor.Xu, Professor.Song, Professor.Shah), Arrays.asList(Professor.Zein_el_Din, Professor.Uddin, Professor.Nait_Adbesselam, Professor.Hare)));

        // generate first generation
        ArrayList<Integer> times = new ArrayList<>();
        times.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        Random rand = new Random();

        for (int i = 0; i < 500; i++) {
            ArrayList<Course> schedule = new ArrayList<>();

            for (Course course : courseMasterList) {
                ClassName className = course.courseName;
                Professor professor = professorMasterList.get(rand.nextInt(professorMasterList.size()));
                Integer time = times.get(rand.nextInt(times.size()));
                Integer expectedEnrollment = course.expectedEnrollment;

                Integer buildingSeed = rand.nextInt(roomMasterList.size());
                Building building = roomMasterList.get(buildingSeed).bulding;
                Integer roomNumber = roomMasterList.get(buildingSeed).roomNumber;
                Integer roomSize = roomMasterList.get(buildingSeed).roomSize;

                schedule.add(new Course(className, professor, building, roomNumber, roomSize, time, expectedEnrollment));
            }
            schedulePopulation.put(schedule, 0.0);
        }
    }
}
