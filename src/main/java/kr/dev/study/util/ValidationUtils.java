package kr.dev.study.util;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


public class ValidationUtils {
	
	public static void printLog(BindingResult bindingResult, Logger logger) {
		logger.debug("Binding Result has error!");
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		for (ObjectError objectError : allErrors) {
			logger.debug("error : {}, {}", objectError.getObjectName(), objectError.getDefaultMessage());
		}
	}
}