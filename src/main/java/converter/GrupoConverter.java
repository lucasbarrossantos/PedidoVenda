package converter;

import modelo.Grupo;
import repository.Grupos;
import util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {

    private Grupos grupos;

    public GrupoConverter() {
        grupos = CDIServiceLocator.getBean(Grupos.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null){
            Long id = new Long(value);
            return grupos.porId(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Grupo grupo = (Grupo) value;
            return grupo.getId() == null ? null : grupo.getId().toString();
        }
        return null;
    }
}
