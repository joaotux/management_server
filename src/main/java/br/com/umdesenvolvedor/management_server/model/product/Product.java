package br.com.umdesenvolvedor.management_server.model.product;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.umdesenvolvedor.management_server.model.EntityAbstract;
import br.com.umdesenvolvedor.management_server.model.company.Company;
import br.com.umdesenvolvedor.management_server.model.provider.Provider;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Product extends EntityAbstract {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Descrição é obrigatória")
    private String description;
    private String code;
    private String barCode;
    private BigDecimal priceSales;
    private BigDecimal priceCost;
    private String note;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductStock> stocks;

    @ManyToOne
    @JoinColumn(name = "id_cate")
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "id_prov")
    private Provider provider;

    @Setter
    @ManyToOne
	@JoinColumn(name = "id_comp")
    @JsonIgnore
	private Company company;

    public Product merger(Product product) {
        this.description = product.getDescription();
        this.code = product.getCode();
        this.barCode = product.getBarCode();
        this.priceSales = product.getPriceSales();
        this.priceCost = product.getPriceCost();
        this.note = product.getNote();
        this.category = product.getCategory();
        this.provider = product.getProvider();

        for(int i = 0; i < product.getStocks().size(); i++) {
            product.getStocks().get(i).setProduct(product);
        }
        this.stocks = product.stocks;

        return this;
    }
}
