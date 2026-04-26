package com.techcodepro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techcodepro.dao.PersonDao;
import com.techcodepro.model.PersonProfile;
import com.techcodepro.util.Utils;

@Component
public class ProfileValidation {

	@Autowired
	PersonDao personDao;

	public String homeTechCodePro(Profile profile) throws Exception {
		validate(profile);
		PersonProfile personProfile = profileToProfileDao(profile);
		personDao.createProfile(personProfile);
		return "profileCreated";
	}

	private PersonProfile profileToProfileDao(Profile profile)  {
		PersonProfile personProfile = new PersonProfile();
		personProfile.setProfileId(Utils.getProfileId());
		personProfile.setEmail(profile.getEmail());
		personProfile.setFirstName(profile.getFirstName());
		personProfile.setLastName(profile.getLastName());
		personProfile.setPassword(Utils.toHex(profile.getPassword()));
		return personProfile;

	}
	
	private PersonProfile getPasswordSecuritQuestion(ResetPasswordSecurity resetPassword) {
		PersonProfile personProfile = new PersonProfile();
		personProfile.setQuestionOne(resetPassword.getQuestionOne());
		personProfile.setQuestionTwo(resetPassword.getQuestionTwo());
		personProfile.setQuestionThree(resetPassword.getQuestionThree());
		
		return personProfile;
	}

	private PersonProfile resetPasswordSecurityAPI(ResetPasswordSecurity resetPassword) {
		PersonProfile personProfile = new PersonProfile();
		personProfile.setAnswerOne(resetPassword.getAnswerOne());
		personProfile.setAnswerTwo(resetPassword.getAnswerTwo());
		personProfile.setAnswerThree(resetPassword.getAnswerThree());
		personProfile.setQuestionOne(resetPassword.getQuestionOne());
		personProfile.setQuestionTwo(resetPassword.getQuestionTwo());
		personProfile.setQuestionThree(resetPassword.getQuestionThree());
		
		return personProfile;
	}
	
	private void validate(Profile profile) throws Exception {
		if(!profile.getConfirmEmail().equalsIgnoreCase(profile.getEmail())) {
			throw new Exception("Email is not valid");
		}

	}

	public List<Person> getProfile(String key,String value) {

		List<PersonProfile> persprofList = personDao.getProfile(key,value); 
		List<Person> personProfile = new ArrayList<Person>();

		for(PersonProfile persprof: persprofList) {
			Person person = new Person();

			if(persprof != null) {
				person.setEmail(persprof.getEmail());
				person.setFirstName(persprof.getFirstName());
				person.setLastName(persprof.getLastName());
				person.setDateOfBirth(persprof.getDateOfBirth());
				person.setGender(persprof.getGender());
			}
			personProfile.add(person);
		}
		return personProfile;
	}
}
