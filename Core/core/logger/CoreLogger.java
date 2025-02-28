package core.logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CoreLogger {
	private static String LOG4J2_PROPERTIES = "\\\\Core\\\\core\\\\logger\\\\log4j2.properties";
	static {
		String path = System.getProperty("user.dir") + LOG4J2_PROPERTIES;
		System.setProperty("log4j.configurationFile",path);
	}
	
	//class.getName()
	public static final Logger createLogger(String className) {
		return LogManager.getLogger(className);
	}
	
	//class
	public static final Logger createLogger(Class<?> clazz) {
		return LogManager.getLogger(clazz);
	}
	
//	private static final Logger log = createLogger(CoreLogger.class);
//	public static void main(String[] args){
//		log.info("Log4j - INFO");
//		log.debug("Log4j - DEBUG");
//		log.warn("Log4j - WARN");
//		System.out.println("This is test Log4j");
//	}
}
