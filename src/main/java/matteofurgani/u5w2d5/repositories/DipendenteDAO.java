package matteofurgani.u5w2d5.repositories;

import matteofurgani.u5w2d5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteDAO extends JpaRepository<Dipendente, Integer> {
    Optional<Dipendente> findByEmail(String email);
}


