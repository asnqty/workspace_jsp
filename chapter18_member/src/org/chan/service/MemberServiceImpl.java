package org.chan.service;

import org.chan.dao.MemberDao;
import org.chan.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService{
	private MemberDao memberDao = MemberDaoImpl.getInstance();
}
