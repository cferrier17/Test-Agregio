package corentinf.testagregio.model.domain;

import corentinf.testagregio.model.TypeMarche;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OffreDomain {
    private List<BlocHoraireDomain> blocsHoraire;
    private int prixPlancher;
    private TypeMarche typeMarche;
}

