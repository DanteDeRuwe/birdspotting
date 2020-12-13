package com.dantederuwe.birdspotting.api;

import com.dantederuwe.birdspotting.domain.BirdSpecie;
import com.dantederuwe.birdspotting.domain.BirdSpotLocation;
import com.dantederuwe.birdspotting.domain.SpottedBird;
import com.dantederuwe.birdspotting.service.SpottedBirdService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("birdspotting")
public class BirdSpottingController {

    private final SpottedBirdService birdService;

    public BirdSpottingController(SpottedBirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping("")
    public String index(Model model){
        var locations = birdService.findAll();

        var locationData = locations
                .stream()
                .collect(
                        Collectors.toMap(
                                // Name of the location
                                BirdSpotLocation::getName,
                                // String representation of list of birdnames
                                l -> l.getSpottedBirds().stream().map(SpottedBird::toString).collect(Collectors.toList()).toString()
                        )
                );

        model.addAttribute("locationData", locationData);

        return "birdspotting";
    }

    @GetMapping("{locationName}")
    public String location(@PathVariable("locationName") String locationName, Model model) {
        var location = getLocation(locationName);
        model.addAttribute("location", location);
        return "birdspotting_location";
    }

    @GetMapping("{locationName}/create-new-spotting")
    public String create(@PathVariable("locationName") String locationName, Model model) {
        var location = getLocation(locationName);
        model.addAttribute("location", location);
        model.addAttribute("birdSpecie", new BirdSpecie("Specie", 2020, "AA000"));
        return "birdspotting_create-new-spotting";
    }

    @PostMapping("{locationName}/create-new-spotting")
    public String add(@PathVariable("locationName") String locationName, @Valid @ModelAttribute("birdSpecie") BirdSpecie birdSpecie, BindingResult result, Model model) {
        var location = getLocation(locationName);
        model.addAttribute("location", location);

        // reload the form in case of validation errors
        if(result.hasErrors()) return "birdspotting_create-new-spotting";

        location.increaseBirdSpot(birdSpecie);
        return "redirect:";
    }

    private BirdSpotLocation getLocation(String locationName) {
        var location = birdService.findByName(locationName);
        if(location.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return location.get();
    }
}
