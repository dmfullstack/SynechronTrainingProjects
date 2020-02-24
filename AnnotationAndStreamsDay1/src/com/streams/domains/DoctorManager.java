package com.streams.domains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoctorManager {

	private List<Doctor> doctorList = new ArrayList<>();
	
	public DoctorManager() {
		init();
	}
	
	private void init(){
		
		this.doctorList.add(new Doctor("D101", "Amit Singh", "�PD"));
		this.doctorList.add(new Doctor("D102", "Subhas Pandey","�PD"));
		this.doctorList.add(new Doctor("D103", "Ananya Chanda","�PD"));
		this.doctorList.add(new Doctor("D104", "Priyanka Dhamankar","Dentist"));
		this.doctorList.add(new Doctor("D105", "Parag Singh","Child"));
		this.doctorList.add(new Doctor("D106", "Ananya Singh","Child"));
		
	}
	
	public Map<String, String> getDoctor(String ...id){
		Map<String, String> docMap = new HashMap<>();
		if(id.length == 0){
			docMap = this.doctorList.stream()
					.collect(Collectors
								.toMap(Doctor::getDoctorId, Doctor::getName)
							);
		} else {
			docMap = this.doctorList.stream()
					.filter((doc)-> doc.getDoctorId().equals(id[0]))
					.collect(Collectors.toMap(Doctor::getDoctorId, Doctor::getName));
		}
		return docMap;		
	}
	
	public List<Doctor> getDoctorsSpeciality(String ...speciality){
		List<Doctor> docList = new ArrayList<>();
		if(speciality.length == 0){
			docList = this.doctorList.stream().collect(Collectors.toList());
		} else {
			docList = this.doctorList.stream()
					.filter((doc)-> doc.getSpeciality().equals(speciality[0]))
					.collect(Collectors.toList());
		}
		return docList;		
	}
	
	public List<Patient> getSeniorCitizenPatient(String doctorName){
		List<Doctor> docList = new ArrayList<>();
		List<Patient> patList = new ArrayList<>();
		
		PatientManager patientMgr = new PatientManager();
		
		docList = this.doctorList.stream()
				.filter((doc)-> doc.getName().equals(doctorName))
					.collect(Collectors.toList());

		if(docList.size()>0){
			patList  = patientMgr.getPatientList();
			String docid = docList.get(0).getDoctorId();
			patList  =  patList.stream()
					.filter((patient)-> patient.getDoctorId().equals(docid))
					.filter((patient)-> patient.getAge()>60)
					.collect(Collectors.toList());
		}		
		return patList ;		
	}
	
	

}