<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
        <h1>Edit Employee</h1>  
        <!-- Musí tam být název projektu  /MVC_CRUDapp/!!
        ...jinak editace objektu nefungeje-->
        
       <form:form method="POST" action="/MVC_CRUDapp/editsave">    
        <table >    
         <!--
        <tr>  
        <td></td>    
         <td><form:hidden  path="id" /></td>  
         </tr> 
         -->  
         <!--  jsem jedno pole navic -->
         <tr>    
          <td>Id : </td>   
          <td><form:input path="id"  /></td>  
         </tr> 
         
         <tr>    
          <td>Name : </td>   
          <td><form:input path="name"  /></td>  
         </tr>    
         <tr>    
          <td>Salary :</td>    
          <td><form:input path="salary" /></td>  
         </tr>   
         <tr>    
          <td>Designation :</td>    
          <td><form:input path="designation" /></td>  
         </tr>   
           
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Edit Save" /></td>    
         </tr>    
        </table>    
       </form:form>    </html>