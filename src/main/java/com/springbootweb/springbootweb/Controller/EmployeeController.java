package com.springbootweb.springbootweb.Controller;

import com.springbootweb.springbootweb.DTO.EmployeeDTO;
import com.springbootweb.springbootweb.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="employeeId") Long id){
//        EmployeeDTO employeeDTO =  employeeService.getEmployeeById(id);
//        if(employeeDTO == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(employeeDTO);

        Optional<EmployeeDTO> employeeDTO =  employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new NoSuchElementException("Employee Not Found"));
//        if(employeeDTO.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(employeeDTO);
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public String handleEmployeeNotFound(NoSuchElementException exception){
//        return "Employee with the given id not found";
//    }

//    @GetMapping
//    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age){
//        return employeeService.getAllEmployees();
//    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        return employeeService.createNewEmployee(inputEmployee);
//    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO employeeDTO = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

//    @PutMapping(path = "/{employeeId}")
//    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,
//                                          @PathVariable Long employeeId){
//        return employeeService.updateEmployeeById(employeeDTO, employeeId);
//    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,
                                          @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDTO, employeeId));
    }

//    @DeleteMapping(path = "/{employeeId}")
//    public void deleteEmployeeById(@PathVariable Long employeeId){
//        employeeService.deleteEmployeeById(employeeId);
//    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean deleted = employeeService.deleteEmployeeById(employeeId);
        if(deleted){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

//    @PatchMapping(path = "/{employeeId}")
//    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
//                                                 @PathVariable Long employeeId){
//        return employeeService.updatePartialEmployeeById(updates, employeeId);
//    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId){
        EmployeeDTO employeeDTO =  employeeService.updatePartialEmployeeById(updates, employeeId);
        if(employeeDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
    }

//    @GetMapping(path = "/getSecretMessage")
//    public String getSecretMessage(){
//        return "The secret message is : abchdmakchns";
//    }

//    @GetMapping(path = "/employees/{employeeId}")
//    @GetMapping(path = "/{employeeId}")
//    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
//        return new EmployeeDTO(employeeId, "Rahul", "rahul@gmail.com", 27, LocalDate.of(2024, 8, 06), true);
//    }

//    @GetMapping(path = "/employees")
//    @GetMapping
//    public String getAllEmployees(@RequestParam(required = false) Integer age){
//        return "Hi age " + age;
//    }

//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        inputEmployee.setId(101L);
//        return inputEmployee;
//    }
}
