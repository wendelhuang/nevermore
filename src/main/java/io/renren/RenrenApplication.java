/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;


@SpringBootApplication
public class RenrenApplication {

	public static void main(String[] args) {
		
		String proxyHost = "127.0.0.1";
    	// 1080是shaodowscoks的默认端口
    	String proxyPort = "11223";

    	System.setProperty("http.proxyHost", proxyHost);
    	System.setProperty("http.proxyPort", proxyPort);

    	// 对https也开启代理
    	System.setProperty("https.proxyHost", proxyHost);
    	System.setProperty("https.proxyPort", proxyPort);
		
		SpringApplication.run(RenrenApplication.class, args);
	}

}