/*
 * File: Measurement.java, created at 28.09.2012
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
 * @todo Klasse Measurement dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since  28.09.2012
 */
@Entity
@Table(name = "measurement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measurement.findAll", query = "SELECT m FROM Measurement m"),
    @NamedQuery(name = "Measurement.findByMeasurementId", query =
    "SELECT m FROM Measurement m WHERE m.measurementId = :measurementId"),
    @NamedQuery(name = "Measurement.findByEntryTime", query =
    "SELECT m FROM Measurement m WHERE m.entryTime = :entryTime"),
    @NamedQuery(name = "Measurement.findByMeasurementFrom", query =
    "SELECT m FROM Measurement m WHERE m.measurementFrom = :measurementFrom"),
    @NamedQuery(name = "Measurement.findByMeasurementUntil", query =
    "SELECT m FROM Measurement m WHERE m.measurementUntil = :measurementUntil"),
    @NamedQuery(name = "Measurement.findByMeasurement", query =
    "SELECT m FROM Measurement m WHERE m.measurement = :measurement"),
    @NamedQuery(name = "Measurement.findByValue", query =
    "SELECT m FROM Measurement m WHERE m.value = :value"),
    @NamedQuery(name = "Measurement.findByUnit", query =
    "SELECT m FROM Measurement m WHERE m.unit = :unit"),
    @NamedQuery(name = "Measurement.findByDeclaration", query =
    "SELECT m FROM Measurement m WHERE m.declaration = :declaration")})
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 34)
    @Column(name = "MeasurementId")
    private String measurementId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EntryTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryTime;
    @Column(name = "MeasurementFrom")
    @Temporal(TemporalType.TIMESTAMP)
    private Date measurementFrom;
    @Column(name = "MeasurementUntil")
    @Temporal(TemporalType.TIMESTAMP)
    private Date measurementUntil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "Measurement")
    private String measurement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Value")
    private long value;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "Unit")
    private String unit;
    @Size(max = 128)
    @Column(name = "Declaration")
    private String declaration;
    @JoinColumn(name = "PersonFk", referencedColumnName = "PersonId")
    @ManyToOne(optional = false)
    private Person personFk;

    public Measurement() {
    }

    public Measurement(String measurementId) {
        this.measurementId = measurementId;
    }

    public Measurement(String measurementId, Date entryTime, String measurement, long value,
            String unit) {
        this.measurementId = measurementId;
        this.entryTime = entryTime;
        this.measurement = measurement;
        this.value = value;
        this.unit = unit;
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getMeasurementFrom() {
        return measurementFrom;
    }

    public void setMeasurementFrom(Date measurementFrom) {
        this.measurementFrom = measurementFrom;
    }

    public Date getMeasurementUntil() {
        return measurementUntil;
    }

    public void setMeasurementUntil(Date measurementUntil) {
        this.measurementUntil = measurementUntil;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
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
        hash += (measurementId != null ? measurementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measurement)) {
            return false;
        }
        Measurement other = (Measurement) object;
        if ((this.measurementId == null && other.measurementId != null) ||
                (this.measurementId != null && !this.measurementId.equals(other.measurementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.opendata.gemba.server.entities.Measurement[ measurementId=" + measurementId + " ]";
    }

}
// EOF
