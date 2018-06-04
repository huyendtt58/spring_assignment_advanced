package com.spring.fpt.dao;

import java.time.LocalDate;
import java.util.List;

import com.spring.fpt.model.AttendanceSlotsEntity;
import com.spring.fpt.model.StudentsEntity;

public interface AttendanceSlotsDao {
	List<AttendanceSlotsEntity> getListAttSlots(LocalDate time);
	
	boolean checkExistAttSlots(int att_slot_id);
	
	List<StudentsEntity> getAllStudents(int att_slot_id);
}
