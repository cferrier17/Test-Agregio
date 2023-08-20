package corentinf.testagregio.repository;

import corentinf.testagregio.model.dto.ParcDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcRepository extends JpaRepository<ParcDto, Long> {
}
