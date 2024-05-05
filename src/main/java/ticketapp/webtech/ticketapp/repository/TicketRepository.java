package ticketapp.webtech.ticketapp.repository;

import ticketapp.webtech.ticketapp.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {
    Optional<TicketEntity> findByTicketnummer(String ticketnummer);
}
