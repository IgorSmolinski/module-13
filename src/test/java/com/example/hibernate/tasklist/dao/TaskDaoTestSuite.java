package com.example.hibernate.tasklist.dao;


import com.example.hibernate.task.Task;
import com.example.hibernate.task.TaskFinancialDetails;
import com.example.hibernate.tasklist.TaskList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
    @SpringBootTest

    public class TaskDaoTestSuite {
        @Autowired
        private TaskDao taskDao;
        @Autowired
        private TaskListDao taskListDao;
        private static final String DESCRIPTION = "Test: Learn Hibernate";
        private static final String LISTNAME = " List things to do.";

        @Test
        public void testTaskDaoSave() {
            //Given
            Task task = new Task(DESCRIPTION, 7);

            //When
            taskDao.save(task);

            //Then
            int id = task.getId();
            Task readTask = taskDao.findOne(id);
            Assert.assertEquals(id, readTask.getId());

            //CleanUp
            taskDao.delete(id);
        }
        @Test
            public void testTaskDaoFindByDuration(){
            //Given
            Task task = new Task(DESCRIPTION, 7);
            taskDao.save(task);
            int id2 = task.getId();
            int duration = task.getDuration();

            //When
            List<Task> readTask = taskDao.findByDuration(duration);

            //Then
            Assert.assertEquals(1, readTask.size());

            //CleanUp
            //int id = readTask.get(0).getId();
            taskDao.delete(id2);
        }

        @Test
        public void testTaskDaoSaveWithFinancialDetails(){
            //Given
            Task task = new Task(DESCRIPTION, 7);
            task.setTaskFinancialDetails(new TaskFinancialDetails(new BigDecimal(120), false));


            //When
            taskDao.save(task);
            int id = task.getId();

            //Then
            Assert.assertNotEquals(0,id);

            //CleanUp
            taskDao.delete(id);

        }

        @Test
        public void testTaskListDaoSaveWithTask(){
            Task task = new Task("Test: What's is going on?", 14);
            Task task2 = new Task("Nothing in particular", 7);
            TaskFinancialDetails tfd = new TaskFinancialDetails(new BigDecimal(20), false);
            TaskFinancialDetails tfd2 = new TaskFinancialDetails(new BigDecimal(10), false);
            task.setTaskFinancialDetails(tfd);
            task2.setTaskFinancialDetails(tfd2);
            TaskList taskList = new TaskList(LISTNAME, "To do tasks");
            taskList.getTasks().add(task);
            taskList.getTasks().add(task2);
            task.setTaskList(taskList);
            task2.setTaskList(taskList);

            //When
            taskListDao.save(taskList);
            int id = taskList.getId();

            //Then
            Assert.assertNotEquals(0, id);

            //CleanUp
            taskListDao.delete(id);


        }
    }