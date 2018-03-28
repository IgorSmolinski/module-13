package com.example.hibernate.tasklist.dao;

import com.example.hibernate.dao.TaskListDao;
import com.example.hibernate.tasklist.TaskList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskListDaoTestSuite {
    @Autowired
    private TaskListDao taskListDao;

    @Test
    public void TaskListDaoSave() {
        //Given
        TaskList taskList = new TaskList("toDo", "Things to do.");

        //When
        taskListDao.save(taskList);

        //Then
        int id = taskList.getId();
        TaskList readTaskList = taskListDao.findOne(id);
        Assert.assertEquals(id, readTaskList.getId());

        //CleanUp
        taskListDao.delete(id);
    }

    @Test
    public void findByListNameTest() {
        //Given
        TaskList taskList = new TaskList("Done", "Things that have been done.");
        String name = taskList.getListName();
        int id = taskList.getId();
        taskListDao.save(taskList);

        //When
        List<TaskList> readTaskList = taskListDao.findByListName(name);


        //Then
        Assert.assertEquals(name,readTaskList.get(id).getListName());
        Assert.assertEquals(1, readTaskList.size());

        //CleanUp
        int CleanUpId = readTaskList.get(id).getId();
        taskListDao.delete(CleanUpId);

    }
}
