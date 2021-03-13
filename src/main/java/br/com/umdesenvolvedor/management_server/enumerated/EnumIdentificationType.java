package br.com.umdesenvolvedor.management_server.enumerated;

public enum EnumIdentificationType {
    CNPJ("CNPJ"), CPF("CPF"), TAXID("Tax ID");

    private String description;

    EnumIdentificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
