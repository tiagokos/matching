package com.matching.controller;

@RestController
public class MatchingController {

	@RequestMapping(value = "/match/{workerId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getMatches(@PathVariable("workerId") int workerId) {
			Object object = null;
			
			// Load REST services
			RestTemplate restTemplate = new RestTemplate();
		    Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			
			HttpStatus httpStatus = HttpStatus.OK;
			if (object == null) {
				httpStatus = HttpStatus.NOT_FOUND;
			}
			
			return new ResponseEntity<Object>(object, httpStatus);
	}
	
}
