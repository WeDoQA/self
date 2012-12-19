package com.wedoqa.self.api.tester;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONException;

import com.wedoqa.self.api.tester.strategies.IJsonTesterStrategy;

public class JsonTester {

	private IJsonTesterStrategy strategy;

	public JsonTester(IJsonTesterStrategy strategy) {
		super();
		this.strategy = strategy;
	}

	public void test(Map jsonObject) throws JSONException, ParseException{
		strategy.test(jsonObject);
	}
}
