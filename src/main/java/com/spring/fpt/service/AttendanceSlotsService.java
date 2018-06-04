package com.spring.fpt.service;

import java.time.LocalDate;
import java.util.List;

import com.spring.fpt.model.AttendanceSlotsEntity;
import com.spring.fpt.model.StudentsEntity;

public interface AttendanceSlotsService {
	List<AttendanceSlotsEntity> getAttSlotsByDate(LocalDate date);
	
	boolean checkAttSlotsExist(int att_slots_id);

	List<StudentsEntity> getAllStudents(int att_slot_id);
}
