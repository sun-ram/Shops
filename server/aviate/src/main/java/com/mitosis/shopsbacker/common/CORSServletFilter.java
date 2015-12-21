package com.mitosis.shopsbacker.common;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
public class CORSServletFilter implements ContainerResponseFilter {

	@Override
	public ContainerResponse filter(ContainerRequest req, ContainerResponse contResp) {
		//req.getHeaderValue("X-FORWARDED-FOR");
		//contResp.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "http://192.168.1.22:7777");
		contResp.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "*");
		contResp.getHttpHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT");

		String reqHead = req.getHeaderValue("Access-Control-Request-Headers");
		contResp.getHttpHeaders().putSingle("Access-Control-Allow-Headers", reqHead);

		return contResp;
	}

}
