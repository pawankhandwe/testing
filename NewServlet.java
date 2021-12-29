/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

   
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
        response.setContentType("text/html;charset=UTF-8");
         response.setContentType("text/html");
         PrintWriter out=response.getWriter();
    try
      { 
     
   String myfile=request.getParameter("myfiles");
   
    
   myfile=myfile.replaceAll("\\s","");
  if(myfile!=null)
   {
       FileInputStream fis=new FileInputStream(myfile);
    File f=new File(myfile);
    int s=(int)f.length();
    
Class.forName("oracle.jdbc.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","webform","webform");
    PreparedStatement ps=con.prepareStatement("insert into files values(?)");
    
    ps.setBinaryStream(1, fis, s);
    
     int x=ps.executeUpdate();
    if(x==1)
              out.print("file saved");
       else
          out.println("not saved"); 
               
   }else
    out.print(" null pointer ex");
            
   
      }catch(NullPointerException e)
    {
        JOptionPane.showMessageDialog(null,"null pointer error","error",JOptionPane.ERROR_MESSAGE);
     e.printStackTrace();
        System.out.println(""+e);
        System.exit(1);
    }
      catch(FileNotFoundException e)
    {
        JOptionPane.showMessageDialog(null,"file not found error","error",JOptionPane.ERROR_MESSAGE);
     e.printStackTrace();
     out.print(""+e);
     System.exit(1);
    }
     catch(NumberFormatException e)
    {
        JOptionPane.showMessageDialog(null,"error in format","error",JOptionPane.ERROR_MESSAGE);
     e.printStackTrace();
     System.exit(1);
    }
      catch(ClassNotFoundException e)
    {
        JOptionPane.showMessageDialog(null,"error in loading driver","error",JOptionPane.ERROR_MESSAGE);
     e.printStackTrace();
     System.exit(1);
    }catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null," error in opening connection","error",JOptionPane.ERROR_MESSAGE);
     ex.printStackTrace();
        System.out.println(""+ex);
     System.exit(1);
    }catch(IOException ex)
    {
        JOptionPane.showMessageDialog(null,"io exception","error",JOptionPane.ERROR_MESSAGE);
     ex.printStackTrace();
        out.print(""+ex);
     
    }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
