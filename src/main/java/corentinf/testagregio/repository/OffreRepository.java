package corentinf.testagregio.repository;

import corentinf.testagregio.model.dto.OffreDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepository extends JpaRepository<OffreDto, Long> {
}
