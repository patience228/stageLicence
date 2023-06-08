package com.id2real;

public class TestUtils {
	public static final String TARGET_DIRECTORY = "target/generated-snippets";
	
	public static final String DEFAULT_USERNAME = "user";
	public static final String DEFAULT_EMAIL = "user@id2real.com";
	public static final String DEFAULT_PASSWORD = "user";
	public static final String LOGIN_URL = "/api/security/auth/login?username=" + TestUtils.DEFAULT_USERNAME
	+ "&password=" + TestUtils.DEFAULT_PASSWORD;
}
