package org.cs461.GeneticAlgorithm.Components;

import org.cs461.GeneticAlgorithm.Enums.Professor;

import java.util.List;

public class Preferences {
    public List<Professor> preferredProfessors;
    public List<Professor> backupProfessors;

    public Preferences(List<Professor> preferredProfessors, List<Professor> backupProfessors) {
        this.preferredProfessors = preferredProfessors;
        this.backupProfessors = backupProfessors;
    }
}
