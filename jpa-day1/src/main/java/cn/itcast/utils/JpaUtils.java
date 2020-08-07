package cn.itcast.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决实体管理类工厂的浪费资源和耗时问题
 *    通过静态代码块的形式，当程序第一次访问此工具类时，创建一个公共实体管理器工厂对象
 *
 * 第一次访问getEntityManager 方法 ：经过静态代码块创建一个factory 对象，再调用方法创建一个EntityManager对象
 * 第二次访问getEntityManager 方法：直接通过一个已经创建好的factory对象，创建getEntityManager对象
 *
 *弹幕：静态资源是编译器执行的，也就是编译期创建了一个公共的工厂对象
 */
public class JpaUtils {
        private  static  EntityManagerFactory factory;
        static {
            //1.加载配置文件，创建entityManagerFactory
            factory = Persistence.createEntityManagerFactory("myJpa");
        }
    /**
     * 获取EntityManager对象
     *
     */
    public static EntityManager getEntityManager(){
       return factory.createEntityManager();
    }

}
