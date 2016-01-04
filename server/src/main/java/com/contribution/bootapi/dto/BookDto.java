package com.contribution.bootapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class BookDto {

	public Long id;
	public String bookId;
	public String title;
	public String url;
	public String status;

	public AccountDto owner;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date onboardDate;

	public AccountDto borrower;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date borrowDate;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
