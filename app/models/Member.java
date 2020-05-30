package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

/**
 * Represents a member
 */

@Entity
public class Member extends Model {
    public String name;
    public String email;
    public String password;
    public String address;
    public String gender;
    public double height;
    public double startWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    /**
     * Constructor based on user input
     *
     * @param name Member name
     * @param email Member email address
     * @param password Member password
     * @param address Member address
     * @param gender Member gender
     * @param height Member height
     * @param startWeight Member weight at registration
     */
    public Member(String name, String email, String password, String address, String gender, double height, double startWeight) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.startWeight = startWeight;
    }

    /**
     * Returns the member object matched by email entry
     * @param email Member email address
     * @return Member object
     */
    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    /**
     * Returns whether the entered password matches for this member
     *
     * @param password entered password
     * @return whether password was a match
     */
    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Gets start weight.
     *
     * @return the start weight
     */
    public double getStartWeight() {
        return startWeight;
    }

    /**
     * Sets start weight.
     *
     * @param startWeight the start weight
     */
    public void setStartWeight(double startWeight) {
        this.startWeight = startWeight;
    }

}
