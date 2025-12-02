package org.chan.service;

public class LanguageServiceImpl implements LanguageService{

	@Override
	public String executeHangeul() {
		return "안녕";
	}

	@Override
	public String executeEnglish() {
		return "Hello";
	}
	
}
