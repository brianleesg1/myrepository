package org.example.springjpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.*;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByEmplid(Long emplid);
}
