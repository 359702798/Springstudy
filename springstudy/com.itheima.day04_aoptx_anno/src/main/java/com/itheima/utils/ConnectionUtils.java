package com.itheima.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;


@Component("connectionUtils")
public class ConnectionUtils {

    private  ThreadLocal<Connection> t1=new ThreadLocal<Connection>();

    @Autowired
    private DataSource dataSource;


public Connection getThreadConnection(){


        try{
            //1.从ThreadLocal上获取连接
            Connection conn=t1.get();
            //2.判断当前线程是否有连接
            if(conn==null){
                //3.从数据源中获取线程,并存入ThreadLocal中
                conn=dataSource.getConnection();

                t1.set(conn);
            }
            //4.返回当前线程上的连接
            return  conn;
        }
        catch (Exception e){

            throw  new RuntimeException();
        }
}



   public  void removeConnection(){


        try{

            t1.remove();


        }catch (Exception e){

            throw  new RuntimeException();
        }



     }

}
