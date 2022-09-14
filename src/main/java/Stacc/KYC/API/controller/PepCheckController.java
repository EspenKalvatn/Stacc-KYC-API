package Stacc.KYC.API.controller;

import Stacc.KYC.API.service.PepCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/check")
public class PepCheckController {

    @Autowired
    PepCheckService pepCheckService;


    @RequestMapping("person/{name}")
    public ResponseEntity checkPerson(@PathVariable String name) {
        return pepCheckService.checkPerson(name);
    }

    @RequestMapping("organization/{orgNumber}")
    public ResponseEntity checkOrganizationNumber(@PathVariable String orgNumber) {
        return pepCheckService.checkOrganizationNumber(orgNumber);
    }


}
