package controller.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@RequestMapping(value = {"/"})
	public String index(){
		return "welcome";		
	}
	
}
