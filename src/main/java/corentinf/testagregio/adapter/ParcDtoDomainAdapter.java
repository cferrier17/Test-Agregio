package corentinf.testagregio.adapter;

import corentinf.testagregio.model.domain.ParcDomain;
import corentinf.testagregio.model.dto.ParcDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParcDtoDomainAdapter implements AbstractDtoDomainAdapter<ParcDto, ParcDomain> {
    private final BlocHoraireDtoDomainAdapter blocHoraireAdapter;

    @Override
    public ParcDto fromDomToDto(ParcDomain domain) {
        return ParcDto.builder()
                .typeParc(domain.getTypeParc())
                .blocHoraires(
                        domain.getBlocHoraires()
                                .stream()
                                .map(blocHoraireAdapter::fromDomToDto)
                                .toList())
                .build();
    }

    @Override
    public ParcDomain fromDtoToDom(ParcDto dto) {
        return ParcDomain.builder()
                .typeParc(dto.getTypeParc())
                .blocHoraires(
                        dto.getBlocHoraires()
                                .stream()
                                .map(blocHoraireAdapter::fromDtoToDom)
                                .toList())
                .build();
    }
}
