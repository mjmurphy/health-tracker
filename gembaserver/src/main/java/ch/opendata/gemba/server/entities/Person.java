/*
 * File: Person.java, created at 28.09.2012
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
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @todo Klasse Person dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since 28.09.2012
 */
@Entity
@Table(name = "person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(
        name = Person.QUERY_FIND_BY_PERSON_ID,
    query = "SELECT p FROM Person p WHERE p.personId = :" + Person.PARAM_PERSON_ID),
    @NamedQuery(
        name = Person.QUERY_FIND_BY_FAMILY_FK,
    query = "SELECT p FROM Person p WHERE p.familyFk = :" + Person.PARAM_FAMILY_FK)})
public class Person implements Serializable {
    private static final String BEAN_NAME = "ch.opendata.gemba.server.entities.Person";
    public static final String QUERY_FIND_BY_FAMILY_FK = BEAN_NAME + ".findByFamilyFk";
    public static final String QUERY_FIND_BY_PERSON_ID = BEAN_NAME + ".findByPersonId";
    public static final String PARAM_PERSON_ID = "aPersonId";
    public static final String PARAM_FAMILY_FK = "aFamilyFk";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 34)
    @Column(name = "PersonId")
    private String personId;
    @Size(max = 64)
    @Column(name = "FamilyName")
    private String familyName;
    @Size(max = 64)
    @Column(name = "GivenName")
    private String givenName;
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "Sex")
    private Character sex;
    @Size(max = 16)
    @Column(name = "SocialInsuranceNumber")
    private String socialInsuranceNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personFk")
    private Set<Folder> folderSet;
    @JoinColumn(name = "FamilyFk", referencedColumnName = "FamilyId")
    @ManyToOne(optional = false)
    private Family familyFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personFk")
    private Set<Accesstoken> accesstokenSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personFk")
    private Set<Diary> diarySet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personFk")
    private Set<Prescription> prescriptionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personFk")
    private Set<Sickness> sicknessSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personFk")
    private Set<Target> targetSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personFk")
    private Set<Datasource> datasourceSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personFk")
    private Set<Measurement> measurementSet;

    public Person() {
    }

    public Person(String personId) {
        this.personId = personId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getSocialInsuranceNumber() {
        return socialInsuranceNumber;
    }

    public void setSocialInsuranceNumber(String socialInsuranceNumber) {
        this.socialInsuranceNumber = socialInsuranceNumber;
    }

    @XmlTransient
    public Set<Folder> getFolderSet() {
        return folderSet;
    }

    public void setFolderSet(Set<Folder> folderSet) {
        this.folderSet = folderSet;
    }

    public Family getFamilyFk() {
        return familyFk;
    }

    public void setFamilyFk(Family familyFk) {
        this.familyFk = familyFk;
    }

    @XmlTransient
    public Set<Accesstoken> getAccesstokenSet() {
        return accesstokenSet;
    }

    public void setAccesstokenSet(Set<Accesstoken> accesstokenSet) {
        this.accesstokenSet = accesstokenSet;
    }

    @XmlTransient
    public Set<Diary> getDiarySet() {
        return diarySet;
    }

    public void setDiarySet(Set<Diary> diarySet) {
        this.diarySet = diarySet;
    }

    @XmlTransient
    public Set<Prescription> getPrescriptionSet() {
        return prescriptionSet;
    }

    public void setPrescriptionSet(Set<Prescription> prescriptionSet) {
        this.prescriptionSet = prescriptionSet;
    }

    @XmlTransient
    public Set<Sickness> getSicknessSet() {
        return sicknessSet;
    }

    public void setSicknessSet(Set<Sickness> sicknessSet) {
        this.sicknessSet = sicknessSet;
    }

    @XmlTransient
    public Set<Target> getTargetSet() {
        return targetSet;
    }

    public void setTargetSet(Set<Target> targetSet) {
        this.targetSet = targetSet;
    }

    @XmlTransient
    public Set<Datasource> getDatasourceSet() {
        return datasourceSet;
    }

    public void setDatasourceSet(Set<Datasource> datasourceSet) {
        this.datasourceSet = datasourceSet;
    }

    @XmlTransient
    public Set<Measurement> getMeasurementSet() {
        return measurementSet;
    }

    public void setMeasurementSet(Set<Measurement> measurementSet) {
        this.measurementSet = measurementSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null
                && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.opendata.gemba.server.entities.Person[ personId=" + personId + " ]";
    }
}
// EOF
