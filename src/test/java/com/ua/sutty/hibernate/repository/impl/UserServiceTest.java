package com.ua.sutty.hibernate.repository.impl;

import com.ua.sutty.spring.app.Application;
import com.ua.sutty.spring.domain.Role;
import com.ua.sutty.spring.domain.User;
import com.ua.sutty.spring.service.UserService;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    private IDatabaseTester iDatabaseTester;

    @Before
    public void loadProperties() throws Exception {
        IDataSet iDataSet = new FlatXmlDataSetBuilder()
                .build(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset.xml"));
        iDatabaseTester = new DataSourceDatabaseTester(dataSource);
        iDatabaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        iDatabaseTester.setDataSet(iDataSet);
        iDatabaseTester.onSetup();
    }

    @Test(expected = NullPointerException.class)
    public void testSaveNull() {
        userService.save(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        userService.delete(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindUserByEmailNull() {
        userService.findUserByEmail(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindUserByLoginNull() {
        userService.findUserByLogin(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindOneByLoginNull() {
        userService.findOneByLogin(null);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteUserByIdNull() {
        userService.deleteUserById(null);
    }

    @Test
    public void findUserByLogin() throws Exception {
        ITable iTable = iDatabaseTester.getDataSet().getTable("user");
        User user = userService.findUserByLogin("secondUser");
        assertEquals("User after findUserByLogin must coincide with role in data base",
                user.getLogin(), iTable.getValue(1, "login"));
        assertEquals("User after findUserByLogin must coincide with role in data base",
                user.getEmail(), iTable.getValue(1, "email"));
    }

    @Test
    public void findOneByLogin() throws Exception {
        ITable iTable = iDatabaseTester.getDataSet().getTable("user");
        Optional<User> user = userService.findOneByLogin("secondUser");
        assertEquals("User after findUserByLogin must coincide with role in data base",
                user.get().getLogin(), iTable.getValue(1, "login"));
        assertEquals("User after findUserByLogin must coincide with role in data base",
                user.get().getEmail(), iTable.getValue(1, "email"));
    }

    @Test
    public void findBUserByEmail() throws Exception {
        ITable iTable = iDatabaseTester.getDataSet().getTable("user");
        User user = userService.findUserByEmail("firstUser@gmail.com");
        assertEquals("User after findUserByEmail must coincide with role in data base",
                user.getLogin(), iTable
                        .getValue(0, "login"));
        assertEquals("User after findUserByEmail must coincide with role in data base",
                user.getEmail(), iTable.getValue(0, "email"));
    }

    @Test
    public void findUserById() throws Exception {
        ITable iTable = iDatabaseTester.getDataSet().getTable("user");
        User user = userService.findUserById(1L);
        assertEquals("User after findUserById must coincide with role in data base",
                user.getLogin(), iTable.getValue(0, "login"));
        assertEquals("User after findUserById must coincide with role in data base",
                user.getEmail(), iTable.getValue(0, "email"));
    }

    @Test
    public void deleteUserById() throws Exception {
        userService.deleteUserById(1L);
        ITable iTable = iDatabaseTester.getConnection().createDataSet().getTable("user");
        assertEquals("After call method deleteUserById size must be decrease by one",
                2, iTable.getRowCount());
        assertEquals("Now first user in base must have login = secondUser", "secondUser", iTable.getValue(0, "login"));
    }

    @Test
    public void testSave() throws Exception {
        User user = User.builder()
                .login("fourth")
                .password("fourth")
                .email("fourth@gmail.com")
                .firstName("fourth")
                .lastName("fourth")
                .birthday(new Date(System.currentTimeMillis()))
                .role(new Role(1L, "USER"))
                .build();
        userService.save(user);
        ITable iTable = iDatabaseTester.getConnection().createDataSet().getTable("user");
        assertEquals("Size data base must be increase by 1",
                4, iTable.getRowCount());
        assertEquals("This user must be coincide with user in base",
                user.getLogin(), iTable.getValue(3, "login"));
        assertEquals("This user must be coincide with user in base",
                user.getEmail(), iTable.getValue(3, "email"));
    }

    @Test
    public void testDelete() throws Exception {
        User someUser = User.builder()
                .id(1L)
                .login("firstUser")
                .password("123")
                .email("firstUser@gmail.com")
                .firstName("first")
                .lastName("user")
                .birthday(Date.valueOf("1999-4-6"))
                .role(new Role(1L, "USER"))
                .build();
        userService.delete(someUser);
        assertEquals("Size data base must be decrease by 1",
                2, iDatabaseTester.getConnection().createDataSet()
                        .getTable("user").getRowCount());
    }

    @Test
    public void testUpdate() throws Exception {
        User user = User.builder()
                .id(2L)
                .login("secondUser")
                .password("123")
                .email("secondUser@gmail.com")
                .firstName("second")
                .lastName("user")
                .birthday(Date.valueOf("2000-1-28"))
                .role(new Role(2L, "ADMIN"))
                .build();
        user.setLogin("modifiedLogin");
        userService.save(user);
        ITable iTable = iDatabaseTester.getConnection().createDataSet().getTable("user");
        assertEquals("This User.login must be coincide with User.login in base",
                user.getLogin(), iTable.getValue(1, "login"));
        assertEquals("Size data base mustn't change",
                3, iTable.getRowCount());
    }

}
