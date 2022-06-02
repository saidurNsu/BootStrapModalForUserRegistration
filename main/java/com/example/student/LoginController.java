package com.example.student;


import com.example.student.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    StudentRepository repo;
    LoginRepository loginRepository;
    UserRepository userRepository;

    @Autowired
    public LoginController(StudentRepository repo, LoginRepository loginRepository,UserRepository userRepository) {
        this.repo = repo;
        this.loginRepository=loginRepository;
        this.userRepository=userRepository;
    }

    //   for view data button
    //  get list of students from db
    @GetMapping("/getAllStudentsDb")
    public List<StudentForm> getAllDbStudents(){
        return  repo.findAll();
    };



    //show edit form
    @GetMapping("/showForm")
    public String showFormpage(Model model){
        StudentForm student= new StudentForm();
        model.addAttribute("students",student);
        return "create_form";
    };


    //turn this on for login landind normal
//    @GetMapping("/")
//    public String showLoginForm(){
//        return "view_login_form";
//    };



    @GetMapping("/home")
    public String showCreateFormAgain(Model model,@ModelAttribute("user") Login user){
        StudentForm student= new StudentForm();
        model.addAttribute("students",student);
        model.addAttribute("userName",user.getUserName());
        return "new_create_form";
    }



    @GetMapping(value = "/")
    public String getLogin(Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            User loggedInUser = userRepository.findUserByUsername(auth.getName());
//                    .orElseThrow(Exception::new);
            /* The user is logged in :) */
            return "redirect:/home";
        }
//        return "view_login_form";
        UserModel userModel =new UserModel();
        model.addAttribute("userModel", userModel);
        return "view_login_form_with_modal";
    }


// edit item
    @GetMapping("/showEditForm/{id}")
    public String showFormpagex(Model model, @PathVariable("id") Long id){
        StudentForm studentModel=repo.findStudentFormById(id);
        model.addAttribute("students",studentModel);
        return "edit";
    };

    @PostMapping("403")
    public String showFormpagex(){
        return "403";
    };


    //controller for landing page
    @GetMapping("/landing")
    public String landingPage(Model model){
        List<StudentForm> studentList= new ArrayList<>();
        studentList = repo.findAll();
        List<StudentForm> studentModelList= new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Long countStudent = repo.count();

        StudentForm student = null;
        if (studentList != null & studentList.size() > 0) {
            for (StudentForm studentForm : studentList) {
                student = new StudentForm();
                student.setId(studentForm.getId());
                student.setFirstName(studentForm.getFirstName());
                student.setLastName(studentForm.getLastName());
                student.setDepartment(studentForm.getDepartment());
                studentModelList.add(student);
            }
        }
        map.put("totalNotFiltered", countStudent);
        map.put("rows", studentModelList);
        model.addAttribute("students",studentModelList);
        model.addAttribute("saidur","saidur");

        return "register_success";
    }

//    @PostMapping("/logout")
//    public String logoutDo(){
////    {   request.getSession().invalidate();
//        return "redirect:/";
//    }







    // new mwthod for jpa query
//     for saving  updating  a new student
    @PostMapping("/addNewStudentsDb/{id}")
    public String addNewStudent(Model model,@ModelAttribute("students")  StudentForm students, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute(students);
            repo.save(students);
            return "details";
        }
            repo.save(students);
        return "details";
    };



    @GetMapping("/addStudentList/{id}")
    public String showDeatailsPagex(Model model, @PathVariable("id") Long id){
        StudentForm studentModel=repo.findStudentFormById(id);
        model.addAttribute("students",studentModel);
        return "details";
    };

    //  Delete
    @GetMapping("/deleteFromDb/{id}")
    public String deleteStudentDb(@PathVariable("id") Long id)  {
        repo.deleteStudentFormById(id);
        return "success";
    };




}
