package controllers;

import play.*;
import play.mvc.*;

public class About extends Controller
{

  public static void index() {
    Logger.info("Rendering about");
    String type;
    if (session.contains("logged_in_Trainerid"))
      type = "trainer";
    else
      type = "member";
    render ("about.html", type);
  }
}
