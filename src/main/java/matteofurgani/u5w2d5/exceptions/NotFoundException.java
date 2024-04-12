package matteofurgani.u5w2d5.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(int id) {
        super("L'id " + id + " non è stato trovato!");
    }
}
