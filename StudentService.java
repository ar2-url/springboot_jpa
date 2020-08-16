package pl.wojcikiewicz.studentsapp.service;

import org.springframework.stereotype.Service;
import pl.wojcikiewicz.studentsapp.model.Student;
import pl.wojcikiewicz.studentsapp.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id);
    }

    public Student add(Student student) {
        // do validation first
        return studentRepository.add(student);
    }
}
