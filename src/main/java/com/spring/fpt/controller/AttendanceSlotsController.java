package com.spring.fpt.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.fpt.model.AttendanceSlotsEntity;
import com.spring.fpt.model.StudentsEntity;
import com.spring.fpt.service.AttendanceSlotsService;

@Controller
public class AttendanceSlotsController {
	
	@Autowired
	AttendanceSlotsService attSlotsService;
	
	@GetMapping("/times")
	public String getClasses(Model model) {
		LocalDate date = LocalDate.now();
		boolean checkAttended = true;
		List<AttendanceSlotsEntity> listAttSlots = attSlotsService.getAttSlotsByDate(date);
		Map<AttendanceSlotsEntity, Boolean> listExistAttSlots = new HashMap<AttendanceSlotsEntity, Boolean>();
		for (AttendanceSlotsEntity attSlots : listAttSlots) {
			checkAttended = attSlotsService.checkAttSlotsExist(attSlots.getId());
			listExistAttSlots.put(attSlots, checkAttended);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = date.format(formatter);
		model.addAttribute("nowDate", formattedDate);
		model.addAttribute("listExistAttSlots", listExistAttSlots);
		return "att-slots";
	}
	
	@GetMapping("/attend")
	public String getAttendanceDetail(@RequestParam("id") int att_slots_id, Model model) {
		List<StudentsEntity> listStu = attSlotsService.getAllStudents(att_slots_id);
		model.addAttribute("listStu", listStu);
		return "attend";
	}

}
