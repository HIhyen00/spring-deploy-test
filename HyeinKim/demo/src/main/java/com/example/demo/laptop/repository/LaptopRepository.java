package com.example.demo.laptop.repository;

import com.example.demo.laptop.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
