/*
 * File: Datasource.java, created at 28.09.2012
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
 * @todo Klasse Datasource dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since  28.09.2012
 */
@Entity
@Table(name = "datasource")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datasource.findAll", query = "SELECT d FROM Datasource d"),
    @NamedQuery(name = "Datasource.findByDataSourceId", query =
    "SELECT d FROM Datasource d WHERE d.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Datasource.findByServiceProvider", query =
    "SELECT d FROM Datasource d WHERE d.serviceProvider = :serviceProvider"),
    @NamedQuery(name = "Datasource.findByServiceProviderUrl", query =
    "SELECT d FROM Datasource d WHERE d.serviceProviderUrl = :serviceProviderUrl"),
    @NamedQuery(name = "Datasource.findByMeasurement", query =
    "SELECT d FROM Datasource d WHERE d.measurement = :measurement"),
    @NamedQuery(name = "Datasource.findByFrequency", query =
    "SELECT d FROM Datasource d WHERE d.frequency = :frequency"),
    @NamedQuery(name = "Datasource.findByAuthToken", query =
    "SELECT d FROM Datasource d WHERE d.authToken = :authToken"),
    @NamedQuery(name = "Datasource.findByAuthTokenSec", query =
    "SELECT d FROM Datasource d WHERE d.authTokenSec = :authTokenSec")})
public class Datasource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 34)
    @Column(name = "DataSourceId")
    private String dataSourceId;
    @Size(max = 64)
    @Column(name = "ServiceProvider")
    private String serviceProvider;
    @Size(max = 128)
    @Column(name = "ServiceProviderUrl")
    private String serviceProviderUrl;
    @Size(max = 24)
    @Column(name = "Measurement")
    private String measurement;
    @Column(name = "Frequency")
    private Integer frequency;
    @Size(max = 120)
    @Column(name = "AuthToken")
    private String authToken;
    @Size(max = 120)
    @Column(name = "AuthTokenSec")
    private String authTokenSec;
    @JoinColumn(name = "PersonFk", referencedColumnName = "PersonId")
    @ManyToOne(optional = false)
    private Person personFk;

    public Datasource() {
    }

    public Datasource(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getServiceProviderUrl() {
        return serviceProviderUrl;
    }

    public void setServiceProviderUrl(String serviceProviderUrl) {
        this.serviceProviderUrl = serviceProviderUrl;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthTokenSec() {
        return authTokenSec;
    }

    public void setAuthTokenSec(String authTokenSec) {
        this.authTokenSec = authTokenSec;
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
        hash += (dataSourceId != null ? dataSourceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datasource)) {
            return false;
        }
        Datasource other = (Datasource) object;
        if ((this.dataSourceId == null && other.dataSourceId != null) ||
                (this.dataSourceId != null && !this.dataSourceId.equals(other.dataSourceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.opendata.gemba.server.entities.Datasource[ dataSourceId=" + dataSourceId + " ]";
    }

}
// EOF
