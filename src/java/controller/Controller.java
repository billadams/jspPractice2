/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.FormValidation;
import business.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DateUtil;

/**
 *
 * @author fssco
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/display.jsp";
        ArrayList<String> messages = new ArrayList<String>();
        HttpSession session = request.getSession();
        boolean isNewSession = session.isNew();
        String birthDateInputString = null;
        String hireDateInputString = null;

        String action = request.getParameter("action");
        if (action == null) {
            action = "first";
        }

//        ArrayList<Person> arrList = new ArrayList<Person>();
//        arrList.add(new Person("Pris", "", "Stratton", "731",
//                LocalDate.of(2016, Month.FEBRUARY, 14), LocalDate.of(2016, Month.FEBRUARY, 14)));
//        arrList.add(new Person("Roy", "", "Batty", "734",
//                LocalDate.of(2016, Month.JANUARY, 8), LocalDate.of(2016, Month.JANUARY, 9)));
//        request.setAttribute("arrList", arrList);
        
        ArrayList<Person> employeeList = (ArrayList<Person>) session.getAttribute("employeeList");
        if  (employeeList == null) {
            employeeList = new ArrayList<Person>();
            
            employeeList.add(new Person("Pris", "", "Stratton", "731",
                    LocalDate.of(2016, Month.FEBRUARY, 14), LocalDate.of(2016, Month.FEBRUARY, 14)));
            employeeList.add(new Person("Roy", "", "Batty", "734",
                    LocalDate.of(2016, Month.JANUARY, 8), LocalDate.of(2016, Month.JANUARY, 9)));
            
            session.setAttribute("employeeList", employeeList);
        }

        if (action.equals("first")) {
            url = "/display.jsp";
        }
        else if (action.equals("addEmployee")) {
            String validationMessage = "";
            LocalDate birthDate = null;
            LocalDate hireDate = null;
            
            // Validate the form input.
            String firstName = request.getParameter("firstName");
            validationMessage = FormValidation.validateStringInput(firstName, "First name");
            if (!validationMessage.equals("")) {
                messages.add(validationMessage);
            }
                          
            String middleName = request.getParameter("middleName");
            
            String lastName = request.getParameter("lastName");
            validationMessage = FormValidation.validateStringInput(lastName, "Last name");
            if (!validationMessage.equals("")) {
                messages.add(validationMessage);
            }
            
            String employeeID = request.getParameter("employeeID");
            validationMessage = FormValidation.validateIntegerInput(employeeID, "Employee ID");
            if (!validationMessage.equals("")) {
                messages.add(validationMessage);
            }
            
            String birthDateString = request.getParameter("birthDate");
            validationMessage = FormValidation.validateDateInput(birthDateString, "Birth date");
            if (!validationMessage.equals("")) {
                messages.add(validationMessage);
            }
            else {
                birthDate = LocalDate.parse(birthDateString);
                birthDateInputString = DateUtil.createFormattedDateInputString(birthDate);
            }
            
            String hireDateString = request.getParameter("hireDate");
            validationMessage = FormValidation.validateDateInput(hireDateString, "Hire date");
            if (!validationMessage.equals("")) {
                messages.add(validationMessage);
            }
            else {
                hireDate = LocalDate.parse(hireDateString);
                hireDateInputString = DateUtil.createFormattedDateInputString(hireDate);
            }
            
            if (messages.isEmpty()) {
                employeeList.add(new Person(firstName, middleName, lastName, employeeID, birthDate, hireDate));
                session.setAttribute("employeeList", employeeList);
            }
            else {
                request.setAttribute("messages", messages);   
            }    

            
            request.setAttribute("firstName", firstName);
            request.setAttribute("middleName", middleName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("employeeID", employeeID);
            request.setAttribute("birthDateInputString", birthDateInputString);
            request.setAttribute("hireDateInputString", hireDateInputString);
        }
        else if (action.equals("removeEmployee")) {
            String indexString = request.getParameter("arrayIndex");
//            boolean indexExists = false;
            int index = -1;
            
            try {
                index = Integer.parseInt(indexString);   
            }
            catch (Exception e) {
                messages.add("The employee index specified is not a valid integer.");
//                request.setAttribute("messages", messages);
            }
            
            if (index >= 0 && index < employeeList.size()) {
                employeeList.remove(index);
                messages = null;
            }
            else {
                messages.add("The specified employee index does not exist.");
            }
            
            request.setAttribute("messages", messages);
        }
        else if (action.equals("destroySession")) {
            session.invalidate();
        }
        
        request.setAttribute("isNewSession", isNewSession);

        ServletContext sc = getServletContext();

        sc.getRequestDispatcher(url)
                .forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
