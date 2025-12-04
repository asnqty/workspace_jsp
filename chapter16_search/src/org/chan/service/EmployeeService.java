package org.chan.service;

import java.util.List;
import java.util.Map;

import org.chan.vo.EmployeeVO;

public interface EmployeeService {
	public List<EmployeeVO> getAll();
	public List<EmployeeVO> getDepatrmentId(String department_id);
	public List<EmployeeVO> getDynamic(Map<String, String> map);
}
