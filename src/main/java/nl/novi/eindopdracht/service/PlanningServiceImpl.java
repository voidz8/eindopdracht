package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.PlanningNotFoundException;
import nl.novi.eindopdracht.model.Order;
import nl.novi.eindopdracht.model.Planning;
import nl.novi.eindopdracht.repository.PlanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PlanningServiceImpl implements PlanningService{

    @Autowired
    private PlanningRepository planningRepository;

    @Override
    public Collection<Planning> getAllSchedules() {
        return planningRepository.findAll();
    }

    @Override
    public Optional<Planning> getPlanning(long id) {
        if (!planningRepository.existsById(id)){throw new PlanningNotFoundException(); }
        return planningRepository.findById(id);
    }

    @Override
    public Long createPlanning(Planning planning) {
        Planning newPlanning = planningRepository.save(planning);
        return newPlanning.getId();
    }

    @Override
    public void deletePlanning(long id) {
        if (!planningRepository.existsById(id)){throw new PlanningNotFoundException();}
        planningRepository.deleteById(id);
    }

    @Override
    public Collection<Order> getOrders(long id) {
        if (!planningRepository.existsById(id)){throw new PlanningNotFoundException();}
        Planning planning = planningRepository.findById(id).get();
        return planning.getOrders();
    }

    @Override
    public void addOrder(long id, Order order) {
        if (!planningRepository.existsById(id)){throw new PlanningNotFoundException();}
        Planning planning = planningRepository.findById(id).get();
        planning.addOrder(order);
        planningRepository.save(planning);
    }

    @Override
    public void removeOrder(long id, Order order) {
        if (!planningRepository.existsById(id)){throw new PlanningNotFoundException();}
        Planning planning = planningRepository.findById(id).get();
        planning.removeOrder(order);
        planningRepository.save(planning);
    }
}
