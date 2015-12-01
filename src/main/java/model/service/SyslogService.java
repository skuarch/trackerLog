package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.bean.Syslog;
import model.repository.SyslogRepository;

@Service
public class SyslogService {

	@Autowired
	private SyslogRepository syslogRepository;
	
	public List<Syslog> getBySeverity(byte severity){
        return syslogRepository.findBySeverity(severity);
    }
	
}
