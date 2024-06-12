package ticketapp.webtech.ticketapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ticketapp.webtech.ticketapp.entity.TicketEntity;
import ticketapp.webtech.ticketapp.service.TicketService;
import ticketapp.webtech.ticketapp.web.TicketRestController;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketRestController.class)
public class TicketRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    private TicketEntity ticket;

    @BeforeEach
    public void setUp() {
        ticket = new TicketEntity("Test Betreff", "Test Nachricht", TicketEntity.TicketStatus.OFFEN);
    }

    @Test
    public void testCreateTicket() throws Exception {
        when(ticketService.save(any(TicketEntity.class))).thenReturn(ticket);

        mockMvc.perform(post("/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"betreff\":\"Test Betreff\",\"nachricht\":\"Test Nachricht\",\"status\":\"OFFEN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.betreff").value("Test Betreff"))
                .andExpect(jsonPath("$.nachricht").value("Test Nachricht"));
    }

    @Test
    public void testGetTicket() throws Exception {
        when(ticketService.get(anyLong())).thenReturn(ticket);

        mockMvc.perform(get("/ticket/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.betreff").value("Test Betreff"));
    }

    @Test
    public void testDeleteTicket() throws Exception {
        doNothing().when(ticketService).delete(anyLong());

        mockMvc.perform(delete("/ticket/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ticket mit der ID 1 wurde erfolgreich gelöscht."));
    }

    @Test
    public void testGetAllTickets() throws Exception {
        List<TicketEntity> tickets = Arrays.asList(ticket);
        when(ticketService.getAllTickets()).thenReturn(tickets);

        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].betreff").value("Test Betreff"));
    }

    @Test
    public void testUpdateTicket() throws Exception {
        TicketEntity updatedTicket = new TicketEntity("Updated Betreff", "Updated Nachricht", TicketEntity.TicketStatus.OFFEN);
        when(ticketService.get(anyLong())).thenReturn(ticket);
        when(ticketService.save(any(TicketEntity.class))).thenReturn(updatedTicket);

        mockMvc.perform(put("/ticket/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"betreff\":\"Updated Betreff\",\"nachricht\":\"Updated Nachricht\",\"status\":\"OFFEN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.betreff").value("Updated Betreff"))
                .andExpect(jsonPath("$.nachricht").value("Updated Nachricht"));
    }
}
