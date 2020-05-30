package controllers;
import models.Assessment;
import models.Member;
import play.Logger;
// Required for rounding method
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for Gym related calculations & determinations
 *
 * @version 1.0
 * @author Seamus McCarthy
 *
 */
public abstract class GymUtility {

    private static final double INCHES_IN_METER = 39.37;
    private static final double KILOS_PER_INCH = 2.3;
    private static final int HEIGHT_LIMIT = 60;
    private static final double TOLERANCE = 0.2;

    /**
     * Returns a String value containing the BMI Category
     *
     * @param bmiValue
     * @return String
     */
    public static String determineBMICategory(double bmiValue) {
        if (bmiValue < 16)
            return "SEVERELY UNDERWEIGHT";
        else if (bmiValue < 18.5)
            return "UNDERWEIGHT";
        else if (bmiValue < 25)
            return "NORMAL";
        else if (bmiValue < 30)
            return "OVERWEIGHT";
        else if (bmiValue < 35)
            return "MODERATELY OBESE";
        else
            return "SEVERELY OBESE";
    }

    /**
     * Returns whether or not the member is at their ideal weight based on their height and latest assessment weight.
     * In the absence of an assessment, the member's start weight is used instead.
     *
     * @param member
     * @param assessment
     * @return boolean
     */
    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        double heightInInches = member.height * INCHES_IN_METER;
        double idealWeight;
        double limitWeight;
        double currentWeight;

        // Set lower limit weight based on gender
        if (member.gender.equals("M"))
            limitWeight = 50;
        else
            limitWeight = 45.5;

        // Calc additional weight per inch over height limit
        if (heightInInches <= HEIGHT_LIMIT)
            idealWeight = limitWeight;
        else
            idealWeight = limitWeight + ((heightInInches - HEIGHT_LIMIT) * KILOS_PER_INCH);

        // If no recorded assessments, use starting weight
        if (assessment == null) {
            currentWeight = member.getStartWeight();
        } else {
            currentWeight = assessment.getWeight();
        }
        // Round the figure, return the absolute value and check if within tolerance
        return Math.abs(round((currentWeight - idealWeight), 2)) <= TOLERANCE;
    }

    /**
     * Returns a figure rounded to the specified number of decimal places
     *
     * @param value
     * @param places
     * @return
     * @see <a href="https://www.baeldung.com/java-round-decimal-number">https://www.baeldung.com/java-round-decimal-number</a>
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Calculates the current BMI value based on the member's height and weight taken from their latest assessment.
     * In the absensce of an assessment, the member's starting weight is used.
     *
     * @param member
     * @param assessment
     * @return
     */
    public static double calculateBMI(Member member, Assessment assessment) {
        if (assessment == null)
            return round(member.getStartWeight() / Math.pow(member.getHeight(), 2),2);
        else
            return round(assessment.getWeight() / Math.pow(member.getHeight(), 2),2);
    }
}
