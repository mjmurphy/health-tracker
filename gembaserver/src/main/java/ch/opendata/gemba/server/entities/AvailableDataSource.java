/*
 * File: AvailableDataSource.java, created at 29.09.2012
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

import javax.xml.bind.annotation.XmlRootElement;
/**
 * @todo Klasse AvailableDataSource dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since  29.09.2012
 */
@XmlRootElement
public class AvailableDataSource {
    private DatasourceType type;
    private String serviceProvider;
    private String serviceProviderUrl;
    private String measurement;

    public AvailableDataSource() {
        
    }
    public AvailableDataSource(DatasourceType type, String serviceProvider, String serviceProviderUrl,
            String measurement) {
        this.type = type;
        this.serviceProvider = serviceProvider;
        this.serviceProviderUrl = serviceProviderUrl;
        this.measurement = measurement;
    }


    public DatasourceType getType() {
        return type;
    }

    public void setType(DatasourceType type) {
        this.type = type;
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


}
// EOF
