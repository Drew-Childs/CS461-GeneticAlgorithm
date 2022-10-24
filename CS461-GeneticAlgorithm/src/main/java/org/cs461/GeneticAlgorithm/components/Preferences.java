package org.cs461.GeneticAlgorithm.components;

import java.util.ArrayList;

public class Preferences {
    public ArrayList<String> preferredProfessors;
    public ArrayList<String> backupProfessors;
    public int expectedEnrollment;

    public Preferences(int expectedEnrollment) {
        this.preferredProfessors = new ArrayList<>();
        this.backupProfessors = new ArrayList<>();
        this.expectedEnrollment = expectedEnrollment;
    }
}
