package rs.digit.bpts.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.digit.bpts.domain.Transfer;

import java.util.Optional;


public interface TransferRepository extends JpaRepository<Transfer, Long> {

    Optional<Transfer> findById(Long id);

}
