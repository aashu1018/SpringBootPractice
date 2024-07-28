package com.springbootweb.springbootweb.Service;

import com.springbootweb.springbootweb.DTO.EmployeeDTO;
import com.springbootweb.springbootweb.Entity.EmployeeEntity;
import com.springbootweb.springbootweb.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

//    public EmployeeDTO getEmployeeById(Long id) {
//        EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElse(null);
//        return modelMapper.map(employeeEntity, EmployeeDTO.class);
//    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeEntity =  employeeRepository.findById(id);
        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map( e -> modelMapper.map(e, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity =  employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long employeeId) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isExistsByEmployeeId(Long employeeId){
        if(!employeeRepository.existsById(employeeId)){
            return false;
        }
        return true;
    }

    public boolean deleteEmployeeById(Long employeeId) {
        if(!isExistsByEmployeeId(employeeId)){
            return false;
        }
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Long employeeId) {
        if(!isExistsByEmployeeId(employeeId)){
            return null;
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
