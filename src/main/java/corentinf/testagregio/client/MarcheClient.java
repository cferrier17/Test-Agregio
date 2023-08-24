package corentinf.testagregio.client;

import corentinf.testagregio.model.dto.OffreDto;
import org.springframework.stereotype.Service;

@Service
public class MarcheClient {

    /**
     * @param offre
     * @return boolean, true if ok, false otherwise
     */
    public boolean sendOffreToMarche(OffreDto offre) {
        //send via http/kafka/..... to market
        return true;
    }
}
