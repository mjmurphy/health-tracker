/*
 * File: FamilyService.java, created at 28.09.2012
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

import ch.opendata.gemba.server.control.FamilyAccess;
import ch.opendata.gemba.server.control.GembaFactory;
import ch.opendata.gemba.server.entities.Family;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 */
@Path("/family/")
@Stateless
public class FamilyService {
    @EJB
    FamilyAccess familyAccess;

    /**
     * Adds a new Family to the database and returns the GUID of the created
     *
     * @return
     */
    @PUT
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Family addFamily() {
        Family result = new Family();
        result.setFamilyId(GembaFactory.createRandomUuid());
        result.setLoginToken(GembaFactory.createRandomUuid());
        familyAccess.create(result);
        return result;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Family login(String aFamilyId, String aSecToken) {
        Family result = familyAccess.findById(aFamilyId);
        if (result.getLoginToken().equals(aSecToken)) {
            return result;
        }
        return null;
    }
}
