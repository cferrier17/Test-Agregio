package corentinf.testagregio.controller;

import corentinf.testagregio.adapter.ParcAdapter;
import corentinf.testagregio.model.TypeMarche;
import corentinf.testagregio.model.domain.ParcDomain;
import corentinf.testagregio.service.OffreService;
import corentinf.testagregio.service.ParcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcs")
@RequiredArgsConstructor
public class ParcController {
    private final ParcService parcService;
    private final ParcAdapter parcAdapter;
    private final OffreService offreService;

    @PostMapping
    public ResponseEntity<ParcDomain> saveParc(@RequestBody ParcDomain parcDomain) {
        var parcDto = parcAdapter.fromDomToDto(parcDomain);
        try {
            parcService.saveParc(parcDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(parcDomain);
    }

    @GetMapping("/by-market")
    public ResponseEntity<List<ParcDomain>> getAllParcsByMarket(@RequestAttribute TypeMarche typeMarche) {
        var allParcsDto = offreService.findAllParcsByMarket(typeMarche);
        var allParcsDomain = allParcsDto.stream()
                .map(parcAdapter::fromDtoToDom)
                .toList();

        return ResponseEntity.ok(allParcsDomain);
    }
}
