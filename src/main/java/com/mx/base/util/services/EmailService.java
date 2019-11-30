package com.mx.base.util.services;

import com.mx.base.models.catalog.Email;

public interface EmailService {
		
	public boolean sendEmail(Email email);
	
}
