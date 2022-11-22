package com.greedy.newworker.att.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.newworker.att.entity.Att;
import com.greedy.newworker.employee.dto.EmployeeDto;

public interface AttRepository extends JpaRepository<Att, Long>{

	@Query(value = "SELECT * " +
		             "FROM tbl_att " +
			        "WHERE ATT_NO = :attNo", nativeQuery = true)
	Optional<Att> findByAttNoAndEmployeeNo(@Param("attNo") Long attNo);

	Page<Att> findByAttMonthContainsAndEmployee(Pageable pageable, String attMonth, EmployeeDto employee);

	/*
	 * + "AND employee.employeeNo =:employee.employeeNo"
	 * */
	
}
