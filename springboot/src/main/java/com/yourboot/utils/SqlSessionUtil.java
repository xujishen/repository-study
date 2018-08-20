package com.yourboot.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Mybatis 直接操作数据库工具类
 * @author 	  Su Jishen
 * @DateTime 2015年9月25日 上午10:13:05
 */
public final class SqlSessionUtil {
	
	public static SqlSessionFactory sqlSessionFactory;

	/*static {
		String resource = "mybatis-config-session.xml";
        InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "main");
	}*/
	
	protected static final SqlSessionFactory buildSqlSessionFactory(String dataSource) {
		String resource = "mybatis-config-session.xml";
        InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, dataSource);
        return sqlSessionFactory;
	}
	
	/**
	 * 获取对象
	 * @author 	  Su Jishen
	 * @DateTime 2015年10月10日 下午4:07:05
	 */
	public static Object getOneObject(String dataSource, String sqlId, Object param) throws Exception {
		sqlSessionFactory = buildSqlSessionFactory(dataSource);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
		//sqlSessionManager.startManagedSession();
		try {
			return sqlSession.selectOne(sqlId, param);
		} 
		finally {
            sqlSession.close();
        }
	}
	
	/**
	 * 获取列表
	 * @author 	  Su Jishen
	 * @DateTime 2015年10月10日 下午4:07:10
	 */
	@SuppressWarnings("rawtypes")
	public static List getList(String dataSource, String sqlId, Object param) {
		sqlSessionFactory = buildSqlSessionFactory(dataSource);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.selectList(sqlId, param);
			return sqlSession.selectList(sqlId, param);
		} finally {
	        sqlSession.close();
	    }
	}
	
	/**
	 * 插入
	 * @author 	  Su Jishen
	 * @DateTime 2015年10月10日 下午4:07:16
	 */
	public static int insert(String dataSource, String sqlId, Object param) {
		sqlSessionFactory = buildSqlSessionFactory(dataSource);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.insert(sqlId, param);
		} finally {
			sqlSession.commit(true);
			sqlSession.close();
		}
	}
	
	/**
	 * 更新
	 * @author 	  Su Jishen
	 * @DateTime 2015年10月10日 下午4:07:22
	 */
	public static int update(String dataSource, String sqlId, Object param) {
		sqlSessionFactory = buildSqlSessionFactory(dataSource);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.update(sqlId, param);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		finally {
			sqlSession.commit();
            sqlSession.close();
        }
	}
	
	/**
	 * 删除
	 * @author 	  Su Jishen
	 * @DateTime 2015年10月10日 下午4:08:07
	 */
	public static int delete(String dataSource, String sqlId, Object param) {
		sqlSessionFactory = buildSqlSessionFactory(dataSource);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.delete(sqlId, param);
		} 
		finally {
			sqlSession.commit(true);
            sqlSession.close();
        }
	}
	
}
