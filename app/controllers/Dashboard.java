package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.text.SimpleDateFormat;
import java.util.*;

public class Dashboard extends Controller
{
  public static void checkIndex() {
    if (session.contains("logged_in_Trainerid"))
      trainerIndex();
    else
      memberIndex();
  }

  public static void memberIndex() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessmentList = member.assessments;
    Collections.sort(assessmentList, Collections.reverseOrder());
    List<Double> weights = new ArrayList<>();

    int count = 0;
    for (Assessment assessment : assessmentList) {
      if (count == assessmentList.size() - 1)
        weights.add(member.startWeight);
      else
        weights.add(assessmentList.get(count + 1).weight);
      count++;
    }
    Assessment latestAssessment = null;
    if (assessmentList.size() != 0)
      latestAssessment = assessmentList.get(0);
    double BMI = GymUtility.calculateBMI(member, latestAssessment);
    String level = GymUtility.determineBMICategory(BMI);
    String BMIValue = String.format("%.2f", BMI);
    boolean idealWeight = GymUtility.isIdealBodyWeight(member, latestAssessment);
    render ("dashboard.html", member, assessmentList, weights, BMIValue, level, idealWeight);
  }

  public static void trainerIndex() {
    Logger.info("Rendering Trainer Dashboard");
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> members = Member.findAll();
    render("trainerdashboard.html", trainer, members);
  }

  public static void addAssessment(Long id, double weight, double chest, double thigh, double arm, double waist, double hips)
  {
    Member member = Accounts.getLoggedInMember();
    Date date = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
    Assessment assessment = new Assessment(ft.format(date), weight, chest, thigh, arm, waist, hips);
    member.assessments.add(assessment);
    member.save();
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long assessmentid) {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    redirect("/dashboard");
  }

  public static void listAssessment(Long id) {
    Logger.info("Rendering Assessment List for Trainer");
    Trainer trainer = Accounts.getLoggedInTrainer();
    Member member = Member.findById(id);
    List<Assessment> assessmentList = member.assessments;
    Collections.sort(assessmentList, Collections.reverseOrder());
    List<Double> weights = new ArrayList<>();

    int count = 0;
    for (Assessment assessment : assessmentList) {
      if (count == assessmentList.size() - 1)
        weights.add(member.startWeight);
      else
        weights.add(assessmentList.get(count + 1).weight);
      count++;
    }
    render ("userassessments.html", trainer, member, assessmentList, weights);
  }

  public static void updateComment(Long id, Long assessmentid, String comment) {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(assessmentid);
    assessment.comment = comment;
    assessment.save();
    redirect("/dashboard");
  }
}
