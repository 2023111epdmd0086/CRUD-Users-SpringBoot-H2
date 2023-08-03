package main.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.main.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}