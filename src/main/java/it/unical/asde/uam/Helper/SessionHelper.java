package it.unical.asde.uam.Helper;

import it.unical.asde.uam.model.Administrator;
import it.unical.asde.uam.model.Professor;
import it.unical.asde.uam.model.Student;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Francesco Bruno
 */
public class SessionHelper {

    /**
     * 
     * @param session
     * @return true if user is anonym user and not logged in
     */
    public static boolean isGuest(HttpSession session) {
        return SessionHelper.getSessionAttribute("user", session) == null;
    }

    /**
     * 
     * @param session
     * @return true if user is logged in
     */
    public static boolean isLogged(HttpSession session) {
        return SessionHelper.getSessionAttribute("user", session) != null;
    }
    
    public static boolean isAdmin(HttpSession session) {
        return ((String)SessionHelper.getUserProfile(session)).equals(UserProfileHelper._ADMINISTRATOR);                        
    }
    
    public static boolean isProfessor(HttpSession session) {
        return ((String)SessionHelper.getUserProfile(session)).equals(UserProfileHelper._PROFESSOR);                               
    }
    
    public static boolean isStudent(HttpSession session) {
        return ((String)SessionHelper.getUserProfile(session)).equals(UserProfileHelper._STUDENT);                
    }

    
    /**
     * 
     * Return the string with the user profile type:
     * administrator, professor, student, guest 
     * 
     * guest is return if the function is invoked and the user is not logged in
     *
     * @param session
     * @return string with a user profile if a user is logged, else it return guest
     */
    public static String getUserProfile(HttpSession session) {
        String profile = (String)SessionHelper.getSessionAttribute("profile", session);
        if (profile == null) {
            profile =  UserProfileHelper._GUEST;
        }
        return profile;
    }

   
    /**
     * It set the user attribute with an Professor istance
     * and the profile attribute as "professor"
     * 
     * @param user The administrator object to store in session
     * @param session 
     */
    public static void setUserProfessorLogged(Professor user, HttpSession session) {
        session.setAttribute("user", user);
        session.setAttribute("profile", UserProfileHelper._PROFESSOR);
    }

    /**
     * It set the user attribute with an Administrator istance
     * and the profile attribute as "administrator"
     * 
     * @param user The administrator object to store in session
     * @param session 
     */
    public static void setUserAdministratorLogged(Administrator user, HttpSession session) {
        session.setAttribute("user", user);
        session.setAttribute("profile",  UserProfileHelper._ADMINISTRATOR);
    }

    
    /**
     * It set the user attribute with an Studente istance
     * and the profile attribute as "studente"
     * 
     * @param user The administrator object to store in session
     * @param session 
     */
    public static void setUserStudentLogged(Student user, HttpSession session) {
        session.setAttribute("user", user);
        session.setAttribute("profile", UserProfileHelper._STUDENT);
    }
    
    
    public static void cleanSession(HttpSession session){
        session.setAttribute("user",null);
        session.setAttribute("profile",null);
    }

    private static Object getSessionAttribute(String attribute, HttpSession session) {
        return session.getAttribute(attribute);        
    }

}
