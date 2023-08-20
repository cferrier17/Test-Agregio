package corentinf.testagregio.model.dto;

import corentinf.testagregio.model.TypeParc;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParcDto {
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<BlocHoraireDto> blocHoraires = new ArrayList<>();
    private TypeParc typeParc;
}
