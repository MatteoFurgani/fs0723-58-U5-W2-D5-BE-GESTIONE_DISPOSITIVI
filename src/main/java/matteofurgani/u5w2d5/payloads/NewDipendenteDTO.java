package matteofurgani.u5w2d5.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(
        @NotEmpty(message = "L'username non può essere vuoto")
        @Size(min = 3, max = 20, message = "L'username deve avere minimo 3 caratteri ed un massimo di 20")
        String username,
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 15, message = "L'username deve avere minimo 3 caratteri ed un massimo di 15")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 15, message = "L'username deve avere minimo 3 caratteri ed un massimo di 15")
        String cognome,
        @NotEmpty(message = "L'email è obbligatoria")
        String email) {
}
