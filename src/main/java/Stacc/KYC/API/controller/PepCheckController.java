package Stacc.KYC.API.controller;

import Stacc.KYC.API.service.PepCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/check")
public class PepCheckController {

    @Autowired
    PepCheckService pepCheckService;

    @RequestMapping("person/{name}")
    @ResponseBody
    public String checkPerson(@PathVariable String name) throws IOException {
        return pepCheckService.checkPerson(name);
    }

    @RequestMapping("organization/{orgNumber}")
    @ResponseBody
    public String checkOrganizationNumber(@PathVariable String orgNumber) {
        return orgNumber;
    }


}
