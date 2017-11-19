package com.javatpoint.dao;
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  

import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;  
import org.springframework.stereotype.Component;

import com.javatpoint.beans.Emp;  


public class EmpDao {  
	
//Klíčová knihovna Spring pro Db. Z ní jsou pujčeny metody queryForObject(), query()	
JdbcTemplate template;  
  
public void setTemplate(JdbcTemplate template) {  
    this.template = template;  
}  
//Funguje
/*Pridal jsem pole getId()*/
public int save(Emp p){  
    String sql="insert into Emp99(id, name, salary, designation) values("+p.getId()+",'"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";  
    return template.update(sql);  
}  
public int update(Emp p){  																											//"+p.getId()+""										
    String sql="update Emp99 set name='"+p.getName()+"', salary="+p.getSalary()+", designation='"+p.getDesignation()+"' where id="+p.getId()+"";  
    return template.update(sql);  
} 
//Funguje
public int delete(int id){  
    String sql="delete from Emp99 where id="+id+"";  
    return template.update(sql);  
}  
public Emp getEmpById(int id){  
    String sql="select * from Emp99 where id=?";  
    //Cele je to ze spring knihovny queryForObject(...new Object[]{id},new BeanPropertyRowMapper<Emp>
    //https://www.mkyong.com/spring/spring-jdbctemplate-querying-examples/
    
    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));  
}  
public List<Emp> getEmployees(){  
    return template.query("select * from Emp99",new RowMapper<Emp>(){  
        public Emp mapRow(ResultSet rs, int row) throws SQLException {  
            Emp e=new Emp();  
            e.setId(rs.getInt(1));  
            e.setName(rs.getString(2));  
            e.setSalary(rs.getFloat(3));  
            e.setDesignation(rs.getString(4));  
            return e;  
        }  
    });  
}  
}  