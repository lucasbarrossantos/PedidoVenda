package converter;


import modelo.Categoria;
import repository.Categorias;
import util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    private Categorias categorias;

    public CategoriaConverter() {
        categorias = CDIServiceLocator.getBean(Categorias.class); // Retorn a instância de um Beans CDI
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Categoria categoria = null;

        if (value != null) {
            Long id = new Long(value);
            categoria = categorias.porId(id);
            return categoria;
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Categoria) value).getId().toString();
        }
        return "";
    }

}
