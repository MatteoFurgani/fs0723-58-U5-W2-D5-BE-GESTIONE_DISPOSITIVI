package matteofurgani.u5w2d5.payloads.errors;

import java.util.Date;
import java.util.List;

public record ErrorsRespDTO(String message, Date timeStamp, List<String> errorsList) {
}
