package com.xlifems.backendninja.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xlifems.backendninja.entity.Log;

@Repository("logRepository")
public interface LogRepository extends JpaRepository<Log, Serializable>{

}
