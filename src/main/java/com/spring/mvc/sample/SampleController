package com.spring.mvc.sample;

@RestController
public class SampleController {

	@Autowired
	private SampleService sampleService;
	
	@CrossOrigin
	@RequestMapping(value = "/sample/{id}", method = RequestMethod.GET)
	public ResponseEntity<Sample> getSample)(@PathVariable("id") int id) {
			Sample sample = sampleService.getById(id);
			
			HttpStatus httpStatus = HttpStatus.OK;
			if (sample == null) {
				httpStatus = HttpStatus.NOT_FOUND;
			}
			
			return new ResponseEntity<Sample>(sample, httpStatus);
	}

}