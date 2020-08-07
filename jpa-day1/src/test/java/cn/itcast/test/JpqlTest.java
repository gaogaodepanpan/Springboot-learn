package cn.itcast.test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {
    /**
     *
     *
     * 查询全部
     *       jpql :  from  cn,itcast.domain.Customer
     *       sql : SELECT * FROM cst_customer
     *
     */

    @Test
    public void testFindAll(){
        //1.获取entituManager对象
        EntityManager em = JpaUtils.getEntityManager();

        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql = " from cn.itcast.domain.Customer";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象

        //发送查询，并封装结果集
        List list = query.getResultList();

        for (Object obj :list) {
            System.out.print(obj);
        }

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }


    /**
     * 排序查询 ： 倒序查询全部用户（根据id倒序）
     *
     *         sql: SELECT * FROM cst_customer ORDER BY cust_id DESC
     *         jpql: from Customer order by custId desc
     *
     *进行jpql查询
     *     1.创建query 查询对象
     *     2.对参数进行赋值
     *     3.查询，并得到返回结果
     *
     *
     */
    @Test
    public void testOrders(){
        //1.获取entituManager对象
        EntityManager em = JpaUtils.getEntityManager();

        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql = " from Customer order by custId desc";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象

        //发送查询，并封装结果集
        List list = query.getResultList();

        for (Object obj :list) {
            System.out.print(obj);
        }

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }


    /**
     *使用jpql查询，统计客户总人数
     *  sql: SELECT COUNT(cusr_id) FROM cst_customer
     *  jpql: select count(custId) from Customer
     *
     */
    @Test
    public void testCount(){
        //1.获取entituManager对象
        EntityManager em = JpaUtils.getEntityManager();

        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
            //i 根据jpql语句创建query查询对象


        String jpql = " select count(custId) from Customer";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象
            //ii 对参数进行赋值
            //iii, 发送查询，并封装结果
            /**
             * getResultList : 直接将查询结果封装为list集合
             */
        Object result = query.getSingleResult();

        System.out.println(result);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     *使用jpql查询，分页查询
     *  sql: SELECT * FROM cst_customer limit ？，？
     *  jpql: from Customer
     *
     */
    @Test
    public void testPaged(){
        //1.获取entituManager对象
        EntityManager em = JpaUtils.getEntityManager();

        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //i 根据jpql语句创建query查询对象


        String jpql = " from Customer  ";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象
        //ii 对参数进行赋值--分页参数
        //起始索引
        query.setFirstResult(0);
        //每页查询的条数
        query.setMaxResults(2);
        //iii, 发送查询，并封装结果
        /**
         * getResultList : 直接将查询结果封装为list集合
         */
        List list = query.getResultList();
        for (Object obj : list){
            System.out.println(obj);
        }


        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }



    /**
     *使用jpql查询，条件查询
     * 案例查询客户名称以“上海”开头的客户
     *  sql: SELECT * FROM cst_customer WHERE cust_name LIKE ?;
     *  jpql: from Customer where custName like ?
     *
     */
    @Test
    public void testCondition(){
        //1.获取entituManager对象
        EntityManager em = JpaUtils.getEntityManager();

        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //i 根据jpql语句创建query查询对象


        String jpql = " from Customer where custName like ?0 "; //注意此处jpa占位符的使用  ‘？索引值’
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象
        //ii 对参数进行赋值--占位符参数
                  //query.setParameter(占位符的索引位置（即第几个占位符从1开始），参数取值);
                  //占位符的索引的位置应该在jpql语句的？后标出并与setParameter 的第一个参数相对应
        query.setParameter(0,"sap%");

        //iii, 发送查询，并封装结果
        /**
         * getResultList : 直接将查询结果封装为list集合
         */
        List list = query.getResultList();
        for (Object obj : list){
            System.out.println(obj);
        }

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

}
