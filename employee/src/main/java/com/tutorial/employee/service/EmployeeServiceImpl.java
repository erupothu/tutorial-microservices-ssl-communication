package com.tutorial.employee.service;

import java.time.Instant;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tutorial.employee.config.RecordNotFoundException;
import com.tutorial.employee.entity.EmployeeEntity;
import com.tutorial.employee.repository.EmployeeRepository;

@Service
@CacheConfig(cacheNames = "employee")
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	private JavaMailSender emailSender;

	Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public EmployeeEntity createEmployee(@Valid EmployeeEntity emp) {
		// TODO Auto-generated method stub
		return empRepo.save(emp);
	}

	@CachePut(value = "empInfo", key = "#id")
	public EmployeeEntity updateEmployee(EmployeeEntity emp) {
		// TODO Auto-generated method stub
		boolean exist = empRepo.existsById(emp.getId());

		if (exist) {
			EmployeeEntity response = empRepo.save(emp);
			return response;
		} else {
			throw new RecordNotFoundException("Employee id not found");
		}
	}

	@Cacheable(value = "empInfo")
	public List<EmployeeEntity> showEmployees() {
		List<EmployeeEntity> response = empRepo.findAll();
		return response;
	}

	@Cacheable(value = "empInfo", condition = "id>0")
	public EmployeeEntity getEmployeById(Integer id) {
		// TODO Auto-generated method stub
		EmployeeEntity response = empRepo.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("employee record not found"));
		return response;
	}

	@Override
	public EmployeeEntity getEmployeLogin(Integer id, String password) throws Exception {
		if (password == null) {
			throw new Exception("invalid password");
		}
		boolean exist = empRepo.existsById(id);

		if (exist) {
			EmployeeEntity response = empRepo.findByIdAndPassword(id, password)
					.orElseThrow(() -> new RecordNotFoundException("invalid password"));
			return response;
		} else {
			throw new RecordNotFoundException("Employee id not found");
		}
	}

	@CacheEvict(value = "empInfo", key = "#id")
	public Object deleteEmployeById(Integer id) {
		// TODO Auto-generated method stub
		boolean response = empRepo.existsById(id);

		if (response) {
			empRepo.deleteById(id);
			return "delete successful";
		} else {
			throw new RecordNotFoundException("Employee id not found");
		}
	}

	@CacheEvict(value = "empInfo", allEntries = true)
	public void clearCache() {
	}

//	@Scheduled(cron = "*/5 * * * * *")
	public void schedulingTask() {
		log.info("This Employee scheduled task: " + Instant.now().toEpochMilli());
	}

	@Override
	public Object sendSimpleMail(String to, String subject, String body) {
		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("harish.e@vayaindia.com");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//	    FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
//	    helper.addAttachment("Invoice", file);

		emailSender.send(message);

		return "successfuly sent";

	}

}
