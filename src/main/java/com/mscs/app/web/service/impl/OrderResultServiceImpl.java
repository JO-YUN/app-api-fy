package com.mscs.app.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.web.dao.OrderResultMapper;
import com.mscs.app.web.service.OrderResultService;

@Service
public class OrderResultServiceImpl implements OrderResultService{
	@Autowired
	private OrderResultMapper orderResultMapper;
}
