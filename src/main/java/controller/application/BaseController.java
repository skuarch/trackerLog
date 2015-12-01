package controller.application;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import model.bean.GenericMessage;

/**
 * this class is designed to be extended in any controller.<br/>
 * all the controllers should extend this class.<br/>
 * if you want to use GenericMessage getters this class must be inject with @Autowired
 * 
 * @author skuarch
 *
 */
public class BaseController {

	@Autowired
	private GenericMessage genericMessage;

	// ==========================================================================
	/**
	 * easy way to handle exceptions in controllers.
	 * 
	 * @param message String
	 * @param exception Exception
	 * @param c Class
	 * @return GenericMessage bean
	 */
	public GenericMessage handleException(String message, Exception exception, Class c) {

		Logger LOGGER = Logger.getLogger(c);
		LOGGER.error(message, exception);
		genericMessage.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		genericMessage.setMessage(exception.getMessage());

		return genericMessage;
	}

	// ==========================================================================
		/**
		 * easy way to handle exceptions in controllers.
		 * 
		 * @param message String
		 * @param exception Exception
		 * @param c Class
		 * @return GenericMessage bean
		 */
		public void handleException(Exception exception, String message, Class c) {

			Logger LOGGER = Logger.getLogger(c);
			LOGGER.error(message, exception);
			
		}

	
	// ==========================================================================
	/**
	 * getter of GenericMessage
	 * 
	 * @return GenericMessage
	 */
	public GenericMessage getGenericMessage() {
		return genericMessage;
	}

	// ==========================================================================
	/**
	 * return a GenericMessage setting with message of successfully.
	 * 
	 * @param id long
	 * @return GenericMessage
	 */
	public GenericMessage getSuccessfulMessage(long id) {
		genericMessage.setId(id);
		genericMessage.setCode(200);
		genericMessage.setMessage("operation successfully completed");
		return genericMessage;
	}

	// ==========================================================================
	/**
	 * getter of GenericMessage
	 * 
	 * @return GenericMessage
	 */
	public GenericMessage getSuccessfulMessage() {
		genericMessage.setCode(200);
		genericMessage.setMessage("operation successfully completed");
		return genericMessage;
	}

}
