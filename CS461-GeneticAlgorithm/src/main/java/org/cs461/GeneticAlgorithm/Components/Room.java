package org.cs461.GeneticAlgorithm.Components;

import org.cs461.GeneticAlgorithm.Enums.Building;

public class Room {
    public Building bulding;
    public Integer roomNumber;
    public Integer roomSize;

    public Room(Building building, Integer roomNumber, Integer roomSize) {
        this.bulding = building;
        this.roomNumber = roomNumber;
        this.roomSize = roomSize;
    }
}
