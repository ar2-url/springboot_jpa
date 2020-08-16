package pl.wojcikiewicz.studentsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wojcikiewicz.studentsapp.model.Student;
import pl.wojcikiewicz.studentsapp.repository.StudentRepository;
import pl.wojcikiewicz.studentsapp.service.StudentService;

import java.util.List;

@Controller
public class StudentController {

    private static final String PAGE_TITLE = "Students";

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = {"/", "/index"})
    public String displayStartPage(Model model) {
        model.addAttribute("pageTitle", "Students");

        return "index";
    }

    // http://localhost:8080/students?page=1
    @GetMapping(value = "/students")
    public String getStudents(Model model,
                              @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        List<Student> students = studentService.findAll();

        model.addAttribute("students", students);

        return "students-list";
    }

//    @GetMapping(value = "/students/{email}")
//    public String getStudentByEmail(Model model,
//                                    @PathVariable String email) {
//
//        Student student = student = studentService.findByEmail(email);
//
//        if (student == null) {
//            model.addAttribute("errorMessage", "Contact not found");
//        }
//
//        model.addAttribute("student", student);
//
//        return "student-details";
//
//    }

    @GetMapping(value = "/students/{studentId}")
    public String getStudentById(Model model,
                                 @PathVariable Integer studentId) {

        Student student = studentService.findById(studentId);

        if (student == null) {
            model.addAttribute("errorMessage", "Student not found");
        }

        model.addAttribute("student", student);

        return "student-details";

    }

    @GetMapping(value = "/students/add")
    public String addStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("addStudent", true);

        return "add-edit-student";
    }

    @PostMapping(value = "/students/add")
    public String addStudent(Model model,
                             @ModelAttribute("student") Student student) {
        student = studentService.add(student);

        return "redirect:/students/" + student.getId();
    }

    @GetMapping(value = "/students/edit/{studentId}")
    public String editStudent(Model model,
                              @PathVariable Integer studentId) {
        Student student = studentService.findById(studentId);

        if (student == null) {
            model.addAttribute("errorMessage", "Student not found");
        }

        model.addAttribute("student", student);
        model.addAttribute("addStudent", false);

        return "add-edit-student";
    }

    @PostMapping(value = "/students/edit")
    public String editStudent(Model model,
                              @ModelAttribute("student") Student student) {
        // todo do validation
        // todo update student's details

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        return "students-list";
    }
}
