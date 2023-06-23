package rs.ac.bg.fon.silab.diplomskirad.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.silab.diplomskirad.service.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    private final ProductService service;
}
