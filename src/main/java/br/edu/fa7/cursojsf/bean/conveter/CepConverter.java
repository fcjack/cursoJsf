package br.edu.fa7.cursojsf.bean.conveter;

import br.edu.fa7.cursojsf.util.Cep;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cepConverter")
public class CepConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if(value != null ) {
      String[] split = value.split("-");
      if(split.length == 2){
        return new Cep( split[0] , split[1] );
      }
      if(split.length == 1){
        return new Cep( split[0] , "" );
      }
    }
    return null;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if(value != null){
      return ((Cep)value).toString();
    }
    return null;
  }
}
