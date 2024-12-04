package com.microservices.company.companies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class Companycontroller {
    private CompanyService companyService;

    public Companycontroller(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity <List<Company>> getallCompanies(){
        return new ResponseEntity<>(companyService.getallcompanies(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Company company){
         companyService.create(company);
        return new ResponseEntity<>("Created succesfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletecompany(@PathVariable Long id){
        boolean deleted = companyService.delete(id);
         if(deleted){
             return new ResponseEntity<>("deeleted suceesfully",HttpStatus.OK);
         }
         return new ResponseEntity<>("id not found",HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Company>> getjobyid(@PathVariable Long id){
       Optional<Company> company = companyService.getCompanybyid(id);
        if(company.isPresent()) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
         boolean updated = companyService.updateCompany(id,company);
         if(updated) {
             return new ResponseEntity<>("updatedSuccesfull", HttpStatus.OK);
         }
         return  new ResponseEntity<>("id not found",HttpStatus.NOT_FOUND);

    }


}
