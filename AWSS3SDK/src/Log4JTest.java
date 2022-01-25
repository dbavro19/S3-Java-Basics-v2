import org.apache.log4j.Logger;

public class Log4JTest {

	public static void main(String[] args) {
		
		
		final Logger logger = Logger.getLogger(Log4JTest.class);
		
		logger.debug("DEBUG: " + " parameter/exception object");
		logger.info("INFO: " + " parameter/exception object");
		logger.warn("WARN: " + " parameter/exception object");
		logger.error("Error: " + " parameter/exception object");
		logger.fatal("FATAL: " + " parameter/exception object");

	}

}
