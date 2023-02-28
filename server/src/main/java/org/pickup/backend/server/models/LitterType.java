package org.pickup.backend.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "litter_types")
public class LitterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "litter_type_desc")
    private String litterTypeDesc;

    @OneToMany(mappedBy="litterType", fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"litterType"})
    private List<Litter> litter;

    public LitterType(String litterTypeDesc) {
        this.litterTypeDesc = litterTypeDesc;
    }
    public LitterType() {}
    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLitterTypeDesc() {
        return litterTypeDesc;
    }

    public void setLitterTypeDesc(String litterTypeDesc) {
        this.litterTypeDesc = litterTypeDesc;
    }
}
