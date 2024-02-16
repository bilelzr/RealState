package tn.pi.realstate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pi.realstate.entities.Transaction;



@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
