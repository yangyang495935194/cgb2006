package com.jt.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;

@Service
public class OrderServiceImpl implements DubboOrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;


	@Override
	@Transactional	//控制事务
	public String saveOrder(Order order) {

		//1.准备订单Id号   大量的字符串拼接
		String orderId = ""+order.getUserId() + System.currentTimeMillis();

		//2.完成订单入库
		order.setOrderId(orderId).setStatus(1);
		orderMapper.insert(order);

		//3.完成订单物流入库
		OrderShipping orderShipping = order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShippingMapper.insert(orderShipping);

		//4.完成订单商品入库操作
		List<OrderItem> orderItemList = order.getOrderItems();
		//手动完成批量赋值的操作
		//insert into orderItem(....) values (xx,xxx,xxx),(xx,xx,xx),(xx,xx,xx);
		for (OrderItem orderItem : orderItemList){
			orderItem.setOrderId(orderId);
			orderItemMapper.insert(orderItem);
		}
		System.out.println("完成订单入库操作");

		return orderId;
	}

	@Override
	public Order findOrderById(String id) {
		//1，查询订单信息
		Order order=orderMapper.selectById(id);
		OrderShipping orderShipping=orderShippingMapper.selectById(id);
		QueryWrapper<OrderItem>queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("order_id",id);
		List<OrderItem>lists=orderItemMapper.selectList(queryWrapper);
		return order.setOrderItems(lists).setOrderShipping(orderShipping);
	}
}