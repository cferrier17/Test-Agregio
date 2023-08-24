package corentinf.testagregio.service;

import corentinf.testagregio.client.MarcheClient;
import corentinf.testagregio.model.TypeMarche;
import corentinf.testagregio.model.dto.OffreDto;
import corentinf.testagregio.model.dto.ParcDto;
import corentinf.testagregio.repository.OffreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

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
        var allParcs = parcService.findAll();
        var parcDto = allParcs.get(ThreadLocalRandom.current().nextInt(0, allParcs.size()));
        offre.setParc(parcDto);
        return Optional.of(offre);
    }

    public List<OffreDto> findAll() {
        return offreRepository.findAll();
    }


    public List<OffreDto> findAllByMarket(TypeMarche typeMarche) {
        return offreRepository.findAll()
                .stream()
                .filter(offreDto -> offreDto.getTypeMarche().equals(typeMarche))
                .toList();
    }

    public List<ParcDto> findAllParcsByMarket(TypeMarche typeMarche) {
        return findAllByMarket(typeMarche)
                .stream()
                .map(OffreDto::getParc)
                .toList();
    }
}
