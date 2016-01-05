package com.contribution.bootapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// JPA实体类的标识
@Entity
public class Account {

	// JPA 主键标识, 策略为由数据库生成主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Email
    private String email;

    @Min(value = 13000000000l, message = "请输入正确的手机号码。")
    @Max(value = 19999999999l, message = "请输入正确的手机号码。")
    private String mobile;

    private String name;

    @Transient
    @NotEmpty(message = "请输入6-18位密码")
    @Size(min = 6, max = 18, message = "请输入6-18位密码。")
    private String password;

    private String hashPassword;

	public Account() {
	}

	public Account(Long id) {
		this.id = id;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
