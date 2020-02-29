package com.quintifi.kafkaeventsutil.domain.network.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author nchopra
 *
 */
@JsonInclude(Include.NON_NULL)
public class BaseRequest implements Serializable {

	private static final long serialVersionUID = -1898217599935891882L;
	private String payload;
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "BaseRequest [payload=" + payload + ", info=" + info + "]";
	}

}
