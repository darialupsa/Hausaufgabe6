package com.example.hausaufgabe6;

import com.example.hausaufgabe6.controller.RegistrationSystem;
import com.example.hausaufgabe6.entities.Student;
import com.example.hausaufgabe6.entities.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class HelloController {
    public final RegistrationSystem registrationSystem;
    @FXML
    public Label Credits;

    public HelloController() throws SQLException {

        this.registrationSystem = new RegistrationSystem();
    }
    @FXML
    private Label GiveYourIdStudent;
    @FXML
    private TextField StudentId;
    @FXML
    protected void helloStudent() throws IOException {
        HelloApplication.openNewWindow("logInStudent.fxml");
    }
    @FXML
    private Label GiveYourIdTeacher;
    @FXML
    private TextField TeacherId;
    @FXML
    protected void helloTeacher() throws IOException {
        HelloApplication.openNewWindow("logInTeacher.fxml");
    }

    @FXML
    private Label studentIsNotFound;
    @FXML
    protected void studentsActions() throws IOException, SQLException {
        int studentId;
        int exist = 0;
        studentId = parseInt(StudentId.getText());
        for (Student student:registrationSystem.getAllStudents())
        {
            if(studentId == student.getStudentId()){
                studentIsNotFound.setText(" ");
                exist++;
                HelloApplication.openNewWindow("studentsActions.fxml");
            }
        }
        if (exist == 0){
            studentIsNotFound.setText("Student not found");
        }
    }


    @FXML
    private Label teacherIsNotFound;
    @FXML
    protected void teachersActions() throws IOException, SQLException {
        int teacherId;
        int exist = 0;
        teacherId = parseInt(TeacherId.getText());
        for (Teacher teacher:registrationSystem.getAllTeachers())
        {
            if(teacherId == teacher.getTeacherId()){
                teacherIsNotFound.setText("");
                exist++;
                HelloApplication.openNewWindow("teachersActions.fxml");
            }
        }
        if (exist == 0){
            teacherIsNotFound.setText("Teacher not found");
        }
    }

    @FXML
    private Label Register;
    @FXML
    private TextField CourseId;

    @FXML
    protected void registrationForStudent() throws SQLException {
        registrationSystem.register(parseInt(StudentId.getText()), parseInt(CourseId.getText()));
    }

    @FXML
    protected void creditsNumber() throws SQLException {
        Credits.setText(String.valueOf(registrationSystem.getStudentRepository().find(parseInt(StudentId.getText())).getTotalCredits()));

    }

    @FXML
    private ListView listView;
    @FXML
    protected void showStudents() throws SQLException {
        List<Student> students = registrationSystem.retrieveStudentsEnrolledForACourse(Integer.parseInt(CourseId.getText()));
        listView.getItems().clear();
        List<String> students2 = students.stream().map(Student::toString).collect(Collectors.toList());
        listView.getItems().addAll(students2);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


}