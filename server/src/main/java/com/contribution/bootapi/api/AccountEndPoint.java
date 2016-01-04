package com.contribution.bootapi.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.contribution.bootapi.service.AccountService;
import com.contribution.bootapi.service.exception.ErrorCode;
import com.contribution.bootapi.service.exception.ServiceException;
import org.springside.modules.constants.MediaTypes;

import java.util.Collections;
import java.util.Map;

// Spring Restful MVC Controller的标识, 直接输出内容，不调用template引擎.
@RestController
public class AccountEndPoint {

	private static Logger logger = LoggerFactory.getLogger(AccountEndPoint.class);

	@Autowired
	private AccountService accountServcie;

    /**
     * @api {POST} /api/accounts/login
     * @apiGroup Account
     * @apiName login
     * @apiVersion 1.0.0
     * @apiDescription 用户登陆
     * @apiPermission anyone
     * @apiSampleRequest http://192.168.9.138:2016/api/accounts/login
     *
     * @apiParam {String} email 注册邮箱
     * @apiParam {String} password 登陆密码
     *
     * @apiParamExample {json} Request Example
     *   POST /api/accounts/login
     *   {
     *     "email": "test@test.test",
     *     "password": "test"
     *   }
     *
     * @apiSuccess (Response 200) {String} token 用户身份token
     * @apiSuccessExample {json} Response 200 Example
     *   HTTP/1.1 200 OK
     *   {
     *     "token": "40abbbfd58ae4311a3256f1a7d036744",
     *   }
     *
     * @apiUse CODE_500
     */
	@RequestMapping(value = "/api/accounts/login", produces = MediaTypes.JSON_UTF_8)
	public Map<String, String> login(@RequestParam("email") String email, @RequestParam("password") String password) {

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			throw new ServiceException("User or password empty", ErrorCode.BAD_REQUEST);
		}

		String token = accountServcie.login(email, password);

		return Collections.singletonMap("token", token);
	}

    /**
     * @api {POST} /api/accounts/logout
     * @apiGroup Account
     * @apiName logout
     * @apiVersion 1.0.0
     * @apiDescription 用户登出
     * @apiPermission user
     * @apiSampleRequest http://192.168.9.138:2016/api/accounts/logout
     *
     * @apiParam {String} [token] 用户身份token
     *
     * @apiParamExample {json} Request Example
     *   POST /api/accounts/logout
     *   {
     *     "token": "40abbbfd58ae4311a3256f1a7d036744",
     *   }
     *
     * @apiSuccess (Response 200) {String} NONE 空白响应
     */
	@RequestMapping(value = "/api/accounts/logout")
	public void logout(@RequestParam(value = "token", required = false) String token) {
		accountServcie.logout(token);
	}

    /**
     * @api {POST} /api/accounts/register
     * @apiGroup Account
     * @apiName register
     * @apiVersion 1.0.0
     * @apiDescription 注册新用户
     * @apiPermission anyone
     * @apiSampleRequest http://192.168.9.138:2016/api/accounts/register
     *
     * @apiParam {String} email 注册邮箱
     * @apiParam {String} [name] 用户名
     * @apiParam {String} password 登陆密码
     *
     * @apiParamExample {json} Request Example
     *   POST /api/accounts/register
     *   {
     *     "email": "test@test.test",
     *     "name": "test",
     *     "password": "test"
     *   }
     *
     * @apiSuccess (Response 200) {String} NONE 空白响应
     * @apiUse CODE_500
     */
	@RequestMapping(value = "/api/accounts/register")
	public void register(@RequestParam("email") String email,
			@RequestParam(value = "name", required = false) String name, @RequestParam("password") String password) {

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			throw new ServiceException("User or name or password empty", ErrorCode.BAD_REQUEST);
		}

		accountServcie.register(email, name, password);
	}
}
