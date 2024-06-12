package ticketapp.webtech.ticketapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ticketapp.webtech.ticketapp.entity.MitarbeiterEntity;
import ticketapp.webtech.ticketapp.repository.MitarbeiterRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class MitarbeiterServiceTest {

    @Mock
    private MitarbeiterRepository repository;

    @InjectMocks
    private MitarbeiterService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMitarbeiter() {
        MitarbeiterEntity mitarbeiter = new MitarbeiterEntity("Thomas", "Müller", 123456, "thomas.müller@ticketapp.com", "tomüller", "password", MitarbeiterEntity.MitarbeiterBereich.IT_MITARBEITER);
        when(repository.save(mitarbeiter)).thenReturn(mitarbeiter);

        MitarbeiterEntity result = service.save(mitarbeiter);

        assertEquals(mitarbeiter, result);
        verify(repository, times(1)).save(mitarbeiter);
    }

    @Test
    public void testGetMitarbeiterById() {
        MitarbeiterEntity mitarbeiter = new MitarbeiterEntity("Thomas", "Müller", 123456, "thomas.müller@ticketapp.com", "tomüller", "password", MitarbeiterEntity.MitarbeiterBereich.IT_MITARBEITER);
        when(repository.findById(1L)).thenReturn(Optional.of(mitarbeiter));

        MitarbeiterEntity result = service.get(1L);

        assertEquals(mitarbeiter, result);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteMitarbeiter() {
        Long id = 1L;
        doNothing().when(repository).deleteById(id);

        service.delete(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testGetMitarbeiterByBenutzername() {
        MitarbeiterEntity mitarbeiter = new MitarbeiterEntity("Thomas", "Müller", 123456, "thomas.müller@ticketapp.com", "tomüller", "password", MitarbeiterEntity.MitarbeiterBereich.IT_MITARBEITER);
        when(repository.findByBenutzername("tomüller")).thenReturn(mitarbeiter);

        MitarbeiterEntity result = service.getByBenutzername("tomüller");

        assertEquals(mitarbeiter, result);
        verify(repository, times(1)).findByBenutzername("tomüller");
    }

    @Test
    public void testGetMitarbeiterByBenutzernameNotExists() {
        when(repository.findByBenutzername("nicht Vorhanden")).thenReturn(null);

        MitarbeiterEntity result = service.getByBenutzername("nicht Vorhanden");

        assertNull(result);
        verify(repository, times(1)).findByBenutzername("nicht Vorhanden");
    }
}
