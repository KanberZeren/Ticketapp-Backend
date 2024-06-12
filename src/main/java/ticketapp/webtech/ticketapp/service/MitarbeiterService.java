package ticketapp.webtech.ticketapp.service;

import ticketapp.webtech.ticketapp.repository.MitarbeiterRepository;
import ticketapp.webtech.ticketapp.entity.MitarbeiterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MitarbeiterService {

    @Autowired
    MitarbeiterRepository repo;

    public MitarbeiterEntity save(MitarbeiterEntity mitarbeiter){
        return repo.save(mitarbeiter);
    }

    public MitarbeiterEntity get(Long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public MitarbeiterEntity getByBenutzername(String benutzername) {
        return repo.findByBenutzername(benutzername);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
