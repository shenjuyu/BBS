package com.yc.commons;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;


public class MyPool {
    private final int init_count = 3; //初始化链接数目
    private final int max_count = 6; //最大
    private int current_count = 0; //到当前连接数
    //连接池，用来存放初始化链接
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    //构造函数，初始化链接放入连接池
    public MyPool() {
        for (int i=0;i<init_count;i++){
            //记录当前连接数
            current_count++;
            Connection connection = createConnection();
            pool.addLast(connection);
        }
    }

    //创建新的连接
    public Connection createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "a");
            return connection;
        }catch (Exception e){
            System.out.println("数据库链接异常");
            throw new RuntimeException();
        }
    }

    //获取链接
    public Connection getConnection() {
        if (pool.size() > 0){
            //removeFirst删除第一个并且返回
            return pool.removeFirst();
        }
        if (current_count < max_count){
            //记录当前使用的连接数
            current_count++;
            //创建链接
            return createConnection();
        }
        throw new RuntimeException("当前链接已经达到最大连接数");
    }

    //释放链接
    public void releaseConnection(Connection connection){
        if (pool.size() < init_count){
            pool.addLast(connection);
            current_count--;
        }else {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
