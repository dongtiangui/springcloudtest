package com.integral.boot.dao;

import com.integral.boot.domain.Integral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIntegralFind extends JpaRepository<Integral,Long> {
}
