package com.github.ssaunder.logging;

import com.github.ssaunder.logging.annotation.LoggingModule;
import com.github.ssaunder.logging.annotation.LoggingOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@LoggingModule("模块")
@RestController
public class TestController {

	@LoggingOperation("操作")
	@GetMapping("/")
	public String test(@RequestParam String a,
					   @RequestParam String b) throws Exception {
//		return GeoIPLite2Utils.resolverIp("36.106.97.90");
		throw new Exception("111");
	}

}
