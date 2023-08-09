package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;
    public Schedule createSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds){
        schedule.setEmployee(new HashSet<>(employeeRepository.findAllById(employeeIds)));
        schedule.setPet(new HashSet<>(petRepository.findAllById(petIds)));

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(long petId){
        Pet pet = petRepository.getOne(petId);

        return scheduleRepository.getAllByPetContains(pet);
    }

    public List<Schedule> getScheduleForEmployee(long employeeId){
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.getAllByEmployeeContains(employee);
    }

    public List<Schedule> getScheduleForCustomer(long customerId){
        Customer customer = customerRepository.getOne(customerId);
        return scheduleRepository.getAllByPetIn(customer.getPetIds());
    }
}
