package br.bom.techmeal.academic.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflitoDeComandaAtivaException extends RuntimeException {
    public ConflitoDeComandaAtivaException(String message) {
        super(message);
    }
}
