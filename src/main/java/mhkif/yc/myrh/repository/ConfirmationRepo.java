package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.model.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepo extends JpaRepository<Confirmation, Long> {
    Confirmation findByToken(String token);
}
