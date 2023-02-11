package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository = new OrderRepository();

    public String addOrder(Order order){
        String response = orderRepository.addOrder(order);
        return response;
    }

    public String addPartner(String id){
        String response = orderRepository.addPartner(id);
        return response;
    }

    public String addOrderPartnerPair(String orderId, String partnerId){
        String response = orderRepository.addOrderPartnerPair(orderId, partnerId);
        return response;
    }

    public Order getOrderById(String id){
        Order response = orderRepository.getOrderById(id);
        return response;
    }

    public DeliveryPartner getPartnerById(String id){
        DeliveryPartner response = orderRepository.getPartnerById(id);
        return response;
    }

    public Integer getOrderCountByPartnerId(String id){
        Integer response = orderRepository.getOrderCountByPartnerId(id);
        return response;
    }

    public List<String> getOrdersByPartnerId(String id){
        List<String> response = orderRepository.getOrdersByPartnerId(id);
        return response;
    }

    public List<String> getAllOrders(){
        List<String> response = orderRepository.getAllOrders();
        return response;
    }

    public Integer getCountOfUnassignedOrders(){
        Integer response = orderRepository.getCountOfUnassignedOrders();
        return response;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        Integer response = orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
        return response;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        String response = orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
        return response;
    }

    public String deletePartnerById(String partnerId){
        String response = orderRepository.deletePartnerById(partnerId);
        return response;
    }

    public String deleteOrderById(String id){
        String response = orderRepository.deleteOrderById(id);
        return response;
    }
}
