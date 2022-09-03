package Stacc.KYC.API.model.organization;

import lombok.Getter;

@Getter
public class Person {
    private String fodselsdato;
    private Name navn;
    private boolean erDoed;
}
