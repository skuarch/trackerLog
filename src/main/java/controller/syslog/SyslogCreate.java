package controller.syslog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controller.application.BaseController;
import model.bean.GenericMessage;
import model.bean.Syslog;
import model.repository.SyslogRepository;
/**
 * nano controller for create syslogs.<br/>
 * Spring RestController
 * 
 * @author skuarch
 *
 */
@RestController
public class SyslogCreate extends BaseController {

	@Autowired
	private SyslogRepository syslogRepository;

	private GenericMessage genericMessage = null;

	// ===============================================================================
	/**
	 * save syslog into DB. the validations is using BeanValidator.
	 * 
	 * @param syslog Syslog
	 * @return GenericMessage
	 */
	@RequestMapping(value = { "/v1/syslog/create" }, method = RequestMethod.POST)
	public GenericMessage createSyslog(@ModelAttribute Syslog syslog) {		

		try {

			syslogRepository.save(syslog);
			genericMessage = getSuccessfulMessage();

		} catch (Exception e) {
			genericMessage = handleException("SyslogCreate.createSyslog()", e, Syslog.class);
		}

		return genericMessage;

	}

}
