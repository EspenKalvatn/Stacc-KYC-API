package Stacc.KYC.API.service;

import Stacc.KYC.API.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class PepCheckService {

    @Autowired
    private ObjectMapper mapper;

    public String checkPerson(String name) throws IOException {
        String jsonString = Files.readString(Path.of("src//main//resources//pep_samll.json"));
        List<Person> personList = mapper.readValue(jsonString, new TypeReference<>() {
        });

        for (Person person : personList) {

            if (person.getName().equals(name)) {
                return name + " found in the list!";
            }
        }

        return "Did not find " + name + " in the list.";
    }


}
