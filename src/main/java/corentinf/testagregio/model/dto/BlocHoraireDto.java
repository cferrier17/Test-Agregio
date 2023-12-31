package corentinf.testagregio.model.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BlocHoraireDto {
    private LocalDateTime debut;
    private int quantiteEnergieTotale;
    private int quantiteEnergieAlouee;
}
