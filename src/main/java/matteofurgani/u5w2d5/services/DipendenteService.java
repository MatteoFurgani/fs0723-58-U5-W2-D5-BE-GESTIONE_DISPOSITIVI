package matteofurgani.u5w2d5.services;

import matteofurgani.u5w2d5.entities.Dipendente;
import matteofurgani.u5w2d5.exceptions.BadRequestException;
import matteofurgani.u5w2d5.payloads.NewDipendenteDTO;
import matteofurgani.u5w2d5.repositories.DipendenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteDAO dipendenteDAO;

    public Dipendente save(NewDipendenteDTO body) throws IOException{
        dipendenteDAO.findByEmail(body.email()).ifPresent(
                dipendente -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                });
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(body.nome());
        dipendente.setCognome(body.cognome());
        dipendente.setEmail(body.email());
        dipendente.setUsername(body.username());
        dipendente.setImmagineProfilo("https://ui-avatars.com/api/?name=" + body.nome().charAt(0) + "+" + body.cognome().charAt(0));
        return dipendenteDAO.save(dipendente);
    }


}
