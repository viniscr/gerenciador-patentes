package br.edu.univasf.patentes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.univasf.patentes.util.cdi.CDIServiceLocator;
import br.edu.univasf.patentes.model.Universidade;
import br.edu.univasf.patentes.repository.Universidades;

@FacesConverter(forClass = Universidade.class)
public class UniversidadeConverter implements Converter{

		private Universidades universidades;
		
		public UniversidadeConverter() {
			universidades = CDIServiceLocator.getBean(Universidades.class);
		}
		
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Universidade retorno = null;
			
			if (value != null) {
				Long id = new Long(value);
				retorno = universidades.porId(id);
			}
			
			return retorno;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if (value != null) {
				return ((Universidade) value).getId().toString();
			}
			
			return "";
		}
}
