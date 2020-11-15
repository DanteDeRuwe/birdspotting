package com.dantederuwe.birdspotting.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BirdSpottingController {

    @GetMapping("birdspotting")
    public String index(){
        return "birdspotting";
    }
}
