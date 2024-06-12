package ticketapp.webtech.ticketapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import ticketapp.webtech.ticketapp.entity.MitarbeiterEntity;
import ticketapp.webtech.ticketapp.service.MitarbeiterService;
import ticketapp.webtech.ticketapp.web.MitarbeiterRestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MitarbeiterRestControllerTest {

    @Mock
    private MitarbeiterService service;

    @InjectMocks
    private MitarbeiterRestController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginSuccess() {
        MitarbeiterEntity mitarbeiter = new MitarbeiterEntity("Hakan", "Sükür", 123456, "hakan.sükür@ticketapp.com", "hasükür", "password", MitarbeiterEntity.MitarbeiterBereich.IT_MITARBEITER);
        when(service.getByBenutzername("hasükür")).thenReturn(mitarbeiter);

        ResponseEntity<String> response = controller.login(mitarbeiter);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Login successful", response.getBody());
    }

    @Test
    public void testLoginFailure() {
        MitarbeiterEntity mitarbeiter = new MitarbeiterEntity("Hakan", "Sükür", 123456, "hakan.sükür@ticketapp.com", "hasükür", "password", MitarbeiterEntity.MitarbeiterBereich.IT_MITARBEITER);
        when(service.getByBenutzername("hasükür")).thenReturn(null);

        ResponseEntity<String> response = controller.login(mitarbeiter);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid username or password", response.getBody());
    }

    @Test
    public void testCreateMitarbeiter() {
        MitarbeiterEntity mitarbeiter = new MitarbeiterEntity("Hakan", "Sükür", 123456, "hakan.sükür@ticketapp.com", "hasükür", "password", MitarbeiterEntity.MitarbeiterBereich.IT_MITARBEITER);
        when(service.save(mitarbeiter)).thenReturn(mitarbeiter);

        MitarbeiterEntity result = controller.createMitarbeiter(mitarbeiter);

        assertEquals(mitarbeiter, result);
        verify(service, times(1)).save(mitarbeiter);
    }

    @Test
    public void testGetMitarbeiter() {
        MitarbeiterEntity mitarbeiter = new MitarbeiterEntity("Thomas", "Müller", 123456, "thomas.müller@ticketapp.com", "tomüller", "password", MitarbeiterEntity.MitarbeiterBereich.IT_MITARBEITER);
        when(service.get(1L)).thenReturn(mitarbeiter);

        MitarbeiterEntity result = controller.getMitarbeiter("1");

        assertEquals(mitarbeiter.getVorname(), result.getVorname());
        assertEquals(mitarbeiter.getNachname(), result.getNachname());
        assertEquals(mitarbeiter.getBenutzername(), result.getBenutzername());
        verify(service, times(1)).get(1L);
    }

    @Test
    public void testDeleteMitarbeiter() {
        Long id = 1L;
        doNothing().when(service).delete(id);

        ResponseEntity<String> response = controller.deleteMitarbeiter("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Mitarbeiter mit der ID 1 wurde erfolgreich gelöscht.", response.getBody());
        verify(service, times(1)).delete(id);
    }

    @Test
    public void testCommunication() {
        ResponseEntity<String> response = controller.testCommunication();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Die Kommunikation zwischen Frontend und Backend funktioniert!", response.getBody());
    }
}

