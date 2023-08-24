package corentinf.testagregio.service;

import corentinf.testagregio.model.TypeMarche;
import corentinf.testagregio.model.TypeParc;
import corentinf.testagregio.model.dto.BlocHoraireDto;
import corentinf.testagregio.model.dto.OffreDto;
import corentinf.testagregio.model.dto.ParcDto;
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
}