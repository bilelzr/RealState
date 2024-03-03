package tn.pi.realstate.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.pi.realstate.dto.request.AddPropertyRequest;
import tn.pi.realstate.services.PropertyService;


@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;


    @PostMapping
    public void addProperty(@RequestBody AddPropertyRequest addPropertyRequest) {
        propertyService.addProperty(addPropertyRequest);
    }
}
