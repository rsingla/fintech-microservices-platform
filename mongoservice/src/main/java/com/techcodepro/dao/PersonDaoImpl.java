package com.techcodepro.dao;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.techcodepro.model.PersonProfile;



@Component
public class PersonDaoImpl implements PersonDao {
	
	//Logger log = Logger.getLogger(PersonDaoImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String createProfile(PersonProfile personProfile) {
		try {
		mongoTemplate.insert(personProfile, "profile");
		} catch(Exception e) {
			e.printStackTrace();
			return "person was not created: see logs";
		}
		return "person created";
	}

	@Override
	public List<PersonProfile> getProfile(String key,String value) {
		List<PersonProfile> personProfile = null;
		try {
			//log.info("MongoTemplate"+mongoTemplate.getDb());
			System.out.println(mongoTemplate.getDb());
			 personProfile = (List<PersonProfile>) mongoTemplate.find(new Query(Criteria.where(key).is(value.trim())), PersonProfile.class,"profile");
			 
			 if(personProfile == null) {
				System.out.println("Person"+personProfile);
			 }
		} catch(Exception e) {
				e.printStackTrace();
				return personProfile;
			}
		
		return personProfile;
	}

	@Override
	public String updatePassword(String profileId, String newPassword) {
		
		return null;
	}

	
	
}
