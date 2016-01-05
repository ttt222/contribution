define({ "api": [
  {
    "type": "POST",
    "url": "/api/accounts/login",
    "title": "",
    "group": "Account",
    "name": "login",
    "version": "1.0.0",
    "description": "<p>用户登陆</p>",
    "permission": [
      {
        "name": "anyone"
      }
    ],
    "sampleRequest": [
      {
        "url": "http://192.168.9.138:2016/api/accounts/login"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>登陆密码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request Example",
          "content": "POST /api/accounts/login\n{\n  \"mobile\": \"11111111111\",\n  \"password\": \"abcde888\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Response 200": [
          {
            "group": "Response 200",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户身份token</p>"
          },
          {
            "group": "Response 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Response 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>提示信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "登陆成功 Example",
          "content": "{\n  \"token\": \"40abbbfd58ae4311a3256f1a7d036744\",\n}",
          "type": "json"
        },
        {
          "title": "参数错误 Example",
          "content": "{\n  \"code\": 400,\n  \"message\": \"User or password empty\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "java/com/contribution/bootapi/api/AccountEndPoint.java",
    "groupTitle": "Account"
  },
  {
    "type": "POST",
    "url": "/api/accounts/logout",
    "title": "",
    "group": "Account",
    "name": "logout",
    "version": "1.0.0",
    "description": "<p>用户登出</p>",
    "permission": [
      {
        "name": "user"
      }
    ],
    "sampleRequest": [
      {
        "url": "http://192.168.9.138:2016/api/accounts/logout"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户身份token</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request Example",
          "content": "POST /api/accounts/logout\n{\n  \"token\": \"40abbbfd58ae4311a3256f1a7d036744\",\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Response 200": [
          {
            "group": "Response 200",
            "type": "boolean",
            "optional": false,
            "field": "success",
            "description": "<p>是否成功:true-成功</p>"
          },
          {
            "group": "Response 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>提示信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "登出成功 Example",
          "content": "{\n  \"message\": \"登出成功\",\n  \"success\": true\n}",
          "type": "json"
        },
        {
          "title": "登出失败 Example",
          "content": "{\n  \"code\": \"400\",\n  \"message\": \"alreay logout\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "java/com/contribution/bootapi/api/AccountEndPoint.java",
    "groupTitle": "Account"
  },
  {
    "type": "POST",
    "url": "/api/accounts/register",
    "title": "",
    "group": "Account",
    "name": "register",
    "version": "1.0.0",
    "description": "<p>注册新用户</p>",
    "permission": [
      {
        "name": "anyone"
      }
    ],
    "sampleRequest": [
      {
        "url": "http://192.168.9.138:2016/api/accounts/register"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "email",
            "description": "<p>注册邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "name",
            "description": "<p>用户名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>登陆密码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request Example",
          "content": "POST /api/accounts/register\n{\n  \"mobile\": \"13333333333\"\n  \"email\": \"test@test.test\",\n  \"name\": \"test\",\n  \"password\": \"test\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Response 200": [
          {
            "group": "Response 200",
            "type": "boolean",
            "optional": false,
            "field": "success",
            "description": "<p>是否成功:true-成功</p>"
          },
          {
            "group": "Response 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>提示信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "注册成功 Example",
          "content": "{\n  \"message\": \"xxx\",\n  \"success\": true\n}",
          "type": "json"
        },
        {
          "title": "参数错误 Example",
          "content": "{\n  \"code\": \"400\",\n  \"password\": \"请输入6-18位密码。\",\n  \"mobile\": \"请输入正确的手机号码。\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "java/com/contribution/bootapi/api/AccountEndPoint.java",
    "groupTitle": "Account"
  }
] });
