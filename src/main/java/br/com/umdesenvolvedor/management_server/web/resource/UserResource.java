package br.com.umdesenvolvedor.management_server.web.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.umdesenvolvedor.management_server.dto.user.UserCreateDTO;
import br.com.umdesenvolvedor.management_server.dto.user.UserDTO;
import br.com.umdesenvolvedor.management_server.exception.ApiException;
import br.com.umdesenvolvedor.management_server.exception.BusinessException;
import br.com.umdesenvolvedor.management_server.model.User;
import br.com.umdesenvolvedor.management_server.security.JwtDecoder;
import br.com.umdesenvolvedor.management_server.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public @ResponseBody User test(@RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        System.out.println("uuid ==== " + uuid);
        
        return service.findById(uuid).get();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserCreateDTO dto) {
        return service.save(dto);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public UserDTO alter(@RequestBody @Valid UserDTO dto, @RequestHeader(name="Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);

        return service.findById(uuid).map(user -> {
            user.setId(uuid);
            user = user.merger(dto);
            return service.alter(user);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
