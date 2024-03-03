package tn.pi.realstate.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.pi.realstate.dto.request.AddPropertyRequest;
import tn.pi.realstate.entities.Property;
import tn.pi.realstate.entities.User;
import tn.pi.realstate.repositories.PropertyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;


    public List<Property> findAllProperties() {
        return this.propertyRepository.findAll();
    }

    public Property addProperty(AddPropertyRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();
        log.info("ADDING NEW PROPERTY");
        Property property = Property.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
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
}
