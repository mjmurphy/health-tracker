/*
 * File: Diary.java, created at 28.09.2012
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
 * @todo Klasse Diary dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since 28.09.2012
 */
@Entity
@Table(name = "diary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(
        name = Diary.QUERY_FIND_BY_DIARY_ID,
    query = "SELECT d FROM Diary d WHERE d.diaryId = :" + Diary.PARAM_DIARY_ID),
    @NamedQuery(
        name = "Diary.findByEntryTime",
    query = "SELECT d FROM Diary d WHERE d.entryTime = :entryTime"),
    @NamedQuery(
        name = Diary.QUERY_FIND_BY_PERSON_FK,
    query = "SELECT p FROM Person p WHERE p.familyFk = :" + Diary.PARAM_PERSON_FK)
})
public class Diary implements Serializable {
    private static final String BEAN_NAME = "ch.opendata.gemba.server.entities.Diary";
    public static final String QUERY_FIND_BY_PERSON_FK = BEAN_NAME + ".findByPersonFk";
    public static final String QUERY_FIND_BY_DIARY_ID = BEAN_NAME + ".findByDiaryId";
    public static final String PARAM_DIARY_ID = "aDiaryId";
    public static final String PARAM_PERSON_FK = "aPersonFk";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 34)
    @Column(name = "DiaryId")
    private String diaryId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EntryTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryTime;
    @Column(name = "HealthState")
    private Short healthState;
    @Lob
    @Size(max = 65535)
    @Column(name = "Comment")
    private String comment;
    @JoinColumn(name = "PersonFk", referencedColumnName = "PersonId")
    @ManyToOne(optional = false)
    private Person personFk;

    public Diary() {
    }

    public Diary(String diaryId) {
        this.diaryId = diaryId;
    }

    public Diary(String diaryId, Date entryTime) {
        this.diaryId = diaryId;
        this.entryTime = entryTime;
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Short getHealthState() {
        return healthState;
    }

    public void setHealthState(Short healthState) {
        this.healthState = healthState;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        hash += (diaryId != null ? diaryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diary)) {
            return false;
        }
        Diary other = (Diary) object;
        if ((this.diaryId == null && other.diaryId != null) || (this.diaryId != null && !this.diaryId.
                equals(other.diaryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.opendata.gemba.server.entities.Diary[ diaryId=" + diaryId + " ]";
    }
}
// EOF
