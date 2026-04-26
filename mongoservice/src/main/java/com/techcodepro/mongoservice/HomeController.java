package com.techcodepro.mongoservice;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techcodepro.service.Person;
import com.techcodepro.service.Profile;
import com.techcodepro.service.ProfileValidation;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String createProfile(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "register";
	}
	
	@RequestMapping(value = "/registersite", method = RequestMethod.GET)
	public String registerProfile(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "registersite";
	}

	@Autowired
	ProfileValidation profileValidation;

	@RequestMapping(value = "/createprofile", method = RequestMethod.POST, consumes={"application/json", "application/xml"})
	public @ResponseBody String homeCreateProfile(@RequestBody Profile profile) throws Exception {

		return profileValidation.homeTechCodePro(profile);

	}
	
	@RequestMapping(value = "/createprofile", method = RequestMethod.POST, consumes={"application/json", "application/xml"})
	public @ResponseBody String setPasswordRecovery(@RequestBody Profile profile) throws Exception {

		return profileValidation.homeTechCodePro(profile);

	}
	
	@RequestMapping(value = "/getprofile/{key}/{value:.+}", method = RequestMethod.GET,produces={"application/json"})
	public @ResponseBody List<Person> homeTechCodePro(@PathVariable String key,@PathVariable String value) throws Exception {
		
		return profileValidation.getProfile(key,value);

	}
	
	
}
