package com.techcodepro.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techcodepro.model.PersonProfile;

@Component
public interface PersonDao {
	
	public String createProfile(PersonProfile personProfile);
	public List<PersonProfile> getProfile(String key, String value);
	public String updatePassword(String profileId, String newPassword);
	

}
