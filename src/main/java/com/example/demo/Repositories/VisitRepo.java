package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.classes.Visit;

public interface VisitRepo extends JpaRepository<Visit,Long>{

}
