package ticketapp.webtech.ticketapp.web;

import ticketapp.webtech.ticketapp.service.TicketService;
import ticketapp.webtech.ticketapp.entity.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class TicketRestController {

    @Autowired
    TicketService service;

    @PostMapping("/ticket")
    public TicketEntity createTicket(@RequestBody TicketEntity ticket){
        return service.save(ticket);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/ticket/{id}")
    public TicketEntity getTicket(@PathVariable String id){
        Long  ticketId = Long.parseLong(id);
        return service.get(ticketId);
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable String id) {
        Long ticketId = Long.parseLong(id);
        service.delete(ticketId);
        return ResponseEntity.ok("Ticket mit der ID " + ticketId + " wurde erfolgreich gelöscht.");
    }

    @GetMapping("/tickets")
    public List<TicketEntity> getAllTickets() {
        return service.getAllTickets();
    }

    @PutMapping("/ticket/{id}")
    public ResponseEntity<TicketEntity> updateTicket(@PathVariable String id, @RequestBody TicketEntity updatedTicket) {
        try {
            Long ticketId = Long.parseLong(id);

            TicketEntity existingTicket = service.get(ticketId);

            existingTicket.setBetreff(updatedTicket.getBetreff());
            existingTicket.setNachricht(updatedTicket.getNachricht());
            existingTicket.setStatus(updatedTicket.getStatus());
            existingTicket.setKommentar(updatedTicket.getKommentar());

            TicketEntity savedTicket = service.save(existingTicket);

            return ResponseEntity.ok(savedTicket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ticketByTicketnummer/{ticketid}") // Änderung hier
    public ResponseEntity<?> getTicketByTicketnummer(@PathVariable String ticketid) {
        TicketEntity ticket = service.getTicketByTicketnummer(ticketid);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
        }
    }

}
