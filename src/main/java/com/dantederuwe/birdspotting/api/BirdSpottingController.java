package com.dantederuwe.birdspotting.api;

import com.dantederuwe.birdspotting.domain.BirdSpotLocation;
import com.dantederuwe.birdspotting.domain.SpottedBird;
import com.dantederuwe.birdspotting.service.SpottedBirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.tools.JavaFileManager;
import java.security.cert.CollectionCertStoreParameters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class BirdSpottingController {

    private static final String BASE_URI = "birdspotting";

    @Autowired
    private final SpottedBirdService birdService;

    public BirdSpottingController(SpottedBirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping(BASE_URI)
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
        model.addAttribute("path", BASE_URI);

        return "birdspotting";
    }

    @GetMapping(BASE_URI + "/{name}")
    @ResponseBody
    public String index(Model model, @PathVariable("name") String name){
        return name;
    }
}
