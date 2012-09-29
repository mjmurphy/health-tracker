/*
 * File: Target.java, created at 28.09.2012
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
import javax.persistence.Lob;
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
 * @todo Klasse Target dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since  28.09.2012
 */
@Entity
@Table(name = "target")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Target.findAll", query = "SELECT t FROM Target t"),
    @NamedQuery(name = "Target.findByTargetId", query =
    "SELECT t FROM Target t WHERE t.targetId = :targetId"),
    @NamedQuery(name = "Target.findByTargetType", query =
    "SELECT t FROM Target t WHERE t.targetType = :targetType"),
    @NamedQuery(name = "Target.findByTargetDate", query =
    "SELECT t FROM Target t WHERE t.targetDate = :targetDate"),
    @NamedQuery(name = "Target.findByMeasurement", query =
    "SELECT t FROM Target t WHERE t.measurement = :measurement"),
    @NamedQuery(name = "Target.findByTargetValue", query =
    "SELECT t FROM Target t WHERE t.targetValue = :targetValue"),
    @NamedQuery(name = "Target.findByUnit", query = "SELECT t FROM Target t WHERE t.unit = :unit")})
public class Target implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TargetId")
    private Integer targetId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "TargetType")
    private String targetType;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Comment")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TargetDate")
    @Temporal(TemporalType.DATE)
    private Date targetDate;
    @Size(max = 24)
    @Column(name = "Measurement")
    private String measurement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TargetValue")
    private long targetValue;
    @Size(max = 12)
    @Column(name = "Unit")
    private String unit;
    @JoinColumn(name = "PersonFk", referencedColumnName = "PersonId")
    @ManyToOne(optional = false)
    private Person personFk;

    public Target() {
    }

    public Target(Integer targetId) {
        this.targetId = targetId;
    }

    public Target(Integer targetId, String targetType, String comment, Date targetDate,
            long targetValue) {
        this.targetId = targetId;
        this.targetType = targetType;
        this.comment = comment;
        this.targetDate = targetDate;
        this.targetValue = targetValue;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public long getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(long targetValue) {
        this.targetValue = targetValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
        hash += (targetId != null ? targetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Target)) {
            return false;
        }
        Target other = (Target) object;
        if ((this.targetId == null && other.targetId != null) ||
                (this.targetId != null && !this.targetId.equals(other.targetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.opendata.gemba.server.entities.Target[ targetId=" + targetId + " ]";
    }

}
// EOF
