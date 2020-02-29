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

}
