package com.ua.sutty.hibernate.repository.impl;

import com.ua.sutty.spring.app.Application;
import com.ua.sutty.spring.domain.Role;
import com.ua.sutty.spring.service.RoleService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RoleServiceTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RoleService roleService;

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
    public void testCreateNull() {
        roleService.save(null);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateNull() {
        roleService.save(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        roleService.delete(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByNameNull() {
        roleService.findRoleByName(null);
    }

    @Test
    public void testFindByName() throws Exception {
        assertEquals("Role after findByName must coincide with role in data base",
                roleService.findRoleByName("USER").getName(), iDatabaseTester.getDataSet()
                        .getTable("role").getValue(0, "name"));
    }

    @Test
    public void testFindById() throws Exception {
        assertEquals("Role after findById must coincide with role in data base",
                roleService.findRoleById(1L).getName(), iDatabaseTester.getDataSet()
                        .getTable("role").getValue(0, "name"));
    }

    @Test
    public void testSave() throws Exception {
        Role role = new Role("MARKETER");
        roleService.save(role);
        ITable iTable = iDatabaseTester.getConnection().createDataSet().getTable("role");
        assertEquals("Size database must be increase by one after save",
                4, iTable.getRowCount());
        assertEquals("Role must be in base and coincide with this role",
                role.getName(), iTable.getValue(3, "name"));
    }

    @Test
    public void testUpdate() throws Exception {
        Role role = new Role(2L, "ADMIN");
        role.setName("ADMIN-ADMIN");
        roleService.save(role);
        assertEquals("This Role.name must be coincide with Role.name in base"
                , role.getName(), iDatabaseTester.getConnection().createDataSet()
                        .getTable("role").getValue(1, "name"));
        assertEquals("Size database shouldn't change", 3,
                iDatabaseTester.getDataSet().getTable("role").getRowCount());
    }

}
