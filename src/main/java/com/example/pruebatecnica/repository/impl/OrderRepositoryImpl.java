package com.example.pruebatecnica.repository.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.example.pruebatecnica.model.OrderEntity;
import com.example.pruebatecnica.repository.OrderRepository;
import com.example.pruebatecnica.service.dto.Order;
import com.example.pruebatecnica.service.dto.Summary;

public class OrderRepositoryImpl implements OrderRepository {
	
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);

	public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Almacenar todos los datos extraidos de la api en la bbdd
	 * @return 
	 */
	@Override
	@Transactional
	public  int[][] saveAll(List<Order> orders) {
		  
		return jdbcTemplate.batchUpdate(
				"INSERT INTO TD_ORDER (uuid, orderid, region, country, item_type,"
						+ "sales_channel, priority , order_date , ship_date ,"
						+ "	units_sold , unit_price , unit_cost , total_revenue, " + "	total_cost , total_profit ) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				orders, 100, (PreparedStatement ps, Order order) -> {
					
					ps.setString(1, order.getUuid());
					ps.setDouble(2, order.getId());
					ps.setString(3, order.getRegion());
					ps.setString(4, order.getCountry());
					ps.setString(5, order.getItem_type());
					ps.setString(6, order.getSales_channel());
					ps.setString(7, order.getPriority());
					ps.setInt(7, order.getUnits_sold());
					ps.setDate(8, parseOrderDate(order.getDate()));
					ps.setDate(9, parseOrderDate(order.getShip_date()));
					ps.setLong(10, order.getUnits_sold());
					ps.setDouble(11, order.getUnit_price());
					ps.setDouble(12, order.getUnit_cost());
					ps.setDouble(13, order.getTotal_revenue());
					ps.setDouble(14, order.getTotal_cost());
					ps.setDouble(15, order.getTotal_profit());

				});
	}
	/**
	 * Obtener resumen por region
	 */
	public List<Summary> findByRegion() {
		String sql = "SELECT region as clave,count(*) AS valor FROM TD_ORDER GROUP BY region ORDER BY valor desc";
        
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Summary>(Summary.class));

	}
	/**
	 * obtener resumen por pais
	 */
	public List<Summary> findByCountry() {
		String sql = "SELECT country as clave,count(*) AS valor FROM TD_ORDER GROUP BY country ORDER BY valor desc";
        
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Summary>(Summary.class));

	}
	
	/**
	 * obtener resumen por itemType
	 */
	public List<Summary> findByItemType() {
		String sql = "SELECT item_type as clave,count(*) AS valor FROM TD_ORDER GROUP BY item_type ORDER BY valor desc";
        
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Summary>(Summary.class));

	}
	
	/**
	 * obtener resumen por salesChannel
	 */
	public List<Summary> findBySalesChannel() {
		String sql = "SELECT sales_channel as clave,count(*) AS valor FROM TD_ORDER GROUP BY sales_channel ORDER BY valor desc";
        
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Summary>(Summary.class));
	}
	
	/**
	 * obtener resumen por campo priority
	 */
	public List<Summary> findByPriority() {
		String sql = "SELECT priority as clave,count(*) AS valor FROM TD_ORDER GROUP BY priority ORDER BY valor desc";
        
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Summary>(Summary.class));

	}
	/**
	 * devolver todos ordenados por orderId
	 */
	public List<OrderEntity> findAllOrderByOrderId() {
		String sql = "select * from td_order order by orderid DESC";
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<OrderEntity>(OrderEntity.class));
		
	}
	
	/**
	 *  parsear la fecha y posteriormente insertarla a bbdd
	 * @param dateString
	 * @return
	 */
	public Date parseOrderDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date= null;
		try {
			date = new Date(format.parse(dateString).getTime());
		}catch(ParseException e) {
			logger.debug("Error al parsear fecha, será grabada con valor null, orderDate: "+dateString);
		}
		return date;
	}

}
