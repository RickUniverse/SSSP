package com.sssp.handler;

import com.sssp.entities.Employee;
import com.sssp.service.DepartmentService;
import com.sssp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lijichen
 * @date 2020/12/3 - 16:39
 */
@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        employeeService.deleteById(id);
        return "redirect:/emps";
    }



    @ModelAttribute//执行控制器方法之前调用
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id,
                              Map<String,Object> map) {
        if (id != null) {
            Employee employee = employeeService.getById(id);
            /**
             * 为了修改，指向最新的id，解决jpa持久化类的id不能修改的问题
             */
            employee.setDepartment(null);
            map.put("employee",employee);
        }
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.PUT)
    public String update(@ModelAttribute("employee") Employee employee) {
        employeeService.saveOrUpdateEmployee(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id,
                        Map<String,Object> map) {
        map.put("emp",employeeService.getById(id));
        map.put("deps",departmentService.getAll());
        return "employee/input";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee) {
        employeeService.saveOrUpdateEmployee(employee);
        return "redirect:/emps";
    }

    @ResponseBody
    @RequestMapping(value = "/validateName", method = RequestMethod.POST)
    public String validateName(@RequestParam("name") String name) {
        if (employeeService.getByName(name) == null){
            return "0";//Can use this name
        } else {
            return "1";//Can'nt use
        }
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Map<String,Object> map) {
        map.put("deps",departmentService.getAll());
        map.put("emp",new Employee());
        return "employee/input";
    }

    @RequestMapping("/emps")
    public String emps(@RequestParam(name = "pageNo", required = false, defaultValue = "1") String pageNoStr,
                       Map<String,Object> map) {
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoStr);
            if (pageNo < 1) {
                pageNo = 1;
            }
            //....
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("page",employeeService.getPage(pageNo,5));
        return "employee/list";
    }
}
