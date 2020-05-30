package controllers;

import play.*;
import play.mvc.*;

/**
 * Controller class for the About page
 *
 * @version 1.0
 * @author Seamus McCarthy
 */
public class About extends Controller
{
  /**
   * Establishes the user type and renders the About page
   */
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
