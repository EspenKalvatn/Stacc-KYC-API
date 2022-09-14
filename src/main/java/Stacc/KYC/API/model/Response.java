package Stacc.KYC.API.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Response {
    private int hits;
    private List<Person> results;

    public Response(List<Person> results) {
        this.results = results;
        this.hits = results.size();
    }
}
