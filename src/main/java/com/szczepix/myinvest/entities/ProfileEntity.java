package com.szczepix.myinvest.entities;

import com.szczepix.myinvest.services.entityService.BaseEntity;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "profiles")
@Data
public class ProfileEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String mobile;

    @Override
    public boolean equals(Object obj) {
        ProfileEntity person = (ProfileEntity) obj;
        return super.equals(obj) || (getId() == person.getId() && firstName.equals(person.firstName) && password.equals(person.password));
    }

    @Override
    public String toString() {
        return print(id, firstName, lastName);
    }
}