package org.chan.service;

import java.util.List;

import org.chan.dao.EmployeeDao;
import org.chan.dao.EmployeeDaoImpl;
import org.chan.vo.EmployeeVO;

public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();
	
	@Override
	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
}
