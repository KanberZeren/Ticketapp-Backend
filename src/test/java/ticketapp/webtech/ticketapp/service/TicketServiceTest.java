package ticketapp.webtech.ticketapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ticketapp.webtech.ticketapp.entity.TicketEntity;
import ticketapp.webtech.ticketapp.entity.TicketEntity.TicketStatus;
import ticketapp.webtech.ticketapp.repository.TicketRepository;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private TicketEntity ticket;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        ticket = new TicketEntity("Test Betreff", "Test Nachricht", TicketStatus.OFFEN);
        setId(ticket, 1L);
    }

    private void setId(TicketEntity ticket, Long id) throws Exception {
        Field field = TicketEntity.class.getDeclaredField("id");
        field.setAccessible(true);
        field.set(ticket, id);
    }

    @Test
    public void testCreateTicket() {
        when(ticketRepository.save(any(TicketEntity.class))).thenReturn(ticket);

        TicketEntity createdTicket = ticketService.save(ticket);

        assertNotNull(createdTicket);
        assertEquals("Test Betreff", createdTicket.getBetreff());
        assertEquals("Test Nachricht", createdTicket.getNachricht());
        assertEquals(TicketStatus.OFFEN, createdTicket.getStatus());
    }

    @Test
    public void testGetTicket() {
        when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket));

        TicketEntity foundTicket = ticketService.get(1L);

        assertNotNull(foundTicket);
        assertEquals("Test Betreff", foundTicket.getBetreff());
        assertEquals("Test Nachricht", foundTicket.getNachricht());
        assertEquals(TicketStatus.OFFEN, foundTicket.getStatus());
    }

    @Test
    public void testDeleteTicket() {
        doNothing().when(ticketRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> ticketService.delete(1L));

        verify(ticketRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllTickets() {
        when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket));

        List<TicketEntity> tickets = ticketService.getAllTickets();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
        assertEquals(1, tickets.size());
        assertEquals("Test Betreff", tickets.get(0).getBetreff());
    }

    @Test
    public void testUpdateTicket() throws Exception {
        TicketEntity updatedTicket = new TicketEntity("Updated Betreff", "Updated Nachricht", TicketStatus.OFFEN);
        setId(updatedTicket, 1L);

        when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(TicketEntity.class))).thenReturn(updatedTicket);

        ticket.setBetreff("Updated Betreff");
        ticket.setNachricht("Updated Nachricht");
        TicketEntity result = ticketService.save(ticket);

        assertNotNull(result);
        assertEquals("Updated Betreff", result.getBetreff());
        assertEquals("Updated Nachricht", result.getNachricht());
    }
}
