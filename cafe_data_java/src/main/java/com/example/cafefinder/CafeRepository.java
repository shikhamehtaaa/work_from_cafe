package com.example.cafefinder;

import java.util.List;

public interface CafeRepository {
    List<Cafe> findAll();
}