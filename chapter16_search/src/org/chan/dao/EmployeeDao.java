package org.chan.dao;

import java.util.List;
import java.util.Map;

import org.chan.vo.EmployeeVO;

public interface EmployeeDao {
	public List<EmployeeVO> getAll();
	public List<EmployeeVO> getDepatrmentId(String department_id);
	public List<EmployeeVO> getDynamic(Map<String, String> map);
}
