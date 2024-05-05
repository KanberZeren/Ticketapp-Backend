package ticketapp.webtech.ticketapp.repository;

import ticketapp.webtech.ticketapp.entity.MitarbeiterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MitarbeiterRepository extends CrudRepository<MitarbeiterEntity, Long> {
    MitarbeiterEntity findByBenutzername(String benutzername);
}
