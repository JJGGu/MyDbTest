/**
 * @program: MyDbTest
 * @description: Testing connection to mysql
 * @author: JJGGu
 * @create: 2020-05-19 12:50
 **/
import java.sql.*;

//1.注册JDBC驱动
//2.连接数据库
//3.实例化statement
//4.使用statement执行查询等语句并返回ResultSet
//5.迭代显示查询得到的数据
//6.关闭resultset，statement，connection
public class MyConnection {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/onlinedb?" +
            "useSSL=false&serverTimezone=UTC";

    static final String USERNAME = "root";
    static final String PWD = "xuhaoranaaa.";

    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try{
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //打开链接
            System.out.println("链接数据库");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PWD);

            //查询
            System.out.println("实例化Statement对象");
            statement = conn.createStatement();
            String sql;
            sql = "SELECT ID,NAME,URL FROM WEBSITES";
            ResultSet rs = statement.executeQuery(sql);

            //显示查询结果
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("NAME");
                String url = rs.getString("URL");

                System.out.println("id:"+id);
                System.out.println("name:" + name);
                System.out.println("URL:" + url);
                System.out.println("\n");
            }
            rs.close();
            statement.close();
            conn.close();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }catch (SQLException se2){

            }
            try {
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException se3){

            }
        }
        System.out.println("over");
    }
}
