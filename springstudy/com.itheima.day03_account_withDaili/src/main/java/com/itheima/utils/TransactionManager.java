package com.itheima.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 和事务管理相关的工具类,它包含了,开启事务,回滚事务和释放连接
 */
@Component("transactionManager")
public class TransactionManager {

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Autowired
    private  ConnectionUtils connectionUtils;



    /**
    * 开启事务
   */
public void beginTransaction(){

    try{
        connectionUtils.getThreadConnection().setAutoCommit(false);

    }catch (Exception e){

   throw  new RuntimeException();

    }


}

    /**
     * 提交事务
     */
    public  void commit(){
        try{
            connectionUtils.getThreadConnection().commit();

        }catch (Exception e){

            throw  new RuntimeException();

        }


      }

    /**
     * 事务的回滚
     */
    public  void rollback(){
        try{
            connectionUtils.getThreadConnection().rollback();

        }catch (Exception e){

            throw  new RuntimeException();

        }


    }

    /**
     * 资源的释放
     */
  public void release(){

      try{
          connectionUtils.getThreadConnection().close();
          connectionUtils.removeConnection();


      }catch (Exception e){

          e.printStackTrace();

      }

  }







}


