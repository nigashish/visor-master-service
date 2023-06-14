package com.eupheus.visor.master.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.accelerate.visor.model.AcademicYear;
import com.eupheus.visor.master.api.repository.AcademicYearRepository;

public class AcedemicYearService {
	@Autowired(required=true)
	private  AcademicYearRepository academicYearRepository;
	
	public Optional<AcademicYear> findById(Long id) {
		return academicYearRepository.findById(id);
	}

}
