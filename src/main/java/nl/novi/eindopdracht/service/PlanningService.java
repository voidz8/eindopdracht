package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Planning;

import java.util.Collection;
import java.util.Optional;

public interface PlanningService {
    Collection<Planning> getAllSchedules();
    Optional<Planning> getPlanning(long id);
    Long createPlanning(Planning planning);
    void deletePlanning(long id);
    Collection<Order> getOrders(long id);
    void addOrder(long id, Order order);
    void removeOrder(long id, Order order);
}
