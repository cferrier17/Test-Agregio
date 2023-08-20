package corentinf.testagregio.service;

import corentinf.testagregio.model.dto.ParcDto;
import corentinf.testagregio.repository.ParcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcService {
    private final ParcRepository parcRepository;

    public ParcDto saveParc(ParcDto parcDto) {
        return parcRepository.save(parcDto);
    }

    public List<ParcDto> findAll() {
        return parcRepository.findAll();
    }
}
