package cn.com.coding4fun.logging;

import cn.com.coding4fun.logging.annotation.LoggingModule;
import cn.com.coding4fun.logging.annotation.LoggingOperation;
import cn.com.coding4fun.logging.util.GeoIPLite2Utils;
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
		return GeoIPLite2Utils.resolverIp("36.106.97.90");
//		throw new Exception("111");
	}

}
