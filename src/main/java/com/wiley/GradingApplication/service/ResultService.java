package com.wiley.GradingApplication.service;

import com.wiley.GradingApplication.dto.*;
import com.wiley.GradingApplication.dto.common.GenericResponse;
import com.wiley.GradingApplication.model.Question;
import com.wiley.GradingApplication.model.Result;
import com.wiley.GradingApplication.model.Student;
import com.wiley.GradingApplication.repository.QuestionRepository;
import com.wiley.GradingApplication.repository.ResultRepository;
import com.wiley.GradingApplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<GenericResponse> getResultForStudent(int studentId, int assignmentId, int courseId) throws Exception {

        List<Result> assignmentList = resultRepository.findByStudent_StudentIdAndQuestion_Assignment_AssignmentIdAndQuestion_Assignment_Course_CourseId(studentId, assignmentId, courseId);
        if (assignmentList.size() > 0) {
            ResultCounterDto resultCounterDto = resultCounter(assignmentList);
            ArrayList<StudentResultDetDto> studentResultDetDtoList = new ArrayList<>();
            StudentResultDetDto studentResultDetDto = new StudentResultDetDto("Total Questions", assignmentList.size(), 10, assignmentList.size() * 10);
            StudentResultDetDto studentResultDetDto1 = new StudentResultDetDto("Correct Answers", resultCounterDto.getCorrectAns(), 10, resultCounterDto.getCorrectAns() * 10);
            StudentResultDetDto studentResultDetDto2 = new StudentResultDetDto("Partially Correct Answers", resultCounterDto.getPartialCorrectAns(), 5, resultCounterDto.getPartialCorrectAns() * 5);
            StudentResultDetDto studentResultDetDto3 = new StudentResultDetDto("Incorrect Answers", resultCounterDto.getIncorrectAns(), 0, 0);
            StudentResultDetDto studentResultDetDto4 = new StudentResultDetDto("Total", 0, 0, resultCounterDto.getCorrectAns() * 10 + resultCounterDto.getPartialCorrectAns() * 5);

            studentResultDetDtoList.add(studentResultDetDto);
            studentResultDetDtoList.add(studentResultDetDto1);
            studentResultDetDtoList.add(studentResultDetDto2);
            studentResultDetDtoList.add(studentResultDetDto3);
            studentResultDetDtoList.add(studentResultDetDto4);
            GenericResponse genericResponse = new GenericResponse(studentResultDetDtoList);
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private ResultCounterDto resultCounter(List<Result> assignmentList) {
        int correctAns = 0;
        int incorrectAns = 0;
        int partialCorrectAns = 0;
        ResultCounterDto resultCounterDto = new ResultCounterDto();

        for (Result resultObj : assignmentList) {
            String result = resultObj.getResult().toLowerCase();
            switch (result) {
                case "pass":
                    correctAns = correctAns + 1;
                    break;
                case "fail":
                    incorrectAns = incorrectAns + 1;
                    break;
                case "partial":
                    partialCorrectAns = partialCorrectAns + 1;
                    break;
            }
            resultCounterDto.setCorrectAns(correctAns);
            resultCounterDto.setIncorrectAns(incorrectAns);
            resultCounterDto.setPartialCorrectAns(partialCorrectAns);
        }
        return resultCounterDto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<GenericResponse> getQuestionsForReview(int studentId, int assignmentId, int courseId) throws Exception {

        List<Result> assignmentList = resultRepository.findByStudent_StudentIdAndQuestion_Assignment_AssignmentIdAndQuestion_Assignment_Course_CourseId(studentId, assignmentId, courseId);
        List<ReviewQuesDto> reviewQuesDtoList = new ArrayList<>();
        if (assignmentList.size() > 0) {

            for (Result resultObj : assignmentList) {
                ReviewQuesDto reviewQuesDto = new ReviewQuesDto(resultObj.getQuestion().getQuestionDes(), resultObj.getTimeSpent(), resultObj.getNumberOfAttempts(), resultObj.getResult().toUpperCase());
                reviewQuesDtoList.add(reviewQuesDto);
            }
            GenericResponse genericResponse = new GenericResponse(reviewQuesDtoList);
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @Transactional(readOnly = true)
    public ResponseEntity<GenericResponse> getStaticsForQuestions(int teacherId, int assignmentId, int courseId) throws Exception {

        List<Result> assignmentList = resultRepository.findByTeacher_TeacherIdAndQuestion_Assignment_AssignmentIdAndQuestion_Assignment_Course_CourseId(teacherId, assignmentId, courseId);

        List<QuesStaticsDto> reviewQuesDtoList = new ArrayList<>();

        List<Question> questionList = questionRepository.findAll();

        if (assignmentList.size() > 0) {

            for (Question questionObj : questionList) {
                double correctAns = 0;
                double incorrectAns = 0;
                double partially = 0;
                double answeringCount = 0;
                double wholeTimeForOneQuestion = 0;

                for (Result resultObj : assignmentList) {
                    if (questionObj.getQuestionId().equals(resultObj.getQuestion().getQuestionId())) {

                        String result = resultObj.getResult().toLowerCase();
                        switch (result) {
                            case "pass":
                                correctAns = correctAns + 1;
                                break;
                            case "fail":
                                incorrectAns = incorrectAns + 1;
                                break;
                            case "partial":
                                partially = partially + 1;
                                break;
                        }
                        wholeTimeForOneQuestion = wholeTimeForOneQuestion + resultObj.getTimeSpent();
                        answeringCount = answeringCount + 1;
                    }
                }
                double correctAnsAvg = (correctAns / answeringCount) * 100;
                double incorrectAnsAvg = (incorrectAns / answeringCount) * 100;
                double partiallyAvg = (partially / answeringCount) * 100;
                double timeAvgForQuestion = wholeTimeForOneQuestion / answeringCount;
                QuesStaticsDto quesStaticsDto = new QuesStaticsDto(questionObj.getQuestionDes(), correctAnsAvg, incorrectAnsAvg, partiallyAvg, timeAvgForQuestion);
                reviewQuesDtoList.add(quesStaticsDto);
            }
            GenericResponse genericResponse = new GenericResponse(reviewQuesDtoList);
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }


    @Transactional(readOnly = true)
    public ResponseEntity<GenericResponse> getOverallGrade(int teacherId, int assignmentId, int courseId) throws Exception {

        List<Result> assignmentList = resultRepository.findByTeacher_TeacherIdAndQuestion_Assignment_AssignmentIdAndQuestion_Assignment_Course_CourseId(teacherId, assignmentId, courseId);

        List<Student> studentList = studentRepository.findAll();

        List<StudentMarksDto> studentMarksDtoList = new ArrayList<>();

        if (assignmentList.size() > 0) {

            for (Student student : studentList) {

                double correctAns = 0;
                double partially = 0;

                for (Result resultObj : assignmentList) {

                    if (student.getStudentId().equals(resultObj.getStudent().getStudentId())) {

                        String result = resultObj.getResult().toLowerCase();
                        switch (result) {
                            case "pass":
                                correctAns = correctAns + 1;
                                break;
                            case "partial":
                                partially = partially + 1;
                                break;
                        }
                    }
                }
                double wholeMark = correctAns * 10 + partially * 5;
                StudentMarksDto studentMarksDto = new StudentMarksDto(student.getStudentName(), wholeMark);
                studentMarksDtoList.add(studentMarksDto);
            }
            if(studentMarksDtoList.size() > 0){
                ArrayList<GradeDetDto> gradeDetDtoArrayList = studentGradeCalculator(studentMarksDtoList);
                GenericResponse genericResponse = new GenericResponse(gradeDetDtoArrayList);
                return new ResponseEntity<>(genericResponse, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private ArrayList<GradeDetDto> studentGradeCalculator(List<StudentMarksDto> studentMarksDtoList) {

        ArrayList<GradeDetDto> gradeDetDtoArrayList = new ArrayList<>();
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (StudentMarksDto studentMarksDto : studentMarksDtoList) {
            if (studentMarksDto.getWholeMark() >= 80) {
                a = a + 1;
            } else if (studentMarksDto.getWholeMark() >= 60 && studentMarksDto.getWholeMark() < 80) {
                b = b + 1;
            } else if (studentMarksDto.getWholeMark() >= 40 && studentMarksDto.getWholeMark() < 60) {
                c = c + 1;
            } else {
                d = d + 1;
            }
        }
        GradeDetDto gradeDetDto = new GradeDetDto("A", a);
        GradeDetDto gradeDetDto1 = new GradeDetDto("B", b);
        GradeDetDto gradeDetDto2 = new GradeDetDto("C", c);
        GradeDetDto gradeDetDto3 = new GradeDetDto("D", d);
        gradeDetDtoArrayList.add(gradeDetDto);
        gradeDetDtoArrayList.add(gradeDetDto1);
        gradeDetDtoArrayList.add(gradeDetDto2);
        gradeDetDtoArrayList.add(gradeDetDto3);

        return gradeDetDtoArrayList;

    }


}
