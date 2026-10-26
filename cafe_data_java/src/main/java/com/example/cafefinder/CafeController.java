package com.example.cafefinder;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cafes")
@CrossOrigin(origins = "*")   // local dev only; restrict to your frontend's origin later
public class CafeController {
    private final CafeService service;

    public CafeController(CafeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cafe> list(@RequestParam(required = false) String neighborhood,
                           @RequestParam(required = false) Boolean wifi,
                           @RequestParam(required = false) Integer minOutlets,
                           @RequestParam(required = false) Integer maxNoise) {
        return service.search(neighborhood, wifi, minOutlets, maxNoise);
    }
}