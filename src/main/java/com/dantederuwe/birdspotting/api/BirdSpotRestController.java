package com.dantederuwe.birdspotting.api;
import com.dantederuwe.birdspotting.domain.BirdSpotLocation;
import com.dantederuwe.birdspotting.domain.SpottedBird;
import com.dantederuwe.birdspotting.service.SpottedBirdService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("birds-spotted")
public class BirdSpotRestController {

    private final SpottedBirdService birdService;

    public BirdSpotRestController(SpottedBirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping("{locationName}")
    public List<SpottedBird> location(@PathVariable("locationName") String locationName, Model model) {

        var location = getLocation(locationName);
        model.addAttribute("location", location);
        return location.getSpottedBirds();
    }

    private BirdSpotLocation getLocation(String locationName) {
        var location = birdService.findByName(locationName);
        if(location.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return location.get();
    }
}