package com.example.cafefinder;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCafeRepository implements CafeRepository {
    private final List<Cafe> cafes = List.of(
            new Cafe(1, "Savor Cafe", "Upper West Side", true, 5, 2, "Good food, laptop friendly"),
            new Cafe(2, "Gregory Coffee", "Midtown", true, 2, 4, "Loud music, no restroom"),
            new Cafe(3, "Qahwah House", "Morningside Heights", true, 10, 3, "Homey atmosphere, ok food"));

    @Override
    public List<Cafe> findAll() {
        return cafes;
    }
}