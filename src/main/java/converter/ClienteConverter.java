package converter;


import modelo.Cliente;
import repository.Clientes;
import util.jsf.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

    private Clientes cliente;

    public ClienteConverter() {
        cliente = CDIServiceLocator.getBean(Clientes.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Long id = new Long(value);
            return cliente.porId(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Cliente cliente = (Cliente) value;
            return cliente.getId() == null ? null : cliente.getId().toString();
        }
        return "";
    }
}
