package br.com.webbudget.domain.service;

import java.util.ResourceBundle;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Servico de busca de enderecos para o cadastro de contatos do sistema
 * 
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.2.0, 16/04/2015
 */
@Service
public class AddressFinderService {

    private final ResourceBundle configBundle;

    /**
     * Inicializa o bundle de configuracao
     */
    public AddressFinderService() {
        this.configBundle = ResourceBundle.getBundle("config.webbudget");
    }
    
    /**
     * Busca os dados referentes a um endereco partindo do CEP como referencia
     * 
     * @param zipcode o cep
     * @return o endereco
     */
    public Address findAddressByZipcode(String zipcode) {
        
        final String wsURL = this.configBundle.getString("ws.cep");
        
        // se a url estiver zicada ja tora o pau e nao deixa passar
        if(wsURL == null || wsURL.isEmpty()) {
            throw new IllegalStateException("Invalid URL for address search");
        }

        final RestTemplate template = new RestTemplate();
        
        return template.getForObject(String.format(wsURL, zipcode), Address.class);
    }
    
    /**
     * A representacao concreta do endereco
     */
    @ToString
    @EqualsAndHashCode
    public class Address {
        
        @Getter
        @Setter
        private String cep;
        @Getter
        @Setter
        private String logradouro;
        @Getter
        @Setter
        private String complemento;
        @Getter
        @Setter
        private String bairro;
        @Getter
        @Setter
        private String localidade;
        @Getter
        @Setter
        private String uf;
        @Getter
        @Setter
        private String ibge;
        
        /**
         * @return o nome completo do estado referente a unidade federativa
         */
        public String getFullUfName() {
            
            switch (this.uf) {
                case "PR": return "Paraná";
                default: return "Desconhecido";
            }
        }
    }
}
