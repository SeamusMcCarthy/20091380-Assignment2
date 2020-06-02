package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class Trainer extends Model {
    public String name;
    public String email;
    public String password;
    public String gender;
    public String specialty;

    public Trainer(String name, String email, String password, String gender, String specialty) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public static Trainer findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }
}
