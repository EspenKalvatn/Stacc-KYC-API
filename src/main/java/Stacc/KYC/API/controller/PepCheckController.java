package Stacc.KYC.API.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/check")
public class PepCheckController {


    @RequestMapping("person/{name}")
    @ResponseBody
    public String checkPerson(@PathVariable String name) throws IOException {
        return name;
    }

    @RequestMapping("organization/{orgNumber}")
    @ResponseBody
    public String checkOrganizationNumber(@PathVariable String orgNumber) {
        return orgNumber;
    }


}
