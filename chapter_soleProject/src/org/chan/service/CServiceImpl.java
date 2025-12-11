package org.chan.service;

import org.chan.dao.CDao;
import org.chan.dao.CDaoImpl;

public class CServiceImpl implements CService{
	CDao cDao = CDaoImpl.getInstance();
}
