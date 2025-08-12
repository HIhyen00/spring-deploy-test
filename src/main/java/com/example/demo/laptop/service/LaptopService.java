package com.example.demo.laptop.service;

import com.example.demo.laptop.service.request.RegisterLaptopRequest;
import com.example.demo.laptop.service.response.RegisterLaptopResponse;

public interface LaptopService {

    RegisterLaptopResponse register(RegisterLaptopRequest request);
}
