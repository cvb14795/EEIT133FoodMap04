package event;

import java.util.*;  
import java.sql.*;  

public class eventdao {
	 public static Connection getConnection(){  
	        Connection con=null;  
	        try{  
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	            con=DriverManager.getConnection("jdbc:sqlserver://group4.database.windows.net:1433;databaseName=Teamproject;user=everyone;password=Foodmap04!");  
	        }catch(Exception e){System.out.println(e);}  
	        return con;  
	    }  
	    public static int save(eventtest1 e){  
	        int status=0;  
	        try{  
	            Connection con=eventdao.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "insert into EVENTTABLE(EVENTNAME,EVENTPEOPLE,EVENTTIME,EVENTCONTENT) values (?,?,?,?)");  
	            ps.setString(1,e.getName());  
	            ps.setInt(2,e.getpeople_num());  
	            ps.setString(3,e.gettime());  
	            ps.setString(4,e.getcontent());  
	              
	            status= ps.executeUpdate(); 
	            System.out.println(status);
	                     
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return status;  
	    }  
	    public static int update(eventtest1 e){  
	        int status=0;  
	        try{  
	            Connection con=eventdao.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "update EVENTTABLE set EVENTPEOPLE=?,EVENTTIME=?,EVENTCONTENT=? where EVENTNAME=?"); 
	            ps.setInt(1,e.getpeople_num());  
	            ps.setString(2,e.gettime());  
	            ps.setString(3,e.getcontent()); 
	            ps.setString(4,e.getName());  
	              
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return status;  
	    }  
	    public static int delete(String name){  
	        int status=0;  
	        try{  
	            Connection con=eventdao.getConnection();  
	            PreparedStatement ps=con.prepareStatement("delete from EVENTTABLE where EVENTNAME=?");  
	            ps.setString(1,name);  
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception e){e.printStackTrace();}  
	          
	        return status;  
	    }  
	    public static eventtest1 getEventById(int id){  
	        eventtest1 e=new eventtest1();  
	          
	        try{  
	            Connection con=eventdao.getConnection();  
	            PreparedStatement ps=con.prepareStatement("select * from EVENTTABLE where id=?");  
	            ps.setInt(1,id);  
	            ResultSet rs=ps.executeQuery();  
	            if(rs.next()){  
	                e.setId(rs.getInt(1));  
	                e.setName(rs.getString(2));  
	                e.setcontent(rs.getString(3));  
	                e.settime(rs.getString(4));  
	                e.setpeople_num(rs.getInt(5));  
	            }  
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return e;  
	    } 
	    public static eventtest1 getEventByName(String eventName){  
	        eventtest1 e =new eventtest1();  
	          
	        try{  
	            Connection con=eventdao.getConnection();  
	            PreparedStatement ps=con.prepareStatement("select * from EVENTTABLE where EVENTNAME=?");  
	            ps.setString(1,eventName);  
	            ResultSet rs=ps.executeQuery();  
	            if(rs.next()){  
	                //e.setId(rs.getInt(1));  
	                e.setName(rs.getString(1)); 
	                e.setpeople_num(rs.getInt(2));
	                e.settime(rs.getString(3));  
	                e.setcontent(rs.getString(4));  
	            }  
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return e;  
	    }  
	    public static List<eventtest1> getAlleventtest1(){  
	        List<eventtest1> list=new ArrayList<eventtest1>();  
	          
	        try{  
	            Connection con=eventdao.getConnection();  
	            PreparedStatement ps=con.prepareStatement("select * from EVENTTABLE");  
	            ResultSet rs=ps.executeQuery();
	            int people_num = 0;
	            String name = null;
	            String content = null;
	            String time = null;
	            while(rs.next()){  
	                eventtest1 e=new eventtest1(people_num,name,time,content);  
	                //e.setId(rs.getInt(1));  
	                e.setName(rs.getString(1));  
	                e.setpeople_num(rs.getInt(2));
	                e.settime(rs.getString(3)); 
	                e.setcontent(rs.getString(4));  
	                list.add(e);  
	            }  
	            con.close();  
	        }catch(Exception e){e.printStackTrace();}  
	          
	        return list;  
	    }  

}
