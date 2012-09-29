/*
 * File: Accesstoken.java, created at 28.09.2012
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
 * @todo Klasse Accesstoken dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since  28.09.2012
 */
@Entity
@Table(name = "accesstoken")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accesstoken.findAll", query = "SELECT a FROM Accesstoken a"),
    @NamedQuery(name = "Accesstoken.findByAccessTokenId", query =
    "SELECT a FROM Accesstoken a WHERE a.accessTokenId = :accessTokenId"),
    @NamedQuery(name = "Accesstoken.findByDoctorNumber", query =
    "SELECT a FROM Accesstoken a WHERE a.doctorNumber = :doctorNumber"),
    @NamedQuery(name = "Accesstoken.findByLoginToken", query =
    "SELECT a FROM Accesstoken a WHERE a.loginToken = :loginToken"),
    @NamedQuery(name = "Accesstoken.findByValidFrom", query =
    "SELECT a FROM Accesstoken a WHERE a.validFrom = :validFrom"),
    @NamedQuery(name = "Accesstoken.findByValidUntil", query =
    "SELECT a FROM Accesstoken a WHERE a.validUntil = :validUntil")})
public class Accesstoken implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 34)
    @Column(name = "AccessTokenId")
    private String accessTokenId;
    @Size(max = 8)
    @Column(name = "DoctorNumber")
    private String doctorNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LoginToken")
    private String loginToken;
    @Column(name = "ValidFrom")
    @Temporal(TemporalType.DATE)
    private Date validFrom;
    @Column(name = "ValidUntil")
    @Temporal(TemporalType.DATE)
    private Date validUntil;
    @JoinColumn(name = "PersonFk", referencedColumnName = "PersonId")
    @ManyToOne(optional = false)
    private Person personFk;

    public Accesstoken() {
    }

    public Accesstoken(String accessTokenId) {
        this.accessTokenId = accessTokenId;
    }

    public Accesstoken(String accessTokenId, String loginToken) {
        this.accessTokenId = accessTokenId;
        this.loginToken = loginToken;
    }

    public String getAccessTokenId() {
        return accessTokenId;
    }

    public void setAccessTokenId(String accessTokenId) {
        this.accessTokenId = accessTokenId;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
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
        hash += (accessTokenId != null ? accessTokenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accesstoken)) {
            return false;
        }
        Accesstoken other = (Accesstoken) object;
        if ((this.accessTokenId == null && other.accessTokenId != null) ||
                (this.accessTokenId != null && !this.accessTokenId.equals(other.accessTokenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.opendata.gemba.server.entities.Accesstoken[ accessTokenId=" + accessTokenId + " ]";
    }

}
// EOF
