package com.wedoqa.self.api.parsers.strategies;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IJsonObjectParsingStrategy<E> {

	final static Logger logger = LoggerFactory.getLogger(IJsonObjectParsingStrategy.class);
	
	public E parseJsonObject(JSONObject jso) throws JSONException, ParseException;
}
