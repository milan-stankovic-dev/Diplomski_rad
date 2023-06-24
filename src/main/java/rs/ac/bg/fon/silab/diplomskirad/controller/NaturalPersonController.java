package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.dto.NaturalPersonDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.NaturalPersonService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/natural-person")
public class NaturalPersonController {
    private final NaturalPersonService service;

    @GetMapping("/all")
    public List<NaturalPersonDTO> getAllNaturalPersons(){
        return service.getAllNaturalPersons();
    }
}
