package org.gunitha.sitemanagementsystem.controller.beans;

import java.util.List;

import lombok.Data;

@Data
public class SiteBean {

	private Long id;

	private String name;

	private String number;

	private Double latitude;

	private Double longitude;

	private AddressBean address;

	private Long DealershipId;

	private Long siteEngineerId;

	private Long siteOwnerId;
	
	private List<Long> dealershipIds;
}
