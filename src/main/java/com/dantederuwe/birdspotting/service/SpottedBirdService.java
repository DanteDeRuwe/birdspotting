package com.dantederuwe.birdspotting.service;

import com.dantederuwe.birdspotting.domain.BirdSpotLocation;
import java.util.List;
import java.util.Optional;

public interface SpottedBirdService {

    public List<BirdSpotLocation> findAll();

    public Optional<BirdSpotLocation> findByName(String name);
}