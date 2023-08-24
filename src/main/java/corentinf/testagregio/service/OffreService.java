package corentinf.testagregio.service;

import corentinf.testagregio.client.MarcheClient;
import corentinf.testagregio.model.dto.OffreDto;
import corentinf.testagregio.repository.OffreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OffreService {
    private final OffreRepository offreRepository;
    private final MarcheClient marcheClient;
    private final ParcService parcService;

    public Optional<OffreDto> saveOffre(OffreDto offre) {
        Optional<OffreDto> builtOffre = buildOffre(offre);


        if (builtOffre.isPresent() && marcheClient.sendOffreToMarche(offre)) {
            return Optional.of(offreRepository.save(offre));
        } else {
            return Optional.empty();
        }
    }


    /**
     * regarde dans l'ensemble des parcs les quantités d'énergie disponibles et crée l'offre en fonction
     * ajuste la quantiteEnergieAlouee des blocs horaire du parc utilisé pour placer l'offre
     * assigne le parc à l'offre
     *
     * @param offre
     * @return Optional(OffreDto) si l'offre est assumable, Optional.empty() sinon
     */
    private Optional<OffreDto> buildOffre(OffreDto offre) {
        var parcDto = parcService.findAll().get(0);
        offre.setParc(parcDto);
        return Optional.of(offre);
    }

    public List<OffreDto> findAll() {
        return offreRepository.findAll();
    }
}
