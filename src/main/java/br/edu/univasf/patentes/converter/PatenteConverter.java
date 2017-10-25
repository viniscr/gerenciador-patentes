package br.edu.univasf.patentes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

import br.edu.univasf.patentes.model.Patente;
import br.edu.univasf.patentes.repository.Patentes;
import br.edu.univasf.patentes.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Patente.class)
public class PatenteConverter implements Converter {

	private Patentes patentes;
	
	public PatenteConverter() {
		patentes = CDIServiceLocator.getBean(Patentes.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Patente retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = patentes.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Patente patente = (Patente) value;
			return patente.getId() == null ? null : patente.getId().toString();
		}
		
		return "";
	}

}
