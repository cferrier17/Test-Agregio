package corentinf.testagregio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import corentinf.testagregio.model.TypeParc;
import corentinf.testagregio.model.domain.BlocHoraireDomain;
import corentinf.testagregio.model.domain.ParcDomain;
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
class ParcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void saveParc() throws Exception {
        var parcDomain = ParcDomain.builder()
                .typeParc(TypeParc.EOLIEN)
                .blocHoraires(Collections.singletonList(BlocHoraireDomain.builder()
                        .quantiteEnergie(100)
                        .debut(LocalDateTime.of(2023, 8, 20, 12, 0))
                        .build()))
                .build();

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        var objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        var parcDomainAsJson = objectWriter.writeValueAsString(parcDomain);

        this.mockMvc.perform(post("/parcs").contentType(MediaType.APPLICATION_JSON)
                .content(parcDomainAsJson))
                .andExpect(status().isOk());
    }
}