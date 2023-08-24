package corentinf.testagregio.model.dto;

import corentinf.testagregio.model.TypeMarche;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OffreDto {
    @Id
    @GeneratedValue
    private long Id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<BlocHoraireDto> blocsHoraire;
    private int prixPlancher;
    private TypeMarche typeMarche;

    @OneToOne
    private ParcDto parc;
}
