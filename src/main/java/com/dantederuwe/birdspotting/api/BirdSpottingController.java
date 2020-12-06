package com.dantederuwe.birdspotting.api;

import com.dantederuwe.birdspotting.domain.BirdSpotLocation;
import com.dantederuwe.birdspotting.domain.SpottedBird;
import com.dantederuwe.birdspotting.service.SpottedBirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("birdspotting")
public class BirdSpottingController {

    @Autowired
    private final SpottedBirdService birdService;

    public BirdSpottingController(SpottedBirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping("")
    public String index(Model model){

        var locations = birdService.findAll();

        Map<String, String> locationData = locations
                .stream()
                .collect(
                        Collectors.toMap(
                                BirdSpotLocation::getName,
                                l -> l.getSpottedBirds().stream().map(SpottedBird::toString).collect(Collectors.toList()).toString()
                        )
                );

        model.addAttribute("locationData", locationData);

        return "birdspotting";
    }

    @GetMapping("{locationName}")
    public String location(@PathVariable("locationName") String locationName, Model model) {

        var location = birdService.findByName(locationName);
        if(location.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        model.addAttribute("location", location.get());

        return "birdspotting_location";
    }


    @GetMapping("{locationName}/create-new-spotting")
    @ResponseBody
    public String create(@PathVariable("locationName") String locationName, Model model) {
        return "You tried to add a spotting to "+ locationName;
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Model model, Exception e){
        model.addAttribute("exception", e);
        return "error";
    }
}
