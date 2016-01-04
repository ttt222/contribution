package com.contribution.bootapi.functional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import com.contribution.bootapi.functional.BaseFunctionalTest;
import org.springside.modules.mapper.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AccountEndpointTest.java
 * Created on  2016-1-4 上午10:21
 * <p/>
 * 版本       修改时间              作者             修改内容
 * V1.0.0     2016-1-4    jiangjianfeng@352.com    初始版本
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Controller
public class AccountEndpointTest extends BaseFunctionalTest {

    private RestTemplate restTemplate;
    private JsonMapper jsonMapper = new JsonMapper();

    private String registerUrl;
    @Before
    public void setup() {
        // TestRestTemplate与RestTemplate, 服务端返回非200返回码时，不会抛异常.
        restTemplate = new TestRestTemplate();
        registerUrl = "http://localhost:" + "2016" + "/api/books";
    }

    @Test
    public void register(){
        //正常注册
        ResponseEntity<String> response = restTemplate.getForEntity(registerUrl + "email={email}&name={name}&password=${password}"
                , String.class, "test@test.test", "test", "test");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        //密码为空
        response = restTemplate.getForEntity(registerUrl + "email={email}&name={name}&password=${password}"
                , String.class, "test@test.test", "test", "");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
