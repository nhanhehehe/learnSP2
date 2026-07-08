package hoc.tot.nhan.repository;

import hoc.tot.nhan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// <User, Integer> lam viec voi entity user va id (primary key) la String
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String name);
    Optional<User> findByUsername(String username);
}
