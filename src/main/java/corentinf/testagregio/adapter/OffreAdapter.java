package corentinf.testagregio.adapter;

import corentinf.testagregio.model.domain.OffreDomain;
import corentinf.testagregio.model.dto.OffreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OffreAdapter implements AbstractDtoDomainAdapter<OffreDto, OffreDomain> {
    private final BlocHoraireAdapter blocHoraireAdapter;

    @Override
    public OffreDto fromDomToDto(OffreDomain offreDomain) {
        return OffreDto.builder()
                .prixPlancher(offreDomain.getPrixPlancher())
                .typeMarche(offreDomain.getTypeMarche())
                .blocsHoraire(
                        offreDomain.getBlocsHoraire()
                                .stream()
                                .map(blocHoraireAdapter::fromDomToDto)
                                .toList())
                .build();
    }

    @Override
    public OffreDomain fromDtoToDom(OffreDto offreDto) {
        return OffreDomain.builder()
                .prixPlancher(offreDto.getPrixPlancher())
                .typeMarche(offreDto.getTypeMarche())
                .blocsHoraire(
                        offreDto.getBlocsHoraire()
                                .stream()
                                .map(blocHoraireAdapter::fromDtoToDom)
                                .toList())
                .build();
    }
}
