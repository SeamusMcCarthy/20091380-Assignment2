package controllers;

import play.Logger;
import play.mvc.Controller;
import models.Trainer;
import models.Member;

public class Profile extends Controller
{
  public static void index() {
    Logger.info("Rendering Profile");
    String type;
    if (session.contains("logged_in_Trainerid")) {
      type = "trainer";
      Trainer trainer = Accounts.getLoggedInTrainer();
      render("profile.html", trainer, type);
    } else {
      type = "member";
      Member member = Accounts.getLoggedInMember();
      render("profile.html", member, type);
    }
  }
}
