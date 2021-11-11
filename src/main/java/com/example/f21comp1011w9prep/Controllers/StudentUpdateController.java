package com.example.f21comp1011w9prep.Controllers;

import com.example.f21comp1011w9prep.Models.Course;
import com.example.f21comp1011w9prep.Models.Student;
import com.example.f21comp1011w9prep.Utilities.MagicData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StudentUpdateController implements Initializable {

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Integer> studentNumCol;

    @FXML
    private TableColumn<Student, String> firstNameCol;

    @FXML
    private TableColumn<Student, String> lastNameCol;

    @FXML
    private TableColumn<Student, String> avgGradeCol;

    @FXML
    private TableColumn<Student, Integer> numOfCoursesCol;

    @FXML
    private TextField searchTextField;

    @FXML
    private ComboBox<Course> coursesComboBox;

    @FXML
    private Spinner<Integer> gradeSpinner;

    @FXML
    private Label rowsReturnedLabel;

    @FXML
    private Label studentSelectedLabel;

    @FXML
    private Button addCourseButton;

    @FXML
    private RadioButton allStudentRadioButton;

    @FXML
    private RadioButton honourRollRadioButton;

    @FXML
    private RadioButton top10RadioButton;

    private ToggleGroup toggleGroup;
    private ArrayList<Student> allStudents;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allStudents = MagicData.getStudents();

        //configuring the TableView
        studentNumCol.setCellValueFactory(new PropertyValueFactory<>("studNum"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        avgGradeCol.setCellValueFactory(new PropertyValueFactory<>("avgGradeString"));
        numOfCoursesCol.setCellValueFactory(new PropertyValueFactory<>("numOfCourses"));
        tableView.getItems().addAll(allStudents);
        rowsReturnedLabel.setText("Rows Returned: " + tableView.getItems().size());

        //configure the TableView selection mode
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, studentSelected) -> {
                    if (studentSelected != null)
                    {
                        studentSelectedLabel.setText(studentSelected.toString());
                        setStudentDetailsVisibility(true);
                    }
                    else
                    {
                        setStudentDetailsVisibility(false);
                    }
                }
        );

        setStudentDetailsVisibility(false);

        //configure the combobox
        coursesComboBox.setPromptText("Select a course");
        coursesComboBox.getItems().addAll(MagicData.getCourseCodes());

        //configure the spinner
        SpinnerValueFactory<Integer> gradeFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 75);
        gradeSpinner.setValueFactory(gradeFactory);
        gradeSpinner.setEditable(true);
        TextField spinnerEditor = gradeSpinner.getEditor();
        spinnerEditor.textProperty().addListener((observableValue, oldValue, newValue)->
        {
            try {
                Integer.parseInt(newValue);
            } catch (NumberFormatException e)
            {
                spinnerEditor.setText(oldValue);
            }
        });
    }

    /**
     * This method will toggle the visibility of the elements that allow the user
     * to add a course for the student
     */
    private void setStudentDetailsVisibility(boolean visibility) {
        studentSelectedLabel.setVisible(visibility);
        coursesComboBox.setVisible(visibility);
        gradeSpinner.setVisible(visibility);
        addCourseButton.setVisible(visibility);
    }

    private void updateLabels()
    {
    }

    @FXML
    private void addGrade()
    {
    }

}

