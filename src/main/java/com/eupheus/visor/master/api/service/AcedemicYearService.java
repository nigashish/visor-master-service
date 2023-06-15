package com.eupheus.visor.master.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.accelerate.visor.school.model.AcademicYear;
import com.eupheus.visor.master.api.repository.AcademicYearRepository;

public class AcedemicYearService {
	@Autowired(required=true)
	private  AcademicYearRepository academicYearRepository;
	
	public Optional<AcademicYear> findById(Long id) {
		return academicYearRepository.findById(id);
	}

}
