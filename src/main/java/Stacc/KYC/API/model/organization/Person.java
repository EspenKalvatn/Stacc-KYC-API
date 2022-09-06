package Stacc.KYC.API.model.organization;

import lombok.Getter;

@Getter
public class Person {
    private String fodselsdato;
    private Name navn;
    private boolean erDoed;

    public String getFullName() {
        String firstName = navn.getFornavn();
        String middleName = navn.getMellomnavn();
        String lastName = navn.getEtternavn();

        String fullName;

        if (middleName == null) {
            fullName = firstName + " " + lastName;
        } else {
            fullName = firstName + " " + middleName + " " + lastName;
        }
        return fullName;
    }
}
