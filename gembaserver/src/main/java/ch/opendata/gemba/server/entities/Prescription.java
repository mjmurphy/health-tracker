/*
 * File: Prescription.java, created at 28.09.2012
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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @todo Klasse Prescription dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since  28.09.2012
 */
@Entity
@Table(name = "prescription")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prescription.findAll", query = "SELECT p FROM Prescription p"),
    @NamedQuery(name = "Prescription.findByPrescriptionId", query =
    "SELECT p FROM Prescription p WHERE p.prescriptionId = :prescriptionId"),
    @NamedQuery(name = "Prescription.findByMediCode", query =
    "SELECT p FROM Prescription p WHERE p.mediCode = :mediCode"),
    @NamedQuery(name = "Prescription.findByMediName", query =
    "SELECT p FROM Prescription p WHERE p.mediName = :mediName"),
    @NamedQuery(name = "Prescription.findByStartDate", query =
    "SELECT p FROM Prescription p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Prescription.findByRepetitions", query =
    "SELECT p FROM Prescription p WHERE p.repetitions = :repetitions"),
    @NamedQuery(name = "Prescription.findByQuantity", query =
    "SELECT p FROM Prescription p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Prescription.findByUnit", query =
    "SELECT p FROM Prescription p WHERE p.unit = :unit"),
    @NamedQuery(name = "Prescription.findByFrequency", query =
    "SELECT p FROM Prescription p WHERE p.frequency = :frequency"),
    @NamedQuery(name = "Prescription.findByIsuredBy", query =
    "SELECT p FROM Prescription p WHERE p.isuredBy = :isuredBy")})
public class Prescription implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 34)
    @Column(name = "PrescriptionId")
    private String prescriptionId;
    @Size(max = 24)
    @Column(name = "MediCode")
    private String mediCode;
    @Size(max = 128)
    @Column(name = "MediName")
    private String mediName;
    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "Repetitions")
    private Integer repetitions;
    @Column(name = "Quantity")
    private Long quantity;
    @Size(max = 12)
    @Column(name = "Unit")
    private String unit;
    @Column(name = "Frequency")
    private Long frequency;
    @Size(max = 8)
    @Column(name = "IsuredBy")
    private String isuredBy;
    @JoinColumn(name = "PersonFk", referencedColumnName = "PersonId")
    @ManyToOne(optional = false)
    private Person personFk;

    public Prescription() {
    }

    public Prescription(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getMediCode() {
        return mediCode;
    }

    public void setMediCode(String mediCode) {
        this.mediCode = mediCode;
    }

    public String getMediName() {
        return mediName;
    }

    public void setMediName(String mediName) {
        this.mediName = mediName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    public String getIsuredBy() {
        return isuredBy;
    }

    public void setIsuredBy(String isuredBy) {
        this.isuredBy = isuredBy;
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
        hash += (prescriptionId != null ? prescriptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prescription)) {
            return false;
        }
        Prescription other = (Prescription) object;
        if ((this.prescriptionId == null && other.prescriptionId != null) ||
                (this.prescriptionId != null && !this.prescriptionId.equals(other.prescriptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.opendata.gemba.server.entities.Prescription[ prescriptionId=" + prescriptionId +
                " ]";
    }

}
// EOF
