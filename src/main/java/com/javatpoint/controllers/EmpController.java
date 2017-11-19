package com.javatpoint.controllers;

import java.util.ArrayList;  
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;  
import com.javatpoint.beans.Emp;  
import com.javatpoint.dao.EmpDao;  

@Controller  
public class EmpController {  
	
	/*Tady je jeden zajímavý moment. Nad třídou EmpDao.java není!! anotace @Component
	  injektuju pomocí beany <bean id="dao" class="com.javatpoint.dao.EmpDao">  ve speing-servlet.xml 
	  =>z toho vyplývá, že můžu injektova buď pomocí @Autowired a @Component nebo pomocí 
	  @Autowired a beany v spring-servlet.xml
	  */
	
    @Autowired  
    EmpDao dao;//will inject dao from xml file  
      
    /*It displays a form to input data, here "command" is a reserved request attribute 
     *which is used to display object data into form 
     */  
    @RequestMapping("/empform")  
    public ModelAndView showform(){  
        return new ModelAndView("empform","command",new Emp());  
    }  
    
    //Funguje - hodnoty z formulare ukazovane ve viewemp.jsp ukladaji do DB
    
    /*Musel jsem oproti vychozimu projektu:
     * 1) pridat dependency oracle.jdbc do pom.xml
     * 2)do spring-servlet.xml pridat jmeno (SYSTEM) a heslo (optimista1) k DB 
     * */
    
    /*It saves object into database. The @ModelAttribute puts request data 
     *  into model object. You need to mention RequestMethod.POST method  
     *  because default request is GET*/  
    @RequestMapping(value="/save",method = RequestMethod.POST)  
    public ModelAndView save(@ModelAttribute("emp") Emp emp){  
        dao.save(emp);  
        return new ModelAndView("redirect:/viewemp");//will redirect to viewemp request mapping  
    }  
    
    //Ukazuje zadane udaje z formulare ve viewemp.jsp
    
    /* It provides list of employees in model object */  
    @RequestMapping("/viewemp")  
    public ModelAndView viewemp(){  
        List<Emp> list=dao.getEmployees();  
        return new ModelAndView("viewemp","list",list);  
    }  
    
    /*Funguje - zobrazuje  editaci  formulare*/
    
    /* It displays object data into form for the given id.  
     * The @PathVariable puts URL data into variable.*/  
    @RequestMapping(value="/editemp/{id}")  
    public ModelAndView edit(@PathVariable int id){  
        Emp emp=dao.getEmpById(id);  
        return new ModelAndView("empeditform","command",emp);  
    } 
    
    /*Funguje - updatuje objekty a pak se vraci na zobrazovaci viewemp.jsp
     Aby to fungovalo, musel jsem do editaovaciho formulare (empeditform.jsp) pridat /SpringConnecToDB_/
     To samé se musí změnit v pom.xml
      <artifactId>SpringConnecToDB_</artifactId>
      <name>SpringConnecToDB_ Maven Webapp</name>
      <finalName>SpringConnecToDB_</finalName>
     */
    
    /* It updates model object. */  
    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
    public ModelAndView editsave(@ModelAttribute("emp") Emp emp){  
        dao.update(emp);  
        return new ModelAndView("redirect:/viewemp");  
    }  
    //Funguje - vyma�e z�znam z datab�ze a pak se vr�t� na �ablonu, kde jsou
    //zobrazeny objekty (viewemp.jsp) ...return new ModelAndView("redirect:/viewemp");
    
    /* It deletes record for the given id in URL and redirects to /viewemp */  
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)  
    public ModelAndView delete(@PathVariable int id){  
        dao.delete(id);  
        return new ModelAndView("redirect:/viewemp");  
    }  
  
}  
