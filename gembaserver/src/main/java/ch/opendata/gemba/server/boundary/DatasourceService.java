/*
 * File: DatasourceService.java, created at 29.09.2012
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
package ch.opendata.gemba.server.boundary;

import ch.opendata.gemba.server.control.DatasourceAccess;
import ch.opendata.gemba.server.entities.AvailableDataSource;
import ch.opendata.gemba.server.entities.DatasourceType;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @todo Klasse DatasourceService dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since 29.09.2012
 */
@Path("/datasource/")
@Stateless
public class DatasourceService {
    @EJB
    FamilyService familyService;
    @EJB
    DatasourceAccess datasourceAccess;

    @GET
    @Path("/types/")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public List<DatasourceType> getAvailableDatasourceTypes() {
        List<DatasourceType> result = new ArrayList<DatasourceType>();
        for (DatasourceType cntType : DatasourceType.values()) {
            result.add(cntType);
        }
        return result;
    }

    @GET
    // @Path("/{family}/{secToken}/")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public List<AvailableDataSource> getAvailableDatasources() {
        List<AvailableDataSource> result = new ArrayList<AvailableDataSource>();
        result.add(new AvailableDataSource(DatasourceType.ACTIVITY, "Nike",
                "http://nikerunning.nike.com/api", "km (running)"));
        result.add(new AvailableDataSource(DatasourceType.ACTIVITY, "Nike Fuelband",
                "http://nikerunning.nike.com/api", "Activity state"));
        result.add(new AvailableDataSource(DatasourceType.ACTIVITY, "Runkeeper",
                "http://api.runkeeper.com", "km (running)"));
        // macht Serverseitig keinen sinn
        //   result.add(new AvailableDataSource(DatasourceType.ENVIRONMENT, "Google Geolocation", "http://api.weather.com", "Location"));
        result.add(new AvailableDataSource(DatasourceType.ENVIRONMENT, "Weather.com",
                "http://api.weather.com", "Climate"));
        result.add(new AvailableDataSource(DatasourceType.ENVIRONMENT, "Air polution",
                "http://api.weather.com", "Climate"));
        result.add(new AvailableDataSource(DatasourceType.BODY_MEASUREMENT, "Withings",
                "http://api.withings.com", "Weight"));
        result.add(new AvailableDataSource(DatasourceType.BODY_MEASUREMENT, "Withings",
                "http://api.withings.com", "Bloodpressure"));
        return result;
    }
}
// EOF
