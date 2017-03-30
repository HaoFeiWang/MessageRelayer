# 短信转发工具
## 截图展示
![首页](https://github.com/HaoFeiWang/MessageRelayer/blob/master/screen/screen_main.jpg)
![转发至短信](https://github.com/HaoFeiWang/MessageRelayer/blob/master/screen/screen_email.jpg)
![转发至邮件](https://github.com/HaoFeiWang/MessageRelayer/blob/master/screen/screen_sms.jpg)

## 功能概述
### 自动转发短信至目标手机号
只需开启转发转发至短信，并且设置目标手机号就可实现自动转发。
### 自动转发短信至目标邮箱
1. 开启转发至邮箱。
2. 设置SMTP服务器（QQ、163、126、Gmail、Outlook、自定义）。如果选择自定义SMTP服务器，则需要设置主机名和端口号。
推荐开启SSL方式，不开启可能会有未知错误，例如QQ邮箱。
3. 设置SMTP邮箱和密码（发送方账号，必须属于SMTP服务器的账号）。需要开启邮箱的SMTP服务，并且密码为其对应的授权码，详细操作参见各邮箱网站的帮助页面。
4. 设置目标邮箱账号（接收方账号，任意服务器均可）。
5. 设置发送方的名称（默认为：短信助手）。
6. 设置邮件主题（默认为：短信转发）。

## 注意
- Android 5.0以上应授权应用短信读取、短信发送权限。
- 在部分深度定制的机型上手动杀死后无法复活（小米、魅族）。

## 版本1.1
![主界面](https://github.com/HaoFeiWang/MessageRelayer/blob/develop/screen/screen_main2.jpg)
![规则定制](https://github.com/HaoFeiWang/MessageRelayer/blob/develop/screen/screen_rule.jpg)
![选中的联系人](https://github.com/HaoFeiWang/MessageRelayer/blob/develop/screen/screen_select.jpg)

![关键字](https://github.com/HaoFeiWang/MessageRelayer/blob/develop/screen/screen_key.jpg)
![联系人](https://github.com/HaoFeiWang/MessageRelayer/blob/develop/screen/screen_list.jpg)
![关于](https://github.com/HaoFeiWang/MessageRelayer/blob/develop/screen/screen_about.jpg)

### 新版功能
- 总闸开关（在主页上面，关闭后不进行任何转发）
- 规则定制
 - 根据指定联系人转发（如果有指定联系人，当收到短信时，属于指定的联系才进行转发；如果没有指定，则转发所有）
 - 根据包含关键字转发（如果有指定关键字，当收到短信时，短信内容包含任一关键字才进行转发；如果没有指定，则转发所有）
- 内容定制
 - 添加内容前缀（转发短信时，在短信的前面加上该前缀）
 - 添加内容后缀（转发短信时，在短信的后面加上该后缀）
如果觉得该项目对您有用，请随意打赏。您的支持将鼓励我继续创作！

![捐赠](https://github.com/HaoFeiWang/MessageRelayer/blob/master/screen/contribution.jpg)
