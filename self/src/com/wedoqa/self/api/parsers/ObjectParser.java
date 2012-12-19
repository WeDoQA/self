package com.wedoqa.self.api.parsers;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

import com.wedoqa.self.api.parsers.strategies.IJsonObjectParsingStrategy;

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
