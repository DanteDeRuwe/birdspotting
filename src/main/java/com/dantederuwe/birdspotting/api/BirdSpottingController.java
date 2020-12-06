package com.dantederuwe.birdspotting.api;

import com.dantederuwe.birdspotting.domain.BirdSpotLocation;
import com.dantederuwe.birdspotting.domain.SpottedBird;
import com.dantederuwe.birdspotting.exceptions.NotFoundException;
import com.dantederuwe.birdspotting.service.SpottedBirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BirdSpottingController {

    private static final String CONTROLLER_NAME = "birdspotting";
    private static final String ADD_ACTION = "create-new-spotting";

    @Autowired
    private final SpottedBirdService birdService;

    public BirdSpottingController(SpottedBirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping(CONTROLLER_NAME)
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
        model.addAttribute("path", CONTROLLER_NAME);

        return "birdspotting";
    }

    @GetMapping(CONTROLLER_NAME + "/{locationName}")
    public String location(Model model, @PathVariable("locationName") String locationName) throws NotFoundException {

        var location = birdService.findByName(locationName);
        if(location.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        model.addAttribute("location", location.get());
        model.addAttribute("indexPath", CONTROLLER_NAME);
        model.addAttribute("addAction", ADD_ACTION);
        model.addAttribute("path", CONTROLLER_NAME + "/" + locationName);

        return "birdspotting_location";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Model model, Exception e){
        model.addAttribute("exception", e);
        return "error";
    }
}
