import java.sql.*;
import java.util.*;
public class hello {
    public static void main(String[] args) throws Exception{
        boolean flag=true;
        Scanner sc=new Scanner(System.in);
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vit","root","Gupta@2003");
        while(flag) {
            System.out.println("for Registration enter number 1 or seeing your data enter number 2 or see all data enter 3 or  delete yours data enter number 4 update data enter number 5 or delete all data enter 6 or terminate enter 7");
            int a = sc.nextInt();
            if (a == 1 || a == 2 || a == 3 || a == 4 || a == 5||a==6||a==7) {
                if (a == 1) {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select MAX(st_id) from student");
                    rs.next();
                    int l = rs.getInt(1);
                    System.out.println("yours student id" + " " + (l + 1));
                    int b = l + 1;
                    System.out.println("student name");
                    String name = sc.next();
                    System.out.println("student branch");
                    String branch = sc.next();
                    PreparedStatement st1 = con.prepareStatement("insert into student (st_id,st_name,st_branch) values(?,?,?)");
                    st1.setInt(1, b);
                    st1.setString(2, name);
                    st1.setString(3, branch);
                    int as=st1.executeUpdate();
                    System.out.println("your data has been entered succesfully" );
                    st.close();
                    st1.close();
                    continue;
                }
                if (a == 2) {
                    System.out.println("see the data by id enter number 1 or by name enter number 2");
                    int see1=sc.nextInt();
                    if(see1==1){
                        System.out.println("enter the id number to get yout details");
                        int seeid = sc.nextInt();
                        PreparedStatement st2 = con.prepareStatement("select * from student where st_id = ?");
                        st2.setInt(1,seeid);
                        ResultSet rs1 = st2.executeQuery();
                        rs1.next();
                        System.out.println("id" + " " + rs1.getInt(1) + " " + "name" + " " + rs1.getString(2) + " " + "branch" + " " + rs1.getString(3) + " " + "college" + " " + rs1.getString(4));
                        st2.close();
                        rs1.close();
                        continue;
                    }
                    if(see1==2) {
                        System.out.println("enter the name to get your details");
                        String z = sc.next();
                        PreparedStatement st3 = con.prepareStatement("select * from student where st_name = ?");
                        st3.setString(1,z);
                        ResultSet rs2 = st3.executeQuery();
                        while (rs2.next()) {
                            System.out.println("id" + " " + rs2.getInt(1) + " " + "name" + " " + rs2.getString(2) + " " + "branch" + " " + rs2.getString(3) + " " + "college" + " " + rs2.getString(4));
                        }
                        st3.close();
                        rs2.close();
                        continue;
                    }else{
                        System.out.println("sorry wrong input give try again");
                        continue;
                    }
                }
                if(a==3){
                    Statement st4=con.createStatement();
                    ResultSet rs3=st4.executeQuery("select * from student");
                    while(rs3.next()){
                        System.out.println("id" + " " + rs3.getInt(1) + " " + "name" + " " + rs3.getString(2) + " " + "branch" + " " + rs3.getString(3) + " " + "college" + " " + rs3.getString(4));

                    }
                    st4.close();
                    rs3.close();
                    continue;
                }
                if (a == 4) {
                    System.out.println("enter the student id");
                    int del= sc.nextInt();
                    PreparedStatement st5 = con.prepareStatement("delete from student where st_id=?");
                    st5.setInt(1, del);
                    st5.executeUpdate();
                    System.out.println("your data has been deleted succesfully " );
                    st5.close();
                    continue;

                }
                if (a == 5) {
                    System.out.println("enter the student id to update you details");
                    int v = sc.nextInt();
                    PreparedStatement st6 = con.prepareStatement("select * from student where st_id = ?");
                    st6.setInt(1, v);
                    ResultSet rs4 = st6.executeQuery();
                    rs4.next();
                    int id1 = rs4.getInt(1);
                    String name1 = rs4.getString(2);
                    String branch1 = rs4.getString(3);
                    System.out.println(id1 + " " + name1 + " " + branch1 + " " + rs4.getString(4));
                    st6.close();
                    rs4.close();
                    System.out.println("if you want to update name enter 1 or update branch enter 2 or both enter 3");
                    int x = sc.nextInt();
                    if (x == 1 || x == 3) {
                        System.out.println("new name");
                        String uname = sc.next();
                        PreparedStatement st7 = con.prepareStatement("update student set st_name=? where st_id=?");
                        st7.setString(1, uname);
                        st7.setInt(2, v);
                        st7.executeUpdate();
                        System.out.println("your name updated succesfully" );
                        st7.close();
                    }
                    if (x == 2 || x == 3) {
                        System.out.println("new branch");
                        String ubranch = sc.next();
                        PreparedStatement st8 = con.prepareStatement("update student set st_branch=? where st_id=?");
                        st8.setString(1, ubranch);
                        st8.setInt(2, v);
                        st8.executeUpdate();
                        System.out.println("your branch updated succesfully");
                        st8.close();
                        continue;
                    }

                    else{
                        System.out.println("sorry wrong input given try again ");
                        continue;
                    }
                }
                if(a==6){
                    PreparedStatement st9 = con.prepareStatement("delete from student");
                    st9.executeUpdate();
                    System.out.println("all data deleted succesfully" );
                    st9.close();
                    continue;
                }
                if (a == 7) {
                    flag = false;
                    con.close();
                }
            }else{
                System.out.println("sorry wrong input given try again");
                continue;
            }
        }
        }
    }

