package models;
import play.db.jpa.Model;
import javax.persistence.Entity;

/**
 * Represents an assessment
 */
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

    /**
     * Returns the weight
     *
     * @return weight weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getChest() {
        return chest;
    }

    /**
     * Sets chest.
     *
     * @param chest the chest
     */
    public void setChest(double chest) {
        this.chest = chest;
    }

    /**
     * Gets thigh.
     *
     * @return the thigh
     */
    public double getThigh() {
        return thigh;
    }

    /**
     * Sets thigh.
     *
     * @param thigh the thigh
     */
    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    /**
     * Gets arm.
     *
     * @return the arm
     */
    public double getArm() {
        return arm;
    }

    /**
     * Sets arm.
     *
     * @param arm the arm
     */
    public void setArm(double arm) {
        this.arm = arm;
    }

    /**
     * Gets waist.
     *
     * @return the waist
     */
    public double getWaist() {
        return waist;
    }

    /**
     * Sets waist.
     *
     * @param waist the waist
     */
    public void setWaist(double waist) {
        this.waist = waist;
    }

    /**
     * Gets hips.
     *
     * @return the hips
     */
    public double getHips() {
        return hips;
    }

    /**
     * Sets hips.
     *
     * @param hips the hips
     */
    public void setHips(double hips) {
        this.hips = hips;
    }

    /**
     * Gets add date.
     *
     * @return the add date
     */
    public String getAddDate() {
        return addDate;
    }

    /**
     * Sets add date.
     *
     * @param addDate the add date
     */
    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Compares the date values for sorting list entries
     * @param assessment
     * @return
     */
    public int compareTo(Assessment assessment) {
        return addDate.compareTo(assessment.addDate);
    }
}