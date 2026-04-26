package com.techcodepro.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "profile")
public class Profile extends Person {

		private String confirmEmail;
		private String password;
		private String confirmPassword;
		
		
		public String getPassword() {
			return password;
		}
		@XmlElement
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmEmail() {
			return confirmEmail;
		}
		@XmlElement
		public void setConfirmEmail(String confirmEmail) {
			this.confirmEmail = confirmEmail;
		}
		public String getConfirmPassword() {
			return confirmPassword;
		}
		@XmlElement
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		
		@Override
		public String toString() {
			return "Profile [confirmEmail=" + confirmEmail + ", password="
					+ password + ", confirmPassword=" + confirmPassword + "]";
		}

		

	
}
