package br.dev.webit.domain;

import java.util.Objects;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class EntidadeId implements br.dev.webit.infra.ddd.ValueObject<EntidadeId> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "agregador_id"))
    private AgregadorId agregadorId;
    private long id;

    protected EntidadeId() {
    }

    EntidadeId(AgregadorId agregadorId, long id) {
        this();
        this.agregadorId = Objects.requireNonNull(agregadorId);
        this.id = id;
    }

    @Override
    public boolean sameValueAs(EntidadeId other) {
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.agregadorId, other.agregadorId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.agregadorId);
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return sameValueAs((EntidadeId) obj);
    }

    @Override
    public String toString() {
        return Long.toString(id);
    }
}
