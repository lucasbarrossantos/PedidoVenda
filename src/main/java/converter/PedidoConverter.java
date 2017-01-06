package converter;


import modelo.Pedido;
import repository.Pedidos;
import util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

    private Pedidos pedidos;

    public PedidoConverter() {
        pedidos = CDIServiceLocator.getBean(Pedidos.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Long id = new Long(value);
            return pedidos.porId(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Pedido pedido = (Pedido) value;
            return pedido.getId() == null ? null : pedido.getId().toString();
        }
        return "";
    }
}
