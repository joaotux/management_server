package br.com.umdesenvolvedor.management_server.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.umdesenvolvedor.management_server.dto.provider.ProviderDTO;
import br.com.umdesenvolvedor.management_server.exception.ApiException;
import br.com.umdesenvolvedor.management_server.exception.BusinessException;
import br.com.umdesenvolvedor.management_server.model.Provider;
import br.com.umdesenvolvedor.management_server.security.JwtDecoder;
import br.com.umdesenvolvedor.management_server.service.ProviderService;

@RestController
@RequestMapping("/provider")
public class ProviderResource {
    
    @Autowired
    private ProviderService service;

    @GetMapping(value = {"list/{active}", "list/{name}/{active}"})
    public Page<ProviderDTO> list(@PathVariable(required = false) String name, @PathVariable boolean active, Pageable pageable,  @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        name = name == null ? "%" : name;

        Page<ProviderDTO> listDTO = service.findAll(name, active, pageable, uuid).map(p -> {
            return ProviderDTO.toDTO(p);
        });
        return listDTO;
    }

    @GetMapping(value = {"list-no-page/{active}", "list-no-page/{name}/{active}"})
    public List<ProviderDTO> list(@PathVariable(required = false) String name, @PathVariable boolean active,  @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        name = name == null ? "%" : name;

        List<ProviderDTO> listDTO = new ArrayList<>();
        service.findAll(name, active, uuid).forEach(p -> {
            listDTO.add(ProviderDTO.toDTO(p));
        });
        return listDTO;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Provider create(@RequestBody Provider provider, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        return service.create(provider, uuid);
    }

    @PutMapping("{id}")
    public Provider alter(@PathVariable Long id, @RequestBody Provider provider, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        return service.find(id, uuid).map(p -> {
            return service.create(p.mescla(provider), uuid);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("{id}")
    public Provider find(@PathVariable Long id, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        return service.find(id, uuid).map(p -> p).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        service.find(id, uuid).ifPresent(p -> {
            service.delete(p, uuid);
        });
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
