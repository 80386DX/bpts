package rs.digit.bpts.repos;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.digit.bpts.domain.Account;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findById(String id);

    List<Account> findAll();

}
