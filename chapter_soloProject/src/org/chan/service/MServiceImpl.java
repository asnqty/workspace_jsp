package org.chan.service;

import org.chan.dao.MDao;
import org.chan.dao.MDaoImpl;

public class MServiceImpl implements MService{
	MDao mDao = MDaoImpl.getInstance();
}
