package tn.pi.realstate.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.pi.realstate.dto.request.AddPropertyRequest;
import tn.pi.realstate.dto.response.PropertyResponse;
import tn.pi.realstate.entities.Property;
import tn.pi.realstate.entities.Status;
import tn.pi.realstate.entities.User;
import tn.pi.realstate.repositories.PropertyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;


    public List<PropertyResponse> findAllProperties() {
        List<PropertyResponse> propertyResponseList = new ArrayList<>();
        List<Property> properties = propertyRepository.findAll();
        properties.forEach(property ->
                propertyResponseList.add(convertToDto(property))
        );
        return propertyResponseList;
    }

    public Property addProperty(AddPropertyRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();
        log.info("ADDING NEW PROPERTY");
        Property property = Property.builder()
                .title(request.getTitle())
                .location(request.getLocation())
                .description(request.getDescription())
                .status(Objects.equals(request.getStatus(), "1") ? Status.SALE : Status.RENT)
                .price(request.getPrice())
                .user(authenticatedUser)
                .build();
        return propertyRepository.save(property);
    }

    public void deleteProperty(long idProperty) {
        Optional<Property> property = propertyRepository.findById(idProperty);
        if (property.isPresent()) {
            log.info("Deleting property with id {}", property.get().getId());
            propertyRepository.delete(property.get());
        } else {
            log.error("CANNOT FIND PROPERTY WITH ID {}", idProperty);
        }
    }


    public PropertyResponse convertToDto(Property property) {
        return PropertyResponse.builder()
                .location(property.getLocation())
                .title(property.getTitle())
                .price(property.getPrice())
                .status(String.valueOf(property.getStatus()))
                .description(property.getDescription())
                .build();
    }
}
