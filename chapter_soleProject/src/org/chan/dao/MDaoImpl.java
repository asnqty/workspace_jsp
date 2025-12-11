package org.chan.dao;

import org.apache.ibatis.session.SqlSession;
import org.chan.mybatis.config.DBService;

public class MDaoImpl implements MDao{
	private static MDaoImpl instance = null;
	private MDaoImpl() {}
	public static MDaoImpl getInstance() {
		if(instance == null) {
			instance = new MDaoImpl();
		}
		return instance;
	}
	
	private static SqlSession sqlsession = null;
	private synchronized static SqlSession getSqlSession() {
		if(sqlsession == null) {
			sqlsession = DBService.getFactory().openSession(false);
		}
		return sqlsession;
	}
}
