package cn.edu.tongji.gohome.order.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface OrderService {

    HashMap<String, Object> searchOrderInfoForCustomerId(long customerId, Integer currentPage, Integer pageSize);

    HashMap<String, Object> searchOrderInfoForStayId(long stayId, Integer currentPage, Integer pageSize);

    HashMap<String, Object> searchOrderDetailedInfoForOrderId(long orderId, Integer currentPage, Integer pageSize);
}
