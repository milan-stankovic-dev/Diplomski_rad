package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.dto.PartnerDTO;
import rs.ac.bg.fon.silab.diplomskirad.service.PartnerService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/partner")
public class PartnerController {
    private final PartnerService service;

    @GetMapping("/all")
    public List<PartnerDTO> getAllPartners(){
        return service.getAllPartners();
    }
}
