package com.miniproject.ecommerseapp.admin;

 public class UserDetails {
	    
		private String firstname;
		private String lastname;
		private String username;
		private String password;
		private String city;
		private String emailid;
		private long mobilenumber;
				
		
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getEmailid() {
			return emailid;
		}
		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}
		public long getMobilenumber() {
			return mobilenumber;
		}
		public void setMobilenumber(long mobilenumber) {
			this.mobilenumber = mobilenumber;
		}
		
		
		@Override
		public String toString() {
			return "User [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password="
					+ password + ", city=" + city + ", emailid=" + emailid + ", mobilenumber=" + mobilenumber + "]";
		}
	}



