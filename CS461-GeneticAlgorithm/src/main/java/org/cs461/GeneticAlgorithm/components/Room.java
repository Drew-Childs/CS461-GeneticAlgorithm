package org.cs461.GeneticAlgorithm.components;

public class Room {
    public String bulding;
    public int roomNumber;
    public int roomSize;
    public Time timeSlots;

    public Room() {
        timeSlots = new Time();
    }
}
