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
            "field": "email",
            "description": "<p>注册邮箱</p>"
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
          "content": "POST /api/accounts/login\n{\n  \"email\": \"test@test.test\",\n  \"password\": \"test\"\n}",
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
          }
        ],
        "Response 500": [
          {
            "group": "Response 500",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>500</p>"
          },
          {
            "group": "Response 500",
            "type": "string",
            "optional": true,
            "field": "message",
            "description": "<p>error description</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Response 200 Example",
          "content": "HTTP/1.1 200 OK\n{\n  \"token\": \"40abbbfd58ae4311a3256f1a7d036744\",\n}",
          "type": "json"
        },
        {
          "title": "Response 500 Example",
          "content": "HTTP/1.1 500 Internal Server Error\n{\n  \"code\": 500\n  \"message\": \"xxx\"\n}",
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
            "optional": true,
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
            "type": "String",
            "optional": false,
            "field": "NONE",
            "description": "<p>空白响应</p>"
          }
        ]
      }
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
          "content": "POST /api/accounts/register\n{\n  \"email\": \"test@test.test\",\n  \"name\": \"test\",\n  \"password\": \"test\"\n}",
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
            "field": "NONE",
            "description": "<p>空白响应</p>"
          }
        ],
        "Response 500": [
          {
            "group": "Response 500",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>500</p>"
          },
          {
            "group": "Response 500",
            "type": "string",
            "optional": true,
            "field": "message",
            "description": "<p>error description</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Response 500 Example",
          "content": "HTTP/1.1 500 Internal Server Error\n{\n  \"code\": 500\n  \"message\": \"xxx\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "java/com/contribution/bootapi/api/AccountEndPoint.java",
    "groupTitle": "Account"
  }
] });
