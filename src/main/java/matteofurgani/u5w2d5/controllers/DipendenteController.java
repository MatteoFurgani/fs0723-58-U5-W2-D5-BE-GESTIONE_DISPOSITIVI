package matteofurgani.u5w2d5.controllers;

import matteofurgani.u5w2d5.entities.Dipendente;
import matteofurgani.u5w2d5.exceptions.BadRequestException;
import matteofurgani.u5w2d5.payloads.NewDipendenteDTO;
import matteofurgani.u5w2d5.payloads.NewDipendenteRespDTO;
import matteofurgani.u5w2d5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @GetMapping
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sort) {
        return dipendenteService.getDipendente(page, size, sort);
    }

    // 3. - GET http://localhost:3001/dipendenti/{id}

    @GetMapping("/{dipendentiId}")
    public Dipendente findById(@PathVariable int dipendentiId) {
        return dipendenteService.findById(dipendentiId);
    }

    // 4. - PUT http://localhost:3001/dipendenti/{id} (+ req.body)

    @PutMapping("/{dipendentiId}")
    public Dipendente findAndUpdate(@PathVariable int dipendentiId, @RequestBody Dipendente body){
        return dipendenteService.findByIdAndUpdate(dipendentiId, body);
    }

    // 5. - DELETE http://localhost:3001/dipendenti/{id}

    @DeleteMapping("/{dipendentiId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int dipendentiId){
        dipendenteService.findByIDAndDelete(dipendentiId);
    }
}
