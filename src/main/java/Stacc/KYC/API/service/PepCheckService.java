package Stacc.KYC.API.service;

import Stacc.KYC.API.model.PepReader;
import Stacc.KYC.API.model.Person;
import Stacc.KYC.API.model.Response;
import Stacc.KYC.API.model.organization.RoleGroup;
import Stacc.KYC.API.model.organization.Roles;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PepCheckService {

    @Autowired
    private ObjectMapper mapper;
    private HashMap<String, Person> personMap;

    public PepCheckService() {
        personMap = PepReader.readCSV("src//main//resources//pep.csv");
    }


    public ResponseEntity checkPerson(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Person> results = new ArrayList<>();
        Person person = personMap.get(name);
        if (person != null) {
            results.add(person);
        }

        Response response = new Response(results);
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok().headers(headers);

        return responseBuilder.body(response);
    }

    public ResponseEntity checkOrganizationNumber(String orgNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Response response = new Response(getAllPubliclyExposed(orgNumber));

        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok().headers(headers);
        return responseBuilder.body(response);
    }

    private List<Person> getAllPubliclyExposed(String orgNumber) {
        List<Person> pepList = new ArrayList<>();

        Object[] jsonObjects = getOrganization(orgNumber);

        List<RoleGroup> roleGroupTypes;
        try {
            roleGroupTypes = mapper.readValue(mapper.writeValueAsString(jsonObjects), new TypeReference<>() {});
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

        for (RoleGroup group : roleGroupTypes) {

            for (Roles roles : group.getRoller()) {
                if (roles.getPerson() == null) {
                    continue;
                }
                String fullName = roles.getPerson().getFullName();

                if (personMap.containsKey(fullName) && !pepList.contains(fullName)) { // The same person can have several roles within an organization.
                    pepList.add(personMap.get(fullName));
                }
            }
        }

        return pepList;
    }

    private Object[] getOrganization(String orgNumber) {
        String url = "https://code-challenge.stacc.dev/api/roller?orgNr=" + orgNumber;
        RestTemplate restTemplate = new RestTemplate();
        Object[] jsonObjects = restTemplate.getForObject(url, Object[].class);
        return jsonObjects;
    }


}
