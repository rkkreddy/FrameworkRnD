package testCode;

import org.slf4j.*;

public class TestLoggerSLF4J {
	
	static Logger LOGGER = LoggerFactory.getLogger(TestLoggerSLF4J.class);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            if (i % 2 == 0) {
                LOGGER.info("Hello {}", i);
                LOGGER.error("Hello {}", i);
                LOGGER.warn("Hello {}", i);
            } else {
                LOGGER.debug("I am on index {}", i);
            }
    }

}
