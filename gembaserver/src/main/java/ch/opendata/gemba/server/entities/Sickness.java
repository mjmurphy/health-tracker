/*
 * File: Sickness.java, created at 28.09.2012
 * $Id$
 * Copyright 2012 Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.opendata.gemba.server.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @todo Klasse Sickness dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since  28.09.2012
 */
@Entity
@Table(name = "sickness")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sickness.findAll", query = "SELECT s FROM Sickness s"),
    @NamedQuery(name = "Sickness.findBySicknessId", query =
    "SELECT s FROM Sickness s WHERE s.sicknessId = :sicknessId"),
    @NamedQuery(name = "Sickness.findByInconvenience", query =
    "SELECT s FROM Sickness s WHERE s.inconvenience = :inconvenience"),
    @NamedQuery(name = "Sickness.findByBodyPart", query =
    "SELECT s FROM Sickness s WHERE s.bodyPart = :bodyPart"),
    @NamedQuery(name = "Sickness.findByLevelOfPain", query =
    "SELECT s FROM Sickness s WHERE s.levelOfPain = :levelOfPain"),
    @NamedQuery(name = "Sickness.findByTypeOfPain", query =
    "SELECT s FROM Sickness s WHERE s.typeOfPain = :typeOfPain"),
    @NamedQuery(name = "Sickness.findByTemperature", query =
    "SELECT s FROM Sickness s WHERE s.temperature = :temperature")})
public class Sickness implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 34)
    @Column(name = "SicknessId")
    private String sicknessId;
    @Size(max = 64)
    @Column(name = "Inconvenience")
    private String inconvenience;
    @Size(max = 45)
    @Column(name = "BodyPart")
    private String bodyPart;
    @Column(name = "LevelOfPain")
    private Short levelOfPain;
    @Size(max = 24)
    @Column(name = "TypeOfPain")
    private String typeOfPain;
    @Column(name = "Temperature")
    private Long temperature;
    @JoinColumn(name = "PersonFk", referencedColumnName = "PersonId")
    @ManyToOne(optional = false)
    private Person personFk;

    public Sickness() {
    }

    public Sickness(String sicknessId) {
        this.sicknessId = sicknessId;
    }

    public String getSicknessId() {
        return sicknessId;
    }

    public void setSicknessId(String sicknessId) {
        this.sicknessId = sicknessId;
    }

    public String getInconvenience() {
        return inconvenience;
    }

    public void setInconvenience(String inconvenience) {
        this.inconvenience = inconvenience;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Short getLevelOfPain() {
        return levelOfPain;
    }

    public void setLevelOfPain(Short levelOfPain) {
        this.levelOfPain = levelOfPain;
    }

    public String getTypeOfPain() {
        return typeOfPain;
    }

    public void setTypeOfPain(String typeOfPain) {
        this.typeOfPain = typeOfPain;
    }

    public Long getTemperature() {
        return temperature;
    }

    public void setTemperature(Long temperature) {
        this.temperature = temperature;
    }

    public Person getPersonFk() {
        return personFk;
    }

    public void setPersonFk(Person personFk) {
        this.personFk = personFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sicknessId != null ? sicknessId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sickness)) {
            return false;
        }
        Sickness other = (Sickness) object;
        if ((this.sicknessId == null && other.sicknessId != null) ||
                (this.sicknessId != null && !this.sicknessId.equals(other.sicknessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.opendata.gemba.server.entities.Sickness[ sicknessId=" + sicknessId + " ]";
    }

}
// EOF
