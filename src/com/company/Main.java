package com.company;

import com.company.commonService.Process;
import com.company.signUp.Registration;
import com.company.signUp.RegistrationImpl;
import com.company.signUp.RegistrationService;


public class Main {
    public static void main(String[] args) {
        RegistrationService registrationService= new RegistrationImpl();
        Registration registration= new Registration(registrationService);

        Process process = new Process(registrationService, registration);
        process.startMainMenu();

   }
}
