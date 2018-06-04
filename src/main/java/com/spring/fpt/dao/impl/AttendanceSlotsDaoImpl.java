package com.spring.fpt.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring.fpt.dao.AttendanceSlotsDao;
import com.spring.fpt.model.AttendanceDetailsEntity;
import com.spring.fpt.model.AttendanceSlotsEntity;
import com.spring.fpt.model.StudentsEntity;

@Repository
public class AttendanceSlotsDaoImpl implements AttendanceSlotsDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<AttendanceSlotsEntity> getListAttSlots(LocalDate time) {
		List<AttendanceSlotsEntity> listAttSlots = new ArrayList<AttendanceSlotsEntity>();
		Date timeSql = java.sql.Date.valueOf(time);		
		String sql = "from AttendanceSlotsEntity as attSlots" + 
			" join TimeslotsEntity as tmSlots on tmSlots.slotId = attSlots.slotId" + 
			" join ClassesEntity as classes on classes.classId = attSlots.classId" + 
			" where date(attSlots.createDate) = ?" + 
			" and attSlots.status = 1" + 
			" and classes.classStatus = 1";
		List<Object[]> listObj = entityManager.createQuery(sql).setParameter(0, timeSql).getResultList();
		for (Object[] obj : listObj) {
			AttendanceSlotsEntity attSlots1 = (AttendanceSlotsEntity) obj[0];
			listAttSlots.add(attSlots1);
		}
		return listAttSlots;
	}

	@Override
	public boolean checkExistAttSlots(int att_slot_id) {
		String sql = "from AttendanceDetailsEntity where idTimeSlot = ?";
		List<AttendanceDetailsEntity> attDetail = new ArrayList<AttendanceDetailsEntity>();
		attDetail = entityManager.createQuery(sql, AttendanceDetailsEntity.class)
				.setParameter(0, att_slot_id).getResultList();
		return (attDetail.size() == 0) ? false : true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentsEntity> getAllStudents(int att_slot_id) {
		List<StudentsEntity> listStudents = new ArrayList<StudentsEntity>();
		String sql;
		if(checkExistAttSlots(att_slot_id)) {
			sql = "from AttendanceSlotsEntity as attSlots" + 
				" join student_class as stuClass on stuClass.class_id = attSlots.class_id" +
				" join students as stu on stu.student_id = stuClass.student_id" +
				" where attSlots.id = ?" + 
				" and stuClass.status = 1";
		} else {
			sql = "from AttendanceSlotsEntity as attSlots" + 
				" join student_class as stuClass on stuClass.class_id = attSlots.class_id" +
				" join students as stu on stu.student_id = stuClass.student_id" +
				" join attendance_details as attDetail on attDetail.id_time_slot = attSlots.id" +
				" where attSlots.id = ?" + 
				" and stuClass.status = 1"; 
		}
		List<Object[]> listObj = entityManager.createQuery(sql).setParameter(0, att_slot_id).getResultList();
		for (Object[] obj : listObj) {
			StudentsEntity student = (StudentsEntity) obj[2];
			listStudents.add(student);
		}
		return listStudents;
	}

}
