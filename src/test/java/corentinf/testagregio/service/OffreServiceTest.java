package corentinf.testagregio.service;

import corentinf.testagregio.model.TypeMarche;
import corentinf.testagregio.model.TypeParc;
import corentinf.testagregio.model.dto.BlocHoraireDto;
import corentinf.testagregio.model.dto.OffreDto;
import corentinf.testagregio.model.dto.ParcDto;
import corentinf.testagregio.repository.OffreRepository;
import corentinf.testagregio.repository.ParcRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OffreServiceTest {
    @Autowired
    private ParcService parcService;

    @Autowired
    private OffreService offreService;

    @Autowired
    private ParcRepository parcRepository;

    @Autowired
    private OffreRepository offreRepository;

    @BeforeEach
    public void before() {
        offreRepository.deleteAll();
        parcRepository.deleteAll();
    }

    @Test
    void saveOffre() {
        var parcDto = ParcDto.builder()
                .typeParc(TypeParc.EOLIEN)
                .blocsHoraire(Collections.singletonList(BlocHoraireDto.builder()
                        .quantiteEnergieTotale(100)
                        .debut(LocalDateTime.of(2023, 8, 20, 12, 0))
                        .build()))
                .build();

        parcService.saveParc(parcDto);

        var offreDto = OffreDto.builder()
                .prixPlancher(10000)
                .typeMarche(TypeMarche.RESERVE_PRIMAIRE)
                .blocsHoraire(Collections.singletonList(BlocHoraireDto.builder()
                        .quantiteEnergieTotale(100)
                        .debut(LocalDateTime.of(2023, 8, 20, 12, 0))
                        .build()))
                .build();

        offreService.saveOffre(offreDto);

        var savedOffreDto = offreService.findAll().get(0);

        assertEquals(savedOffreDto.getParc().getId(), parcDto.getId());
        assertEquals(savedOffreDto.getBlocsHoraire().get(0).getQuantiteEnergieAlouee(), 0);
    }

    @Test
    void findAllByMarket() {
        var parcDto = ParcDto.builder()
                .typeParc(TypeParc.EOLIEN)
                .blocsHoraire(Collections.singletonList(BlocHoraireDto.builder()
                        .quantiteEnergieTotale(100)
                        .debut(LocalDateTime.of(2023, 8, 20, 12, 0))
                        .build()))
                .build();

        parcService.saveParc(parcDto);

        var offreDto1 = OffreDto.builder()
                .typeMarche(TypeMarche.RESERVE_PRIMAIRE)
                .prixPlancher(5000)
                .build();

        var offreDto2 = OffreDto.builder()
                .typeMarche(TypeMarche.RESERVE_RAPIDE)
                .prixPlancher(6000)
                .build();

        var offreDto3 = OffreDto.builder()
                .typeMarche(TypeMarche.RESERVE_SECONDAIRE)
                .prixPlancher(7000)
                .build();

        offreService.saveOffre(offreDto1);
        offreService.saveOffre(offreDto2);
        offreService.saveOffre(offreDto3);

        var offreByMarket = offreService.findAllByMarket(TypeMarche.RESERVE_PRIMAIRE).get(0);
        assertEquals(offreByMarket.getTypeMarche(), TypeMarche.RESERVE_PRIMAIRE);
        assertEquals(offreByMarket.getPrixPlancher(), 5000);
    }
}