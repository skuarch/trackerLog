package model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * this class take some specifications of RFC 5424 https://tools.ietf.org/html/rfc5424<br/>
 * this is one example of syslog format<34>1 2003-10-11T22:14:15.003Z mymachine.example.com su - ID47- BOM'su root' failed for lonvick on /dev/pts/8<br/>
 * Bean
 * @author skuarch
 *
 */
@Entity
@Table(name = "syslog")
@Document
public class Syslog implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@Column(name = "syslog_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private ObjectId id;
	
	@Min(0)
	@Max(23)
	@NotNull
	@Column(name = "syslog_facility")
	private short facility; //0 - 23
	
	@Min(0)
	@Max(7)	
	@Column(name = "syslog_severity")
	private byte severity;	// 0 - 7
	
	@Column(name = "syslog_version")
	private int version;
	
	@NotNull
	@Column(name = "syslog_timestamp")
	private String timestamp; //format 2003-10-11T22:14:15.003Z
	
	@Column(name = "syslog_host_name")
	private String hostName;
	
	@NotNull
	@Column(name = "syslog_app_name")
	private String appName;
	
	@Column(name = "syslog_process_id")
	private int processId;
	
	@Column(name = "syslog_message_id")
	private String messageId;
	
	@Column(name = "syslog_structured_data")
	private String structuredData;
	
	@NotNull
	@Size(min=1, max = 512)	
	@Column(name = "syslog_message")
	private String message;
	
	public Syslog() {
		
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public short getFacility() {
		return facility;
	}

	public void setFacility(short facility) {		
		this.facility = facility;
	}

	public byte getSeverity() {
		return severity;
	}

	public void setSeverity(byte severity) {
		this.severity = severity;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {		
		this.timestamp = timestamp;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getStructuredData() {
		return structuredData;
	}

	public void setStructuredData(String structuredData) {
		this.structuredData = structuredData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
}
