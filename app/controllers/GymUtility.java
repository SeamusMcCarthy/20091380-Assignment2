package controllers;
import models.Assessment;
import models.Member;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class GymUtility {

    private static final double INCHES_IN_METER = 39.37;
    private static final double KILOS_PER_INCH = 2.3;
    private static final int HEIGHT_LIMIT = 60;
    private static final double TOLERANCE = 0.2;

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

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double calculateBMI(Member member, Assessment assessment) {
        if (assessment == null)
            return round(member.getStartWeight() / Math.pow(member.getHeight(), 2),2);
        else
            return round(assessment.getWeight() / Math.pow(member.getHeight(), 2),2);
    }
}
