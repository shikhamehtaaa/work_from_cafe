package com.example.cafefinder;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CafeService {
    private final CafeRepository repository;

    public CafeService(CafeRepository repository) {   // depends on the interface, not the in-memory class
        this.repository = repository;
    }

    public List<Cafe> search(String neighborhood, Boolean wifi, Integer minOutlets, Integer maxNoise) {
        return repository.findAll().stream()
                .filter(c -> neighborhood == null || c.neighborhood().equalsIgnoreCase(neighborhood))
                .filter(c -> wifi == null || c.wifi() == wifi)
                .filter(c -> minOutlets == null || c.outlets() >= minOutlets)
                .filter(c -> maxNoise == null || c.noiseLevel() <= maxNoise)
                .toList();
    }
}