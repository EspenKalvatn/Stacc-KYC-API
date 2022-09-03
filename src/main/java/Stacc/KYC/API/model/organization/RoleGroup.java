package Stacc.KYC.API.model.organization;

import lombok.Getter;


import java.util.List;

@Getter
public class RoleGroup {
    private RoleGroupType type;
    private String sistEndret;
    private List<Roles> roller;
}
