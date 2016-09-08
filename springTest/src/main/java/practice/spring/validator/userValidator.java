package practice.spring.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import practice.spring.pojo.User;

@Component
public class userValidator implements Validator{
	
	@Autowired
	private MessageSource messageSource;
	Locale locale;

	// which objects can be validated by this validator
	public boolean supports(Class<?> paramClass) {
		return User.class.equals(paramClass);
	}

	public void validate(Object target, Errors errors) {
		//simple validation
		//independent from the Servlet API and is based on a thread local in order to provide the current locale in any entity of your architecture.
		locale=LocaleContextHolder.getLocale();
		System.out.println("locale::" + locale);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.userName", this.messageSource.getMessage("validation.error.userName", null, "user name missing", locale));
		User user = (User) target;
		// complex validation based on business logic
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("password", "user.password.missMatch","password do not match");
		}
	}

}
