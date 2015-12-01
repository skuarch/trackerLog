package controller.syslog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controller.application.BaseController;
import model.bean.Syslog;
import model.service.SyslogService;

@RestController
public class SyslogGetListSeverity extends BaseController {

	@Autowired
	private SyslogService syslogService;

	// ====================================================================================
	@RequestMapping(value = { "/v1/syslog/get/list/severity/{severity}" }, method = RequestMethod.GET)
	public List<Syslog> getList(@PathVariable("severity") byte severity) {

		List<Syslog> list = null;

		try {

			list = syslogService.getBySeverity(severity);

		} catch (Exception e) {
			handleException(e, "SyslogGetListSeverity.getList()", SyslogGetListSeverity.class);
		}

		return list;

	}

}
