package com.greedy.newworker.survey.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.newworker.employee.entity.Department;
import com.greedy.newworker.employee.entity.Employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "SUR_SURVEY")
@SequenceGenerator(name = "SUR_SEQ_GENERATOR",
sequenceName = "SEQ_SUR_NO",
initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Survey {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUR_SEQ_GENERATOR")
	@Column(name = "SUR_NO")
	private Long surNo;

	@Column(name = "SUR_TITLE")
	private String surTitle;

	@Column(name = "SUR_CONTENT")
	private String surContent;
	
	@Column(name = "SUR_STATUS")
	private String surStatus;
	
	@Column(name = "SUR_DATE")
	private Date surDate;
	
	@Column(name = "SUR_UPDATE")
	private Date surUpDate;
	
	@Column(name = "SUR_START_DATE")
	private Date surStartDate;
	
	@Column(name = "SUR_END_UPDATE")
	private Date surEndDate;
	
	@Column(name = "SUR_IMG_PATH")
	private String surImgPath;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_NO")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "DEP_NO")
	private Department dep;
	
}