package com.szczepix.myinvest.entities;

import com.szczepix.myinvest.services.entityService.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Profiles")
@Data
public class ProfileEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String mobile;

    @Override
    public boolean equals(Object obj) {
        ProfileEntity person = (ProfileEntity) obj;
        return super.equals(obj) || (getId().equals(person.getId()) && firstName.equals(person.firstName) && password.equals(person.password));
    }

    @Override
    public String toString() {
        return print(id, firstName, lastName);
    }
}