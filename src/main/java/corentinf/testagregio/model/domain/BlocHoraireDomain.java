package corentinf.testagregio.model.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BlocHoraireDomain {
    private LocalDateTime debut;
    private int quantiteEnergie;
    private int quantiteEnergieAlouee;
}
