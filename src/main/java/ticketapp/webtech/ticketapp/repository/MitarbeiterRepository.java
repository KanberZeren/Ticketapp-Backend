package ticketapp.webtech.ticketapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketapp.webtech.ticketapp.entity.MitarbeiterEntity;

@Repository
public interface MitarbeiterRepository extends JpaRepository<MitarbeiterEntity, Long> {
    MitarbeiterEntity findByBenutzername(String benutzername);
}
