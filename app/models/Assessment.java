package models;
import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class Assessment extends Model implements Comparable<Assessment>
{
    public double weight, chest, thigh, arm, waist, hips;
    public String addDate, comment;

    /**
     * Assessment constructor
     *
     * @param addDate the add date
     * @param weight  the weight
     * @param chest   the chest
     * @param thigh   the thigh
     * @param arm     the arm
     * @param waist   the waist
     * @param hips    the hips
     */
    public Assessment(String addDate, double weight, double chest, double thigh, double arm, double waist, double hips)
    {
        this.addDate = addDate;
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.arm = arm;
        this.waist = waist;
        this.hips = hips;
        this.comment = "";
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getThigh() { return thigh; }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getArm() {
        return arm;
    }

    public void setArm(double arm) {
        this.arm = arm;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int compareTo(Assessment assessment) {
        return addDate.compareTo(assessment.addDate);
    }
}