package com.wedoqa.self.api.parsers;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

import com.wedoqa.self.api.parsers.strategies.IJsonObjectParsingStrategy;


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

public class ObjectParser<E> {

	private IJsonObjectParsingStrategy<E> strategy;

	public ObjectParser(IJsonObjectParsingStrategy<E> strategy) {
		super();
		this.strategy = strategy;
	}
	
	public E parse(JSONObject jso) throws JSONException, ParseException{
		return strategy.parseJsonObject(jso);
	}
	
}
