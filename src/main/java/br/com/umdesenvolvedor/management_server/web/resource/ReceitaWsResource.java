package br.com.umdesenvolvedor.management_server.web.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.umdesenvolvedor.management_server.exception.ApiException;
import br.com.umdesenvolvedor.management_server.exception.BusinessException;
import br.com.umdesenvolvedor.management_server.model.ReceitaWs;

@RestController
@RequestMapping("/receitaWs")
public class ReceitaWsResource {

    @GetMapping("{cnpj}")
    public ReceitaWs find(@PathVariable String cnpj) {
        RestTemplate rest = new RestTemplate();
        String url = "https://www.receitaws.com.br/v1/cnpj/" + cnpj;

        ResponseEntity<String> response = null;
        try {
            response = rest.getForEntity(url, String.class);
        }catch(Exception e) {
            throw new BusinessException("Tente novamente em alguns minutos!");
        }

        Gson gson = new GsonBuilder().create();
        ReceitaWs receitaWs = new ReceitaWs();
        if(response.getStatusCode().equals(HttpStatus.OK)) {
            receitaWs = gson.fromJson(response.getBody(), ReceitaWs.class);
        }
        return receitaWs;
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
