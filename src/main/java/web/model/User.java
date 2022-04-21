package web.model;

import web.util.Gender;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String name;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column
    private String phone;

    public User() {}

    public User(String name, Date birthdate, Gender gender, String phone) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phone = phone;
    }

    public User(long id, String name, Date birthdate, Gender gender, String phone) {
        this.Id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phone = phone;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
