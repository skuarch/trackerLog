package model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.bean.Syslog;


public interface SyslogRepository extends MongoRepository<Syslog, String> {

	public List<Syslog> findBySeverity(byte severity);

}