package com.wedoqa.self.api.tester;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONException;

import com.wedoqa.self.api.tester.strategies.IJsonTesterStrategy;

/*
 * Copyright 2013 WeDoQA
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

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
