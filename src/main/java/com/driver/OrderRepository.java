package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    private HashMap<String, Order> orderDb;
    private HashMap<String, DeliveryPartner> deliveryPartnerDb;
    private HashMap<DeliveryPartner, List<Order>> orderPartnerPairDb;
    private HashMap<String, String> orderTimeDb;


    public OrderRepository(){
        this.orderDb = new HashMap<>();
        this.deliveryPartnerDb = new HashMap<>();
        this.orderPartnerPairDb = new HashMap<>();
        this.orderTimeDb = new HashMap<>();
    }

    public String addOrder(Order order){
        orderDb.put(order.getId(), order);
        orderTimeDb.put(order.getId(), order.getTimeInString());
        return "Success";
    }

    public String addPartner(String id){
        DeliveryPartner deliveryPartner = new DeliveryPartner(id);
        deliveryPartnerDb.put(id, deliveryPartner);
        return "Success";
    }

    public String addOrderPartnerPair(String orderId, String partnerId){
        Order order = orderDb.get(orderId);
        DeliveryPartner partner = deliveryPartnerDb.get(partnerId);
        List<Order> orders;
        if(!orderPartnerPairDb.containsKey(partner)){
            orders = new ArrayList<>();
        }else{
            orders = orderPartnerPairDb.get(partner);
        }
        orders.add(order);
        partner.setNumberOfOrders(orders.size());
        orderPartnerPairDb.put(partner, orders);
        return "Success";
    }

    public Order getOrderById(String id){
        Order order = orderDb.get(id);
        return order;
    }

    public DeliveryPartner getPartnerById(String id){
        DeliveryPartner partner = deliveryPartnerDb.get(id);
        return partner;
    }

    public Integer getOrderCountByPartnerId(String id){
        DeliveryPartner partner = deliveryPartnerDb.get(id);
        return partner.getNumberOfOrders();
    }

    public List<String> getOrdersByPartnerId(String id){
        DeliveryPartner partner = deliveryPartnerDb.get(id);
        List<Order> list = orderPartnerPairDb.get(partner);
        List<String> ans = new ArrayList<>();
        for(Order order : list){
            ans.add(order.getId());
        }
        return ans;
    }

    public List<String> getAllOrders(){
        List<String> ans = new ArrayList<>();
        for(String id : orderDb.keySet()){
            ans.add(id);
        }
        return ans;
    }

    public Integer getCountOfUnassignedOrders(){
        List<String> totalAssignedOrders = new ArrayList<>();
        int count = 0;
        for(DeliveryPartner partner : orderPartnerPairDb.keySet()){
            List<Order> list = orderPartnerPairDb.get(partner);
            for(Order order : list){
                totalAssignedOrders.add(order.getId());
            }
        }
        for(String id : orderDb.keySet()){
            if(!totalAssignedOrders.contains(id)){
                count++;
            }
        }
        return count;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        String timeArray[] = time.split(":");
        int hh = Integer.parseInt(timeArray[0]);
        int mm = Integer.parseInt(timeArray[1]);
        int timeInInt = hh * 60 + mm;
        int count = 0;
        DeliveryPartner partner = deliveryPartnerDb.get(partnerId);
        List<Order> listOfOrdersByCurrentPartner = orderPartnerPairDb.get(partner);
        for(Order order : listOfOrdersByCurrentPartner){
            if(order.getDeliveryTime() > timeInInt){
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        DeliveryPartner partner = deliveryPartnerDb.get(partnerId);
        List<Order> orders = orderPartnerPairDb.get(partner);
        int recent = Integer.MIN_VALUE;
        Order theLastOrder = null;
        for(Order order : orders){
            if(order.getDeliveryTime() > recent){
                recent = order.getDeliveryTime();
                theLastOrder = order;
            }
        }
        return orderTimeDb.get(theLastOrder.getTimeInString());
    }

    public String deletePartnerById(String partnerId){
        DeliveryPartner partner = deliveryPartnerDb.get(partnerId);
        orderPartnerPairDb.remove(partner);
        deliveryPartnerDb.remove(partnerId);
        return "Success";
    }

    public String deleteOrderById(String id){
        Order order = orderDb.get(id);
        orderDb.remove(id);
        for(DeliveryPartner partner : orderPartnerPairDb.keySet()){
            if(orderPartnerPairDb.get(partner).contains(order)){
                orderPartnerPairDb.get(partner).remove(order);
                break;
            }
        }
        return "Success";
    }
}
