package com.wedoqa.self.api.tester.strategies;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IJsonTesterStrategy {

final static Logger logger = LoggerFactory.getLogger(IJsonTesterStrategy.class);
	
	public void test(Map jsonObject) throws JSONException, ParseException;
	
}
