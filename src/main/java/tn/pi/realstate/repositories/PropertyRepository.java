package tn.pi.realstate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pi.realstate.entities.Property;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
