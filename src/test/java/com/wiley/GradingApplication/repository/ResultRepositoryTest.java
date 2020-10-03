package com.wiley.GradingApplication.repository;

import com.wiley.GradingApplication.model.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@DataJpaTest
class ResultRepositoryTest {

    @Autowired
     private TeacherRepository teacherRepository;


    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void should_findAll_is_empty() {
        Iterable<Teacher> tutorials = teacherRepository.findAll();

        assertThat(tutorials).isEmpty();
    }

    @Test
    public void should_save_Teacher() {
        Teacher tutorial = teacherRepository.save(new Teacher(1, "Desc1", 220));

        assertThat(tutorial).hasFieldOrPropertyWithValue("teacherId", 1);
        assertThat(tutorial).hasFieldOrPropertyWithValue("teacherName", "Desc1");
        assertThat(tutorial).hasFieldOrPropertyWithValue("teacherAge", 220);
    }

    @Test
    public void should_find_all_teacher(){

        Teacher tut1 = new Teacher(11, "Desc1", 220);
        entityManager.merge(tut1);

        Teacher tut2 = new Teacher(21, "Desc2", 210);
        entityManager.merge(tut2);

        Teacher tut3 =  new Teacher(31, "Desc3", 200);
        entityManager.merge(tut3);

        Iterable<Teacher> tutorials = teacherRepository.findAll();
        assertThat(tutorials).hasSize(3);
    }

    @Test
    public void should_find_Teacher_by_id() {

        Teacher tut2 = entityManager.merge(new Teacher(20, "Desc2", 210));
        Teacher foundTutorial = teacherRepository.findById(tut2.getTeacherId()).get();
        assertThat(foundTutorial).isEqualTo(tut2);
    }

    @Test
    public void should_delete_Teacher_by_id() {
        Teacher tut1 = new Teacher(100, "Desc1", 220);
        entityManager.merge(tut1);

        Teacher tut2 = new Teacher(200, "Desc2", 210);
        Teacher tut = entityManager.merge(tut2);

        Teacher tut3 =  new Teacher(300, "Desc3", 200);
        entityManager.merge(tut3);

        teacherRepository.deleteById(tut.getTeacherId());
        Iterable<Teacher> tutorials = teacherRepository.findAll();
        assertThat(tutorials).hasSize(2);
    }

    @Test
    public void should_delete_all_tutorials() {
        entityManager.merge(new Teacher(1000, "Desc1", 220));
        entityManager.merge(new Teacher(2000, "Desc2", 230));

        teacherRepository.deleteAll();
        assertThat(teacherRepository.findAll()).isEmpty();
    }

    @Test
    public void should_update_Teacher_by_id() {


        Teacher tut2 = entityManager.merge(new Teacher(20000, "Desc2", 210));
        Teacher comperingObj = new Teacher(30000, "Desc3", 30);
        Teacher tutSaved = teacherRepository.findById(tut2.getTeacherId()).get();


        tutSaved.setTeacherAge(comperingObj.getTeacherAge());
        tutSaved.setTeacherName(comperingObj.getTeacherName());
        Teacher tut22  = teacherRepository.save(tutSaved);

        Teacher checkTut = teacherRepository.findById(tut22.getTeacherId()).get();

        assertThat(checkTut.getTeacherAge()).isEqualTo(comperingObj.getTeacherAge());
        assertThat(checkTut.getTeacherName()).isEqualTo(comperingObj.getTeacherName());
    }

//    @Test
//    void findByStudent_StudentIdAndQuestion_Assignment_AssignmentIdAndQuestion_Assignment_Course_CourseId() {
//    }
//
//    @Test
//    void findByTeacher_TeacherIdAndQuestion_Assignment_AssignmentIdAndQuestion_Assignment_Course_CourseId() {
//    }
}