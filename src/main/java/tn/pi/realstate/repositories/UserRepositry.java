package tn.pi.realstate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pi.realstate.entities.User;


@Repository
public interface UserRepositry extends JpaRepository<User,Long> {
}
