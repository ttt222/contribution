package com.contribution.bootapi.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.contribution.bootapi.domain.Account;
import com.contribution.bootapi.repository.AccountDao;
import com.contribution.bootapi.service.exception.ErrorCode;
import com.contribution.bootapi.service.exception.ServiceException;
import org.springside.modules.utils.Digests;
import org.springside.modules.utils.Encodes;
import org.springside.modules.utils.Ids;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

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
// Spring Bean的标识.
@Service
public class AccountService {

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountDao accountDao;

	// 注入配置值
	@Value("${app.loginTimeoutSecs:600}")
	private int loginTimeoutSecs;

	// codehale metrics
	@Autowired
	private CounterService counterService;

	// guava cache
	private Cache<String, Account> loginUsers;

	@PostConstruct
	public void init() {
		loginUsers = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(loginTimeoutSecs, TimeUnit.SECONDS)
				.build();
	}

	@Transactional(readOnly = true)
	public String login(String email, String password) {
		Account account = accountDao.findByEmail(email);

		if (account == null) {
			throw new ServiceException("User not exist", ErrorCode.UNAUTHORIZED);
		}

		if (!account.hashPassword.equals(hashPassword(password))) {
			throw new ServiceException("Password wrong", ErrorCode.UNAUTHORIZED);
		}

		String token = Ids.uuid2();
		loginUsers.put(token, account);
		counterService.increment("loginUser");
		return token;
	}

	public void logout(String token) {
		Account account = loginUsers.getIfPresent(token);
		if (account == null) {
			logger.warn("logout an alreay logout token:" + token);
		} else {
			loginUsers.invalidate(token);
			counterService.decrement("loginUser");
		}
	}

	public Account getLoginUser(String token) {

		Account account = loginUsers.getIfPresent(token);

		if (account == null) {
			throw new ServiceException("User doesn't login", ErrorCode.UNAUTHORIZED);
		}

		return account;
	}

	@Transactional
	public void register(String email, String name, String password) {

		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
			throw new ServiceException("Invalid parameter", ErrorCode.BAD_REQUEST);
		}

		Account account = new Account();
		account.email = email;
		account.name = name;
		account.hashPassword = hashPassword(password);
		accountDao.save(account);
	}

	protected static String hashPassword(String password) {
		return Encodes.encodeBase64(Digests.sha1(password));
	}
}
