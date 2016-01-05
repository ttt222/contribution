package com.contribution.bootapi.api;

import com.contribution.bootapi.beanvalidator.BeanValidators;
import com.contribution.bootapi.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.contribution.bootapi.service.AccountService;
import com.contribution.bootapi.service.exception.ErrorCode;
import com.contribution.bootapi.service.exception.ServiceException;
import org.springside.modules.constants.MediaTypes;

import javax.validation.Validator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @apiDefine CODE_500
 * @apiSuccess (Response 500) {number} code 500
 * @apiSuccess (Response 500) {string} [message] error description
 * @apiSuccessExample {json} Response 500 Example
 *   HTTP/1.1 500 Internal Server Error
 *   {
 *     "code": 500
 *     "message": "xxx"
 *   }
 */

/**
 * @apiDefine CODE_400
 * @apiSuccess (Response 400) {string} [message] error description
 * @apiSuccessExample {json} Response 400 Example
 *   Error 400: Bad Request
 *   {
 *     "popXXX": "xxx"
 *   }
 */
// Spring Restful MVC Controller的标识, 直接输出内容，不调用template引擎.
@RestController
public class AccountEndPoint {

	private static Logger logger = LoggerFactory.getLogger(AccountEndPoint.class);

    @Autowired
    private Validator validator;

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
     * @apiParam {String} mobile 手机号码
     * @apiParam {String} password 登陆密码
     *
     * @apiParamExample {json} Request Example
     *   POST /api/accounts/login
     *   {
     *     "mobile": "11111111111",
     *     "password": "abcde888"
     *   }
     *
     * @apiSuccess (Response 200) {String} token 用户身份token
     * @apiSuccessExample {json} 登陆成功 Example
     *   {
     *     "token": "40abbbfd58ae4311a3256f1a7d036744",
     *   }
     *
     * @apiSuccess (Response 200) {String} code 错误代码
     * @apiSuccess (Response 200) {String} message 提示信息
     * @apiSuccessExample {json} 参数错误 Example
     *   {
     *     "code": 400,
     *     "message": "User or password empty"
     *   }
     */
	@RequestMapping(value = "/api/accounts/login", produces = MediaTypes.JSON_UTF_8, method = RequestMethod.POST)
	public Map<String, String> login(@RequestBody Account account) {
		if (StringUtils.isEmpty(account.getMobile()) || StringUtils.isEmpty(account.getPassword())) {
			throw new ServiceException("User or password empty", ErrorCode.BAD_REQUEST);
		}

		String token = accountServcie.login(account.getMobile(), account.getPassword());

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
     * @apiParam {String} token 用户身份token
     *
     * @apiParamExample {json} Request Example
     *   POST /api/accounts/logout
     *   {
     *     "token": "40abbbfd58ae4311a3256f1a7d036744",
     *   }
     *
     * @apiSuccess (Response 200) {boolean} success 是否成功:true-成功
     * @apiSuccess (Response 200) {string} message 提示信息
     * @apiSuccessExample {json} 登出成功 Example
     *   {
     *     "message": "登出成功",
     *     "success": true
     *   }
     * @apiSuccessExample {json} 登出失败 Example
     *   {
     *     "code": "400",
     *     "message": "alreay logout"
     *   }
     */
	@RequestMapping(value = "/api/accounts/logout", produces = MediaTypes.JSON_UTF_8, method = RequestMethod.POST)
	public Map<String, Object> logout(@RequestBody Map<String, String> token) {
		accountServcie.logout(token.get("token"));

        Map<String, Object> result = new HashMap();
        result.put("message", "登出成功。");
        result.put("success", true);
        return result;
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
     * @apiParam {String} mobile 手机号码
     * @apiParam {String} [email] 注册邮箱
     * @apiParam {String} [name] 用户名
     * @apiParam {String} password 登陆密码
     *
     * @apiParamExample {json} Request Example
     *   POST /api/accounts/register
     *   {
     *     "mobile": "13333333333"
     *     "email": "test@test.test",
     *     "name": "test",
     *     "password": "test"
     *   }
     *
     * @apiSuccess (Response 200) {boolean} success 是否成功:true-成功
     * @apiSuccess (Response 200) {string} message 提示信息
     * @apiSuccessExample {json} 注册成功 Example
     *   {
     *     "message": "xxx",
     *     "success": true
     *   }
     * @apiSuccessExample {json} 参数错误 Example
     *   {
     *     "code": "400",
     *     "password": "请输入6-18位密码。",
     *     "mobile": "请输入正确的手机号码。"
     *   }
     */
	@RequestMapping(value = "/api/accounts/register", produces = MediaTypes.JSON_UTF_8, method = RequestMethod.POST)
	public Map<String, Object> register(@RequestBody Account account) {
        BeanValidators.validateWithException(validator, account);
		accountServcie.register(account);

        Map<String, Object> result = new HashMap();
        result.put("message", "注册成功");
        result.put("success", true);
        return result;
	}
}
