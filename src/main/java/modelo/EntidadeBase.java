package modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
abstract class EntidadeBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPersisted() {
        return this.id != null && this.id > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        //add por causa multselect
        if (o == null || id == null || getClass() != o.getClass()) return false;

        EntidadeBase that = (EntidadeBase) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EntidadeBase{" +
                "id=" + id +
                '}';
    }
}
