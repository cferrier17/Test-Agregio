package corentinf.testagregio.model.domain;

import corentinf.testagregio.model.TypeParc;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ParcDomain {
    private List<BlocHoraireDomain> blocHoraires;
    private TypeParc typeParc;

}
