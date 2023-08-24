package corentinf.testagregio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import corentinf.testagregio.model.TypeMarche;
import corentinf.testagregio.model.TypeParc;
import corentinf.testagregio.model.domain.BlocHoraireDomain;
import corentinf.testagregio.model.domain.OffreDomain;
import corentinf.testagregio.model.dto.BlocHoraireDto;
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
}
