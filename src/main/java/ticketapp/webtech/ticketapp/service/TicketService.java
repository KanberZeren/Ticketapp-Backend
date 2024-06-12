package ticketapp.webtech.ticketapp.service;

import ticketapp.webtech.ticketapp.repository.TicketRepository;
import ticketapp.webtech.ticketapp.entity.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository repo;

    public TicketEntity save(TicketEntity ticket){
        return repo.save(ticket);
    }

    public TicketEntity get(Long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public TicketEntity getTicketByTicketnummer(String ticketnummer) {
        TicketEntity ticket = repo.findByTicketnummer(ticketnummer).orElse(null);
        return ticket;
    }

    public List<TicketEntity> getAllTickets() {
        return (List<TicketEntity>) repo.findAll();
    }
}
