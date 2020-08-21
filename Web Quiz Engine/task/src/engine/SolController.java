package engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.security.Principal;



@RestController
public class SolController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private SolService service;

    @Autowired
    public SolController(SolService service) {
        this.service = service;
    }



    /*@GetMapping(path = "api/completed", produces = "application/json")
    public ResponseEntity<Page<Solutions>> getCompletedQuizPage(Principal principal, Pageable pageable) {
        try {
            Page<Solutions> solList = service.findAllCompletedQuizzesAsPage(principal.getName(), pageable);
            return new ResponseEntity<>(solList, HttpStatus.OK);
        } catch (Exception exc) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Quiz Not Found", exc);
    }

    }*/
}