package org.cs461.GeneticAlgorithm.Algorithm;

import org.cs461.GeneticAlgorithm.Components.Course;
import org.cs461.GeneticAlgorithm.Components.Preferences;
import org.cs461.GeneticAlgorithm.Components.Room;
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
    public static Map<Integer, Double> generationFitness;
    private static Integer generation;
    private Fitness fitness;
    private static Random rand = new Random();


    public GeneticAlgorithm() {
        courseMasterList = new ArrayList<>();
        roomMasterList = new ArrayList<>();
        professorMasterList = new ArrayList<>();

        courseProfessorPreferences = new HashMap<>();
        schedulePopulation = new HashMap<>();
        generationFitness = new HashMap<>();

        fitness = new Fitness();

        generation = 0;
    }


    public void execute() {
        setup();

        for (Map.Entry<ArrayList<Course>, Double> schedule : schedulePopulation.entrySet()) {
            schedule.setValue(fitness.calcFitness(schedule.getKey()));
        }

        crossover();

        // iterate until at least 100 generations and fitness target is met
        //     calculate fitness of each score
        //     perform crossover

        // print/return final results
    }

    public void crossover() {
        Double fitnessSum = 0.0;
        Double previous = 0.0;
        LinkedHashMap<ArrayList<Course>, Double> softmax = new LinkedHashMap<>();
        LinkedHashMap<ArrayList<Course>, Double> cdf = new LinkedHashMap<>();

        schedulePopulation.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(schedule -> softmax.put(schedule.getKey(), Math.exp(schedule.getValue())));

        for (Map.Entry<ArrayList<Course>, Double> schedule : softmax.entrySet()) {
            fitnessSum += schedule.getValue();
        }

        for (Map.Entry<ArrayList<Course>, Double> schedule : softmax.entrySet()) {
            cdf.put(schedule.getKey(), (schedule.getValue() / fitnessSum) + previous);
            previous += schedule.getValue() / fitnessSum;
        }

        generationFitness.put(generation, fitnessSum / schedulePopulation.size());
        generation += 1;

        ArrayList<ArrayList<Course>> parents = new ArrayList<>();
        ArrayList<Course> childOne = new ArrayList<>();
        ArrayList<Course> childTwo = new ArrayList<>();
        schedulePopulation = new HashMap<>();

        for (int i = 0; i < 250; i++) {
            for (Map.Entry<ArrayList<Course>, Double> schedule : cdf.entrySet()) {
                Double percentile = rand.nextDouble(0, 1);

                if (schedule.getValue() > percentile) {
                    parents.add(schedule.getKey());
                }

                if (parents.size() == 2) {
                    break;
                }
            }

            Integer split = rand.nextInt(0, 11);

            childOne.addAll(parents.get(0).subList(0, split));
            childOne.addAll(parents.get(1).subList(split, 11));

            childTwo.addAll(parents.get(1).subList(0, split));
            childTwo.addAll(parents.get(0).subList(split, 11));

            schedulePopulation.put(mutate(childOne), 0.0);
            schedulePopulation.put(mutate(childTwo), 0.0);

            parents.clear();
            childOne.clear();
            childTwo.clear();
        }

        // reset any data structures?
    }

    public ArrayList<Course> mutate(ArrayList<Course> child) {
        if (rand.nextInt(1, 100) == 1) {
            Integer index = rand.nextInt(0, 11);
            ArrayList<Integer> times = new ArrayList<>();
            times.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));

            ClassName className = child.get(index).courseName;
            Professor professor = professorMasterList.get(rand.nextInt(professorMasterList.size()));
            Integer time = times.get(rand.nextInt(times.size()));
            Integer expectedEnrollment = child.get(index).expectedEnrollment;

            Integer buildingSeed = rand.nextInt(roomMasterList.size());
            Building building = roomMasterList.get(buildingSeed).bulding;
            Integer roomNumber = roomMasterList.get(buildingSeed).roomNumber;
            Integer roomSize = roomMasterList.get(buildingSeed).roomSize;


            child.set(index, new Course(className, professor, building, roomNumber, roomSize, time, expectedEnrollment));
        }

        return child;
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
