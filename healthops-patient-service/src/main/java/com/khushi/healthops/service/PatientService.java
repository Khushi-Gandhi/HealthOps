package com.khushi.healthops.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.khushi.healthops.DTO.PatientRequestDTO;
import com.khushi.healthops.DTO.PatientResponseDTO;
import com.khushi.healthops.exception.EmailAlreadyExistsException;
import com.khushi.healthops.exception.PatientNotFoundException;
import com.khushi.healthops.grpc.BillingServiceGrpcClient;
import com.khushi.healthops.kafka.KafkaProducer;
import com.khushi.healthops.mapper.PatientMapper;
import com.khushi.healthops.model.Patient;
import com.khushi.healthops.repository.PatientRepository;

@Service
public class PatientService {

	private PatientRepository patientRepository;
	private BillingServiceGrpcClient billingServiceGrpcClient;
	private final KafkaProducer kafkaProducer;
	
	public PatientService(PatientRepository patientRepository,
			BillingServiceGrpcClient billingServiceGrpcClient, KafkaProducer kafkaProducer) {
		this.patientRepository = patientRepository;
		this.billingServiceGrpcClient = billingServiceGrpcClient;
		this.kafkaProducer = kafkaProducer;
	}
	
	public List<PatientResponseDTO> getPatients() {
		List<Patient> patients = patientRepository.findAll();
		
		// patient -> PatientMapper.toDTO(patient)  =>  PatientMapper::toDTO
		List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(PatientMapper::toDTO).toList();
		return patientResponseDTOs;
	}
	
	public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
		if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
			throw new EmailAlreadyExistsException("A patient with this email " + patientRequestDTO.getEmail() +
					" already exists");
		}
		
		Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
		
		billingServiceGrpcClient.createBillingAccount(patient.getId().toString(), patient.getName()
				, patient.getEmail());
		
		kafkaProducer.sendEvent(patient);
		
		return PatientMapper.toDTO(patient);
	}
	
	public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
		Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found with id: " + id));
		if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
			throw new EmailAlreadyExistsException("A patient with this email " + patientRequestDTO.getEmail() +
					" already exists");
		}
		
		patient.setName(patientRequestDTO.getName());
		patient.setAddress(patientRequestDTO.getAddress());
		patient.setEmail(patientRequestDTO.getEmail());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
		
		Patient updatedPatient = patientRepository.save(patient);
		return PatientMapper.toDTO(updatedPatient);
	}

	public void DeletePatient(UUID id) {
		patientRepository.deleteById(id);
	}
}
