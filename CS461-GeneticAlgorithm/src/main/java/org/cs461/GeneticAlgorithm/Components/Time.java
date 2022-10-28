package org.cs461.GeneticAlgorithm.Components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Time {
    public Map<Integer, ArrayList<Object>> times;

    public Time() {
        times = new HashMap<>();

        times.put(1, new ArrayList<>());
        times.put(2, new ArrayList<>());
        times.put(3, new ArrayList<>());
        times.put(4, new ArrayList<>());
        times.put(5, new ArrayList<>());
        times.put(6, new ArrayList<>());
    }
}
