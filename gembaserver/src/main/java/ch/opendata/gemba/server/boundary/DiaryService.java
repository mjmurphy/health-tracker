/*
 * File: DiaryService.java, created at 29.09.2012
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

import ch.opendata.gemba.server.control.DiaryAccess;
import ch.opendata.gemba.server.control.GembaFactory;
import ch.opendata.gemba.server.entities.Diary;
import ch.opendata.gemba.server.entities.Family;
import ch.opendata.gemba.server.entities.Person;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @todo Klasse DiaryService dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since 29.09.2012
 */
@Path("/diary/")
@Stateless
public class DiaryService {
    @EJB
    PersonService personService;
    @EJB
    DiaryAccess diaryAccess;

    @PUT
    @Path("/{family}/{secToken}/{person}/")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Diary create(
            @PathParam("family") String aFamilyId,
            @PathParam("secToken") String aSecToken,
            @PathParam("person") String aPersonId) {
        Person person = personService.get(aFamilyId, aSecToken, aPersonId);
        if (person != null) {
            Diary result = new Diary();
            result.setPersonFk(person);
            result.setDiaryId(GembaFactory.createRandomUuid());
            diaryAccess.create(result);
            return result;
        }
        return null;
    }

    @GET
    @Path("/{family}/{secToken}/{person}/")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public List<Diary> get(@PathParam("family") String aFamilyId,
            @PathParam("secToken") String aSecToken, @PathParam("person") String aPersonId) {
        Person person = personService.get(aFamilyId, aSecToken, aPersonId);
        if (person != null) {
            return diaryAccess.findForPerson(person);
        }
        return null;
    }

    @GET
    @Path("/{family}/{secToken}/{person}/{diary}/")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Diary get(
            @PathParam("family") String aFamilyId,
            @PathParam("secToken") String aSecToken,
            @PathParam("person") String aPersonId,
            @PathParam("diary") String aDiaryId) {
        Person person = personService.get(aFamilyId, aSecToken, aPersonId);
        if (person != null) {
            return diaryAccess.findById(aDiaryId);
        }
        return null;
    }

    @POST
    @Path("/{family}/{secToken}/{person}/{diary}/")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Diary update(
            @PathParam("family") String aFamilyId,
            @PathParam("secToken") String aSecToken,
            @PathParam("person") String aPersonId,
            @PathParam("diary") String aDiaryId,
            Diary aDiary) {
        Person person = personService.get(aFamilyId, aSecToken, aPersonId);
        if (person != null) {
            Diary result = diaryAccess.findById(aDiaryId);
            /*result.setDateOfBirth(aPerson.getDateOfBirth());
             result.setFamilyName(aPerson.getFamilyName());
             result.setGivenName(aPerson.getGivenName());
             result.setSex(aPerson.getSex());
             result.setSocialInsuranceNumber(aPerson.getSocialInsuranceNumber()); */
        }
        return null;
    }
}
