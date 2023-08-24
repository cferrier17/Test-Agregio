package corentinf.testagregio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import corentinf.testagregio.model.TypeMarche;
import corentinf.testagregio.model.TypeParc;
import corentinf.testagregio.model.domain.BlocHoraireDomain;
import corentinf.testagregio.model.domain.OffreDomain;
import corentinf.testagregio.model.dto.BlocHoraireDto;
import corentinf.testagregio.model.dto.OffreDto;
import corentinf.testagregio.model.dto.ParcDto;
import corentinf.testagregio.service.OffreService;
import corentinf.testagregio.service.ParcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OffreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParcService parcService;

    @Autowired
    private OffreService offreService;


    @Test
    void saveOffre() throws Exception {
        var parcDto = ParcDto.builder()
                .typeParc(TypeParc.EOLIEN)
                .blocsHoraire(Collections.singletonList(BlocHoraireDto.builder()
                        .quantiteEnergieTotale(100)
                        .debut(LocalDateTime.of(2023, 8, 20, 12, 0))
                        .build()))
                .build();

        parcService.saveParc(parcDto);


        var offreDomain = OffreDomain.builder()
                .prixPlancher(10000)
                .typeMarche(TypeMarche.RESERVE_PRIMAIRE)
                .blocsHoraire(Collections.singletonList(BlocHoraireDomain.builder()
                        .quantiteEnergie(100)
                        .debut(LocalDateTime.of(2023, 8, 20, 12, 0))
                        .build()))
                .build();

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        var objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        var offreDomainAsJson = objectWriter.writeValueAsString(offreDomain);

        this.mockMvc.perform(post("/offres").contentType(MediaType.APPLICATION_JSON)
                        .content(offreDomainAsJson))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAllOffreByMarket() throws Exception {
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

        offreService.saveOffre(offreDto1);

        this.mockMvc.perform(get("/offres/by-market").contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("typeMarche", TypeMarche.RESERVE_PRIMAIRE))
                .andExpect(status().isOk());
    }
}
