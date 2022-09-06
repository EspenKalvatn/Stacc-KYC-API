package Stacc.KYC.API.service;

import Stacc.KYC.API.model.PepReader;
import Stacc.KYC.API.model.Person;
import Stacc.KYC.API.model.organization.Name;
import Stacc.KYC.API.model.organization.RoleGroup;
import Stacc.KYC.API.model.organization.Roles;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PepCheckService {

    @Autowired
    private ObjectMapper mapper;
//    private List<String> nameList;
    private List<Person> personList;
    public PepCheckService() {
//        nameList = PepReader.CSVToNameList("src//main//resources//pep.csv");
        personList = PepReader.CSVToPOJOList("src//main//resources//pep.csv");
    }

    public String checkPerson(String name) {
        if (isPoliticallyExposed(name)) {
            return name + " is a politically exposed person.";
        }
        return name + " is not a politically exposed person.";
    }

    public List<String> checkOrganizationNumber(String orgNumber) throws IOException {
        List<String> pepList = new ArrayList<>();

        String url = "https://code-challenge.stacc.dev/api/roller?orgNr=" + orgNumber;
        RestTemplate restTemplate = new RestTemplate();
        Object[] jsonObjects = restTemplate.getForObject(url, Object[].class);

        List<RoleGroup> roleGroupTypes = mapper.readValue(mapper.writeValueAsString(jsonObjects), new TypeReference<>() {});

        for (RoleGroup group : roleGroupTypes) {

            for (Roles roles : group.getRoller()) {
                if (roles.getPerson() == null) {
                    continue;
                }
                String fullName = roles.getPerson().getFullName();

                if (isPoliticallyExposed(fullName) && !pepList.contains(fullName)) { // The same person can have several roles within an organization.
                    pepList.add(fullName);
                }
            }
        }

        return pepList;
    }



    private boolean isPoliticallyExposed(String name) {

        for (Person person : personList) {
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

//        for (String n : nameList) {
//            if (n.equals(name)) {
//                return true;
//            }
//        }
//        return false;
    }


}
