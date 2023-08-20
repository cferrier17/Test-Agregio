package corentinf.testagregio.adapter;

import corentinf.testagregio.model.domain.BlocHoraireDomain;
import corentinf.testagregio.model.dto.BlocHoraireDto;
import org.springframework.stereotype.Service;

@Service
public class BlocHoraireDtoDomainAdapter implements AbstractDtoDomainAdapter<BlocHoraireDto, BlocHoraireDomain> {
    @Override
    public BlocHoraireDto fromDomToDto(BlocHoraireDomain blocHoraireDomain) {
        return BlocHoraireDto.builder()
                .debut(blocHoraireDomain.getDebut())
                .quantiteEnergie(blocHoraireDomain.getQuantiteEnergie())
                .build();
    }

    @Override
    public BlocHoraireDomain fromDtoToDom(BlocHoraireDto blocHoraireDto) {
        return BlocHoraireDomain.builder()
                .debut(blocHoraireDto.getDebut())
                .quantiteEnergie(blocHoraireDto.getQuantiteEnergie())
                .build();
    }
}
