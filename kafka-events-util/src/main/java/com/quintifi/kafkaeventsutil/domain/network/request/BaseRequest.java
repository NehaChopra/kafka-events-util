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
	private String topic;
	private String key;
	private String message;
	private Integer partition;
	private Long timestamp;

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getPartition() {
		return partition;
	}

	public void setPartition(Integer partition) {
		this.partition = partition;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "BaseRequest [payload=" + payload + ", info=" + info + ", topic=" + topic + ", key=" + key + ", message="
				+ message + ", partition=" + partition + ", timestamp=" + timestamp + "]";
	}

}
