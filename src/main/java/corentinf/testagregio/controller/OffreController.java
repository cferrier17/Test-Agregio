package corentinf.testagregio.controller;

import corentinf.testagregio.adapter.OffreAdapter;
import corentinf.testagregio.model.TypeMarche;
import corentinf.testagregio.model.domain.OffreDomain;
import corentinf.testagregio.service.OffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/by-market")
    public ResponseEntity<List<OffreDomain>> getAllOffreByMarket(@RequestAttribute TypeMarche typeMarche) {
        var allByMarket = offreService.findAllByMarket(typeMarche);
        var list = allByMarket.stream()
                .map(offreDto -> offreAdapter.fromDtoToDom(offreDto))
                .toList();

        return ResponseEntity.ok(list);
    }
}
