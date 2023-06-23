package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.service.NaturalPersonService;

@RequiredArgsConstructor
@RestController
@RequestMapping(name = "/api/v1/natural-person")
public class NaturalPersonController {
    private final NaturalPersonService service;
}
