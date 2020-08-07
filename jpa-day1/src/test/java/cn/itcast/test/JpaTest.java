package cn.itcast.test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     *     Jpa的操作步骤
     *          1.加载配置文件创建工厂（实体管理器工厂）对象
     *              Persistence:静态方法（根据持久化单元名称创建实体管理器工厂）
     *                   createEntityManagerFactory(持久化单元名："myJpa");
     *          2.通过实体管理器工厂获取实体管理器
     *              EntityManagerFactory：获取EntityManager对象
     *              方法：createEntityManager()
     *              *内部维护了很多内容
     *                  *内部维护了数据库信息
     *                  *维护了缓存信息
     *                  *维护了所有的实体管理器对象
     *                  *再创建EntityManagerFactory的过程中会根据配置创建数据库表
     *              *EntityManagerFactory的创建过程比较浪费资源
     *              特点：线程安全的对象
     *                   多个线程访问同一个EntityManagerFactory不会有线程安全问题
     *              *如何解决EntityManagerFactory的创建过程浪费资源的问题？
     *              思路：创建一个公共的EntityManagerFactory的对象
     *              *静态代码块的形式创建EntityManagerFactory
     *          3.获取事务对象，开启事务
     *              EntityManager对象：实体类管理器 ，真正的和数据库交互的对象
     *                  方法：getTransaction :获取/创建事物对象
     *                       persist ：保存
     *                       merge ： 更新
     *                       remove ： 删除
     *                       find/ getReference : 根据ID查询
     *               由getTransaction生成的Transaction 对象 ： 事务
     *                   方法： begin ：开启事务
     *                         commit ： 提交事务
     *                         rollback：回滚
     *          4.完成增删改查操作
     *          5.提交事务（回滚事务）
     *          6.释放资源
     */
    @Test
    public void testSave(){
//        //        1.加载配置文件创建工厂（实体管理器工厂）对象
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
//        //        2.通过实体管理器工厂获取实体管理器
//
//        EntityManager em = factory.createEntityManager();
        EntityManager em = JpaUtils.getEntityManager();


        //        3.获取事务对象，
        EntityTransaction tx = em.getTransaction();
        //        开启事务
        tx.begin();
        //        4.完成增删改查操作:保存一个客户到数据库中；
        Customer customer = new Customer();
        customer.setCustName("sap");
        customer.setCustIndustry("IT");
        em.persist(customer);//保存操作
//        5.提交事务

        tx.commit();
//        6.释放资源
        em.close(); //关闭管理器
        //  factory.close(); //关闭工厂
    }


    /**
     * 根据id查询客户
     *
     */
    @Test
    public void testFind(){
        //1.通过工具类获取entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.增删改查--根据id查询客户
        /**
         * find:根据id查询数据
         *          class：查询数据结果需要包装的实体类类型的字节码
         *          id:查询的主键的取值
         *       1.查询对象就是当前客户对象本身
         *       2.在调用find方法的时候，就会发送sql语句查询数据库
         *       立即加载
         */
        Customer customer = em.find(Customer.class,1l); //1l 是将1转成long类型
        System.out.print(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }


    @Test
    public void testgetReference(){
        //1.通过工具类获取entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.增删改查--根据id查询客户
        /**
         * getReference:根据id查询数据
         *          class：查询数据结果需要包装的实体类类型的字节码
         *          id:查询的主键的取值
         *          1.获取的对象是一个动态代理对象
         *          2.调用getReference方法不会立即发送sql语句查询数据库
         *              *当调用查询结果对象的时候，才会发送查询sql,什么时候用，什么时候发送sql语句查询数据库
         *
         *              延迟加载，也叫懒加载
         *               *得到一个动态代理对象
         *               *什么时候用什么时候才会加载
         */
        Customer customer = em.getReference(Customer.class,1l); //1l 是将1转成long类型
        System.out.print(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     *
     * 删除客户的案例
     *
     *
     *
     */
    @Test
    public void testRemove(){
        //1.通过工具类获取entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.增删改查--删除客户
        //i 根据id查询客户
        Customer customer = em.find(Customer.class, 1l);

        //ii 调用remove方法完成删除操作
        em.remove(customer);

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 更新操作
     *   merge(object)
     *
     *
     *
     */

    @Test
    public void testUpdate(){
        //1.通过工具类获取entityManager
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.增删改查--更新操作
        //i 查询客户
        Customer customer = em.find(Customer.class, 2l);
        //ii 更新客户
        customer.setCustIndustry("ERP");
        em.merge(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }
}
