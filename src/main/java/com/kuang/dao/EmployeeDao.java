package com.kuang.dao;

import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// dao层让类被spring托管的注解
@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

    // 员工有所属的部门
    @Autowired
    @Qualifier(value = "departmentDao")
    private DepartmentDao departmentDao;
    // 模拟数据库中的数据
    static {

        employees.put(1001,new Employee(1001,"A","2020586213@qq.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"B","2020586213@qq.com",1,new Department(102,"市场部")));
        employees.put(1003,new Employee(1003,"C","2020586213@qq.com",1,new Department(103,"教研部")));
        employees.put(1004,new Employee(1004,"D","2020586213@qq.com",1,new Department(104,"运营部")));
        employees.put(1005,new Employee(1005,"E","2020586213@qq.com",1,new Department(105,"后勤部")));
    }

    // 主键自增
    private static Integer initId = 1006;
    // 增加一个员工
    public void add(Employee employee){
        employee.setId(initId);
        int DID = employee.getDepartment().getId();
        DepartmentDao departmentDao = new DepartmentDao();
        Collection<Department> departments = departmentDao.getDepartments();
        for (Department department : departments) {
            if (DID == department.getId()){
                employee.setDepartment(department);
            }
        }
        employees.put(initId++,employee);
    }

    // 查询全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }
}
