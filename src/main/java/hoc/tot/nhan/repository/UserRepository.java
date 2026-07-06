package hoc.tot.nhan.repository;

import hoc.tot.nhan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// <User, Integer> lam viec voi entity user va id (primary key) la String
public interface UserRepository extends JpaRepository<User, String> {
}
