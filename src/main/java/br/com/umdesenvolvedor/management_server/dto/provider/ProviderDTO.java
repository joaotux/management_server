package br.com.umdesenvolvedor.management_server.dto.provider;

import br.com.umdesenvolvedor.management_server.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProviderDTO {
    private Long id;
    private String name;
    private boolean active;

    public static ProviderDTO toDTO(Provider provider) {
        return new ProviderDTO(provider.getId(), provider.getPerson().getName(), provider.isActive());
    }
}