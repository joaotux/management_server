package br.com.umdesenvolvedor.management_server.web.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.umdesenvolvedor.management_server.dto.company.CompanyDTO;
import br.com.umdesenvolvedor.management_server.exception.ApiException;
import br.com.umdesenvolvedor.management_server.exception.BusinessException;
import br.com.umdesenvolvedor.management_server.model.company.Company;
import br.com.umdesenvolvedor.management_server.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyResource {
    
    @Autowired
    private CompanyService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CompanyDTO create(@RequestBody @Valid Company company) {
        return service.create(company);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiException handleValidationException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		return new ApiException(bindingResult);
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiException handleBussinesException(BusinessException exception) {
		return new ApiException(exception);
	}
}
