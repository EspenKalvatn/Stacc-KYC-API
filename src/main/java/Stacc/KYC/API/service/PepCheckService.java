package Stacc.KYC.API.service;

import Stacc.KYC.API.model.Person;
import Stacc.KYC.API.model.organization.RoleGroup;
import Stacc.KYC.API.model.organization.Roles;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class PepCheckService {

    @Autowired
    private ObjectMapper mapper;


    public String checkPerson(String name) throws IOException {
        if (isPoliticallyExposed(name)) {
            return name + " is a politically exposed person.";
        }
        return name + " is not a politically exposed person";
    }

    public List<String> checkOrganizationNumber(String orgNumber) throws IOException {
        List<String> pepList = new ArrayList<>();

        String url = "https://code-challenge.stacc.dev/api/roller?orgNr=" + orgNumber;
        RestTemplate restTemplate = new RestTemplate();
        Object[] jsonObjects = restTemplate.getForObject(url, Object[].class);
        List<RoleGroup> roleGroupTypes = mapper.readValue(mapper.writeValueAsString(jsonObjects), new TypeReference<>() {});

        for (RoleGroup group : roleGroupTypes) {
            for (Roles roles : group.getRoller()) {
                String name = roles.getPerson().getNavn().getFornavn() + " " + roles.getPerson().getNavn().getEtternavn();
                if (isPoliticallyExposed(name) && !pepList.contains(name)) { // The same person can have several roles within an organization.
                    pepList.add(name);
                }
            }
        }

        return pepList;
    }

    private boolean isPoliticallyExposed(String name) throws IOException {
        String jsonString = Files.readString(Path.of("src//main//resources//pep_samll.json"));
        List<Person> pepList = mapper.readValue(jsonString, new TypeReference<>() {});

        for (Person person : pepList) {
            if (person.getName().equals(name)) {
                return true;
            }
            for (String alias : person.getAliases()) {
                if (alias.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }


}
