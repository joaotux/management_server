package br.com.umdesenvolvedor.management_server.web.resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.umdesenvolvedor.management_server.dto.ServiceApplicationDTO;
import br.com.umdesenvolvedor.management_server.dto.ServiceApplicationResponseDTO;
import br.com.umdesenvolvedor.management_server.model.ServiceApplication;
import br.com.umdesenvolvedor.management_server.model.company.Company;
import br.com.umdesenvolvedor.management_server.security.JwtDecoder;
import br.com.umdesenvolvedor.management_server.service.ServiceApplicationService;

@RestController
@RequestMapping("/service")
public class ServiceApplicationResource {

    @Autowired
    private ServiceApplicationService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = {"list/{active}", "list/{query}/{active}"})
    public Page<ServiceApplicationDTO> list(@PathVariable(required = false) String query, @PathVariable boolean active,
    @RequestHeader (name = "Authorization") String token, Pageable pageable) {
        String uuid = JwtDecoder.getUUID(token);
        query = query == null ? "%" : query;
        Page<ServiceApplicationDTO> listDTO = service.list(query, active, uuid, pageable).map(sp -> {
            return mapper.map(sp, ServiceApplicationDTO.class);
        });
        return listDTO;
    }

    @PostMapping
    public ServiceApplicationResponseDTO create(@RequestBody ServiceApplication serviceAppli, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        Company company = new Company(uuid);
        serviceAppli.setCompany(company);
        return mapper.map(service.create(serviceAppli), ServiceApplicationResponseDTO.class);
    }

    @GetMapping("{id}")
    public ServiceApplicationResponseDTO find(@PathVariable Long id, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        return service.find(id, uuid).map(ap -> {
            return mapper.map(ap, ServiceApplicationResponseDTO.class);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ServiceApplicationResponseDTO edite(@PathVariable Long id, @RequestBody ServiceApplicationResponseDTO dto, 
    @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        return service.find(id, uuid).map(ap -> {
            ap = service.create(ap.merger(dto));
            return mapper.map(ap, ServiceApplicationResponseDTO.class);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id, @RequestHeader (name = "Authorization") String token) {
        String uuid = JwtDecoder.getUUID(token);
        service.find(id, uuid).ifPresent(sp -> {
            service.delete(sp);
        });
    }
}
