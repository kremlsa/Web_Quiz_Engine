package engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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



    @GetMapping(path = "/api/quizzes/completed", produces = "application/json")
    public ResponseEntity<Page<SolDto>> getCompletedQuizPage(Principal principal, @PageableDefault(value = 10) Pageable pageable) {
    //public ResponseEntity<Page<String>> getCompletedQuizPage(Principal principal, Pageable pageable) {

            //Page<Solutions> solList = service.findAllCompletedQuizzesAsPage(principal.getName(), pageable);
        Page<SolDto> solList = service.findAllCompletedQuizzesAsPage(principal.getName(), pageable).map(Utils::convertCompletionEntityToDto);;
            return new ResponseEntity<>(solList, HttpStatus.OK);
    }
}