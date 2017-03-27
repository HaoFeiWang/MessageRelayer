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
