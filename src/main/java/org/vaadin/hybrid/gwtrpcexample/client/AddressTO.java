package org.vaadin.hybrid.gwtrpcexample.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AddressTO implements IsSerializable {

	String firstName;
	String lastName;
	String phoneNumber;
	String emailAddress;
	int id = -1;
	
	public int getId() {
		return id;
	}
	public AddressTO setId(int id) {
		this.id = id;
		return this;
	}
	public String getFirstName() {
		return firstName;
	}
	public AddressTO setFirstName(String firstName) {
		this.firstName = firstName;
                return this;
	}
	public String getLastName() {
		return lastName;
	}
	public AddressTO setLastName(String lastName) {
		this.lastName = lastName;
                return this;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public AddressTO setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
                return this;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public AddressTO setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
                return this;
	}
}
