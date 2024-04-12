package matteofurgani.u5w2d5.controllers;

import matteofurgani.u5w2d5.entities.Dipendente;
import matteofurgani.u5w2d5.exceptions.BadRequestException;
import matteofurgani.u5w2d5.payloads.NewDipendenteDTO;
import matteofurgani.u5w2d5.payloads.NewDipendenteRespDTO;
import matteofurgani.u5w2d5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    // 1. - POST http://localhost:3001/dipendenti (+ req.body)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendenteRespDTO saveDipendente(@RequestBody @Validated NewDipendenteDTO body, BindingResult validation) throws Exception{
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }

        Dipendente dipendente = dipendenteService.save(body);
        return new NewDipendenteRespDTO(dipendente.getId());
    }

    // 2. - GET http://localhost:3001/dipendenti

    // 3. - GET http://localhost:3001/dipendenti/{id}

    // 4. - PUT http://localhost:3001/dipendenti/{id} (+ req.body)

    // 5. - DELETE http://localhost:3001/dipendenti/{id}
}
