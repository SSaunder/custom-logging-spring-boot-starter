package com.github.ssaunder.logging.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Component;
import sun.net.util.IPAddressUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Optional;

/**
 * IP解析地理位置
 */
@Component
public class GeoIPLite2Utils {

	public static DatabaseReader INSTANCE;

	public GeoIPLite2Utils(DatabaseReader reader){
		INSTANCE = reader;
	}

	public static String resolverIp(String ip) throws IOException, GeoIp2Exception {
		if (internalIp(ip)) {
			return "内网地址";
		}
		InetAddress ipAddress = InetAddress.getByName(ip);
		Optional<CityResponse> cityResponse = INSTANCE.tryCity(ipAddress);
		return cityResponse.map(e -> e.getCountry().getName() + e.getMostSpecificSubdivision().getName() + e.getCity().getName()).orElse(null);
	}

	public static boolean internalIp(String ip) {
		byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
		return internalIp(addr);
	}

	public static boolean internalIp(byte[] addr) {
		final byte b0 = addr[0];
		final byte b1 = addr[1];
		//10.x.x.x/8
		final byte SECTION_1 = 0x0A;
		//172.16.x.x/12
		final byte SECTION_2 = (byte) 0xAC;
		final byte SECTION_3 = (byte) 0x10;
		final byte SECTION_4 = (byte) 0x1F;
		//192.168.x.x/16
		final byte SECTION_5 = (byte) 0xC0;
		final byte SECTION_6 = (byte) 0xA8;
		switch (b0) {
			case SECTION_1:
				return true;
			case SECTION_2:
				if (b1 >= SECTION_3 && b1 <= SECTION_4) {
					return true;
				}
			case SECTION_5:
				switch (b1) {
					case SECTION_6:
						return true;
				}
			default:
				return false;
		}
	}
}
