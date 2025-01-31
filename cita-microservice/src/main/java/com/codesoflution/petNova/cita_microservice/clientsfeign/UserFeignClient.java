package com.codesoflution.petNova.cita_microservice.clientsfeign;

import com.codesoflution.petNova.cita_microservice.dtos.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-microservice", url = "http://localhost:8001", path = "/apiPetNova/users")
public interface UserFeignClient {

    @GetMapping("/getUserById/{userId}")
    UserDTO getUserById(@PathVariable("userId") Long userId);
}
