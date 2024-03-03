package tn.pi.realstate.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.pi.realstate.dto.request.AddPropertyRequest;
import tn.pi.realstate.dto.response.PropertyResponse;
import tn.pi.realstate.services.PropertyService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("api/v1/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("add")
    public void addProperty(@RequestBody AddPropertyRequest addPropertyRequest) {
        propertyService.addProperty(addPropertyRequest);
    }


    @GetMapping("getAll")
    public ResponseEntity<List<PropertyResponse>> getAllProperties() {
        return new ResponseEntity<>(propertyService.findAllProperties(), HttpStatusCode.valueOf(200));
    }
}
