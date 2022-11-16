package com.movielog.domain;

import lombok.Data;

@Data
public class LoginVO {

	private String userid;
	private String userpw;
	private boolean useCookie;

    }