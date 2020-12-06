package com.dantederuwe.birdspotting.api;

import com.dantederuwe.birdspotting.domain.BirdSpotLocation;
import com.dantederuwe.birdspotting.domain.SpottedBird;
import com.dantederuwe.birdspotting.service.SpottedBirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @Autowired
    private final SpottedBirdService birdService;

    public BirdSpottingController(SpottedBirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping("birdspotting")
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
}
