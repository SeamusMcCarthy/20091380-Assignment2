package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller
{
    public static void signup()
    {
        render("signup.html");
    }

    public static void login()
    {
        render("login.html");
    }

    public static void authenticate(String email, String password, String acctype)
    {
        if (acctype.equals("member")) {
            Member member = Member.findByEmail(email);
            if ((member != null) && (member.checkPassword(password))) {
                Logger.info("Authentication successful");
                session.put("logged_in_Memberid", member.id);
                redirect("/dashboard");
            } else {
                Logger.info("Authentication failed");
                redirect("/login");
            }
        } else {
            Trainer trainer = Trainer.findByEmail(email);
            if ((trainer != null) && (trainer.checkPassword(password))) {
                Logger.info("Authentication successful");
                session.put("logged_in_Trainerid", trainer.id);
                redirect("/trainerdashboard");
            } else {
                Logger.info("Authentication failed");
                redirect("/login");
            }
        }
    }

    public static void register(String name, String email, String password, String address, String gender, double height, double startWeight)
    {
        Logger.info("Registering new user " + email);
        Member member = new Member(name, email, password, address, gender, height, startWeight);
        member.save();
        redirect("/login");
    }

    public static void logout()
    {
        session.clear();
        redirect ("/");
    }

    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static Trainer getLoggedInTrainer()
    {
        Trainer trainer = null;
        if (session.contains("logged_in_Trainerid")) {
            String trainerId = session.get("logged_in_Trainerid");
            trainer = Trainer.findById(Long.parseLong(trainerId));
        } else {
            login();
        }
        return trainer;
    }

    public static void deleteMember(Long id) {
        Member member = Member.findById(id);
        member.delete();
        redirect("/dashboard");
    }

    public void updateMember(String name, String email, String password, String address, String gender, double height, double startWeight) {
        Member member = Accounts.getLoggedInMember();
        member.setName(name);
        member.setEmail(email);
        member.setPassword(password);
        member.setAddress(address);
        member.setGender(gender);
        member.setHeight(height);
        member.setStartWeight(startWeight);
        member.save();
        redirect("/dashboard");
    }

    public void updateTrainer(String name, String email, String password, String gender, String specialty) {
        Trainer trainer = Accounts.getLoggedInTrainer();
        trainer.setName(name);
        trainer.setEmail(email);
        trainer.setPassword(password);
        trainer.setGender(gender);
        trainer.setSpecialty(specialty);
        trainer.save();
        redirect("/trainerdashboard");
    }
}
