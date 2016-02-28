package br.edu.fa7.cursojsf.bean.validator;

import br.edu.fa7.cursojsf.util.Cep;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("cepValidator")
public class CepValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value != null && value instanceof Cep) {
            Cep cep = (Cep) value;
            validaRegiao(cep.getRegiao());
            validaSufixo(cep.getSufixo());
        }
    }

    private void validaRegiao(String regiao) {
        if (regiao != null) {
            regiao = regiao.replaceAll("\\D", "");
            if (regiao.length() != 5) {
                throw new ValidatorException(getMessage("Regiao do CEP invalida, deve conter 5 caracteres numericos."));
            }
        } else {
            throw new ValidatorException(getMessage("Regiao do CEP invalida, esta vazia."));
        }
    }

    private void validaSufixo(String sufixo) {
        if (sufixo != null) {
            sufixo = sufixo.replaceAll("\\D", "");
            if (sufixo.length() != 3) {
                throw new ValidatorException(getMessage("Sefixo do CEP invalido, deve conter 3 caracteres numericos."));
            }
        } else {
            throw new ValidatorException(getMessage("Sefixo do CEP invalido, esta vazio."));
        }
    }

    private FacesMessage getMessage(String message) {
        return new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
    }

}
