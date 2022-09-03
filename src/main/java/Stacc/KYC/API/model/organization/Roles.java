package Stacc.KYC.API.model.organization;

import lombok.Getter;

@Getter
public class Roles {

    private RoleGroupType type;
    private Person person;
    private boolean fratraadt;
    private Integer rekkefolge;
}
