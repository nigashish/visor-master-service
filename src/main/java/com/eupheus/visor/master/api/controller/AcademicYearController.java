package com.eupheus.visor.master.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accelerate.visor.school.model.AcademicYear;
import com.eupheus.visor.master.api.repository.AcademicYearRepository;
@RestController
@RefreshScope
@RequestMapping("/masters")
public class AcademicYearController {
	@Autowired
	private  AcademicYearRepository academicYearRepository;
	@GetMapping("academicyearbyid/{id}")
	  public Optional<AcademicYear> findAcademicYearById(@PathVariable Long id)
	  {
		Optional<AcademicYear> result=  academicYearRepository.findById(id);
		System.out.println("result---"+result);
		return result;
	  }
	
	@GetMapping("/academicYears")
	  public List<AcademicYear> findAllAcademicYear()
	  {
		List<AcademicYear> allAcademicYear = academicYearRepository.findAll();
		System.out.println("allAcademicYear---"+allAcademicYear.size());
		return allAcademicYear;
	  }
	@PostMapping("/academicYears")
	public AcademicYear bookOrder(@RequestBody AcademicYear academicYear) {
		System.out.println("academicYear--"+academicYear);
		return academicYearRepository.save(academicYear);
	}
}
