package org.chan.service;

import java.util.List;
import java.util.Map;

import org.chan.dao.EmployeeDao;
import org.chan.dao.EmployeeDaoImpl;
import org.chan.vo.EmployeeVO;

public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();
	
	@Override
	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
	@Override
	public List<EmployeeVO> getDepatrmentId(String department_id) {
		return dao.getDepatrmentId(department_id);
	}
	@Override
	public List<EmployeeVO> getDynamic(Map<String, String> map) {
		return dao.getDynamic(map);
	}
}
