package org.cs461.GeneticAlgorithm;

import org.cs461.GeneticAlgorithm.Algorithm.GeneticAlgorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Running...");

        GeneticAlgorithm testing = new GeneticAlgorithm();
        testing.execute();
    }
}