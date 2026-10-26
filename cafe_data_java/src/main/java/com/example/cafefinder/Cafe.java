package com.example.cafefinder;

public record Cafe(
        long id,
        String name,
        String neighborhood,
        boolean wifi,
        int outlets,
        int noiseLevel,
        String notes) {}

