package com.contribution.bootapi.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AccountDto {
	public Long id;
    public String mobile;
	public String email;
	public String name;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
