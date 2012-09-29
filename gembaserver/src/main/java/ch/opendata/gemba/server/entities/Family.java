/*
 * File: Family.java, created at 28.09.2012
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
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 * @todo Klasse Family dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since  28.09.2012
 */
@Entity
@Table(name = "family")
@XmlRootElement
@NamedQueries({
    @NamedQuery(
        name = Family.QUERY_FIND_BY_FAMILY_ID,
        query = "SELECT f FROM Family f WHERE f.familyId = :" + Family.PARAM_FAMILY_ID)})
public class Family implements Serializable {
    private static final String BEAN_NAME = "ch.opendata.gemba.server.entities.Family";
    public static final String QUERY_FIND_BY_FAMILY_ID = BEAN_NAME + ".findByFamilyId";

    public static final String PARAM_FAMILY_ID = "aFamilyId";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 34)
    @Column(name = "FamilyId")
    private String familyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "LoginToken")
    private String loginToken;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familyFk")
    private Set<Person> personSet;

    public Family() {
    }

    public Family(String familyId) {
        this.familyId = familyId;
    }

    public Family(String familyId, String loginToken) {
        this.familyId = familyId;
        this.loginToken = loginToken;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    @XmlTransient
    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (familyId != null ? familyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Family)) {
            return false;
        }
        Family other = (Family) object;
        if ((this.familyId == null && other.familyId != null) ||
                (this.familyId != null && !this.familyId.equals(other.familyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.opendata.gemba.server.entities.Family[ familyId=" + familyId + " ]";
    }

}
// EOF
