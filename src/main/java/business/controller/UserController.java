package business.controller;

import business.model.User;
import business.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Configuration
@RestController
@RequestMapping(path = "/api/v1", produces = "application/json")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Async("asyncTaskExecutor")
    @GetMapping("/users")
    public CompletableFuture<List<User>> getAllEmployees() {
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    @Async("asyncTaskExecutor")
    @GetMapping("/users/{id}")
    public CompletableFuture<ResponseEntity<User>> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return userRepository.findById(employeeId)
                .map(employee -> CompletableFuture.completedFuture(ResponseEntity.ok().body(employee)))
                .orElse(CompletableFuture.completedFuture(ResponseEntity.notFound().build()));
    }

    @Async("asyncTaskExecutor")
    @PostMapping("/users")
    public CompletableFuture<User> createEmployee(@Valid @RequestBody User employee) {
        return CompletableFuture.completedFuture(userRepository.save(employee));
    }

    @Async("asyncTaskExecutor")
    @PutMapping("/users/{id}")
    public CompletableFuture<ResponseEntity<User>> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                                  @Valid @RequestBody User employeeDetails) {
        return userRepository.findById(employeeId)
                .map(employee -> {
                    employee.setEmail(employeeDetails.getEmail());
                    employee.setLastName(employeeDetails.getLastName());
                    employee.setFirstName(employeeDetails.getFirstName());
                    employee.setPaid(false);
                    final User updatedEmployee = userRepository.save(employee);
                    return CompletableFuture.completedFuture(ResponseEntity.ok(updatedEmployee));
                })
                .orElse(CompletableFuture.completedFuture(ResponseEntity.notFound().build()));
    }

    @Async("asyncTaskExecutor")
    @DeleteMapping("/employees/{id}")
    public CompletableFuture<Map<String, Boolean>> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        return userRepository.findById(employeeId)
                .map(employee -> {
                    userRepository.delete(employee);
                    Map<String, Boolean> response = new HashMap<>();
                    response.put("deleted", Boolean.TRUE);
                    return CompletableFuture.completedFuture(response);
                })
                .orElse(CompletableFuture.completedFuture(null));
    }

    @Async("asyncTaskExecutor")
    @GetMapping("/employees/emailcheck/{email}")
    public CompletableFuture<ResponseEntity<Boolean>> emailCheck(@PathVariable(value = "email") String email) {
        final Optional<User> employeesByEmailId = userRepository.findByEmail(email);
        return CompletableFuture.completedFuture(ResponseEntity.ok(employeesByEmailId.isPresent()));
    }
}
