package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.service.FirmService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/firm")
public class FirmController {
    private final FirmService service;
}
