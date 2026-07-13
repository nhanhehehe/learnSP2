package hoc.tot.nhan.repository;

import hoc.tot.nhan.dto.request.LogoutRequest;
import hoc.tot.nhan.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedRepository extends JpaRepository<InvalidatedToken, String> {
}
