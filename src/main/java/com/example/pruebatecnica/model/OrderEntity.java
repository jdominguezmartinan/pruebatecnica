package com.example.pruebatecnica.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "td_order")
public class OrderEntity {

	@Column(name = "uuid", nullable = false)
	private String uuid;
	@Column(name = "orderid", nullable = false)
	private BigDecimal orderid;
	@Column(name = "region")
	private String region;
	@Column(name = "country")
	private String country;
	@Column(name = "item_type")
	private String itemType;
	@Column(name = "sales_channel")
	private String salesChannel;
	@Column(name = "priority")
	private String priority;
	@Column(name = "order_date")
	private Date orderDate;
	@Column(name = "ship_date")
	private Date shipDate;
	@Column(name = "units_sold")
	private BigDecimal unitsSold;
	@Column(name = "unit_price")
	private BigDecimal unitPrice;
	@Column(name = "unit_cost")
	private BigDecimal unitCost;
	@Column(name = "total_revenue")
	private BigDecimal totalRevenue;
	@Column(name = "total_cost")
	private BigDecimal totalCost;
	@Column(name = "total_profit")
	private BigDecimal totalProfit;
}
