package de.tekup.loan.invoke.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.tekup.loan.invoke.services.RestClient;
import de.tekup.loan.invoke.services.RestClientFeign;
import de.tekup.loan.invoke.ui.types.CustomerRequest;
import de.tekup.loan.invoke.ui.types.WsResponse;

@Controller
public class CheckController {
	@Autowired
	private RestClient client;
	// alternative way
	@Autowired
	private RestClientFeign fiegnClient;
	
	@GetMapping("/check/customer")
	public String checkCustomerForm(Model model) {
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.setCibilScore(500);
		model.addAttribute("request", customerRequest);
		return "request";
	}
	
	@PostMapping("/check/customer")
	public String checkResults(@ModelAttribute("request") CustomerRequest request,
			Model model) {
		//invoke of web service
		//WsResponse response = client.getLoanStatus(request);
		// other way
		WsResponse response = fiegnClient.consumeByFeign(request);
		model.addAttribute("response",response);
		return "results";
	}
}
