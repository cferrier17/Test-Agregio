package corentinf.testagregio.controller;

import corentinf.testagregio.adapter.OffreAdapter;
import corentinf.testagregio.model.domain.OffreDomain;
import corentinf.testagregio.service.OffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offres")
@RequiredArgsConstructor
public class OffreController {

    private final OffreService offreService;
    private final OffreAdapter offreAdapter;

    @PostMapping
    public ResponseEntity<OffreDomain> save(@RequestBody OffreDomain offre) {
        var offreDto = offreAdapter.fromDomToDto(offre);
        offreService.saveOffre(offreDto);

        return ResponseEntity.ok(offre);
    }
}
