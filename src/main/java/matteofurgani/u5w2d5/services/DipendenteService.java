package matteofurgani.u5w2d5.services;

import matteofurgani.u5w2d5.entities.Dipendente;
import matteofurgani.u5w2d5.exceptions.BadRequestException;
import matteofurgani.u5w2d5.exceptions.NotFoundException;
import matteofurgani.u5w2d5.payloads.NewDipendenteDTO;
import matteofurgani.u5w2d5.repositories.DipendenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Dipendente> getDipendente(int page, int size, String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return dipendenteDAO.findAll(pageable);
    }

    public Dipendente findById(int id){
        return dipendenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public Dipendente findByIdAndUpdate(int id, Dipendente body){

        Dipendente found = this.findById(id);
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setUsername(body.getUsername());
        found.setImmagineProfilo(body.getImmagineProfilo());
        return dipendenteDAO.save(found);
    }

    public void findByIDAndDelete(int id) {
        Dipendente found = this.findById(id);
        dipendenteDAO.delete(found);
    }


}
