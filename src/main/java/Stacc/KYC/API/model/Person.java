package Stacc.KYC.API.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Person {

    private float score;
    private String id;
    private String schema;
    private String name;
    private String aliases;
    private String birth_date;
    private String countries;
    private String identifiers;
    private String sanctions;
    private String phones;
    private String emails;
    private String dataset;
    private String last_seen;
    private String first_seen;
}
