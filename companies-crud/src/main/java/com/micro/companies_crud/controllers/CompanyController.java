package com.micro.companies_crud.controllers;


import com.micro.companies_crud.entities.Company;
import com.micro.companies_crud.services.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping (path = "company")
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping(path = "{name}")
    public ResponseEntity<Company>get(@PathVariable String name){
        log.info("Get company by name: {}", name);
        return ResponseEntity.ok(this.companyService.readByName(name));
    }

    @PostMapping
    public ResponseEntity<Company>post(@RequestBody Company company){
        log.info("Post company: {}", company.getName());
        return ResponseEntity.created(URI.create(this.companyService.create(company).getName())).build();
    }

    @PutMapping(path = "{name}")
    public ResponseEntity<Company>put(@RequestBody Company company, @PathVariable String name){
        log.info("Put company: {}", company.getName());
        return ResponseEntity.ok(this.companyService.update(company, name));
    }

    @DeleteMapping(path = "{name}")
    public ResponseEntity<?> delete(@PathVariable String name){
        log.info("Delete company: {}", name);
        this.companyService.delete(name);
        return ResponseEntity.noContent().build();
    }
}
