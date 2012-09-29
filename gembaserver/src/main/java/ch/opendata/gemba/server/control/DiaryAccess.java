/*
 * File: DiaryAccess.java, created at 28.09.2012
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

package ch.opendata.gemba.server.control;

import ch.opendata.gemba.server.entities.Diary;
import ch.opendata.gemba.server.entities.Family;
import ch.opendata.gemba.server.entities.Person;
import static ch.opendata.gemba.server.entities.Diary.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 * @todo Klasse DiaryAccess dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since  28.09.2012
 */
@Stateless
public class DiaryAccess extends AbstractAccess<Diary> {
    @PersistenceContext(unitName = "ch.opendata_gembaserver")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiaryAccess() {
        super(Diary.class);
    }
    public Diary findById(String anId) {
        Query query = em.createNamedQuery(QUERY_FIND_BY_DIARY_ID);
        query.setParameter(PARAM_DIARY_ID, anId);
        Diary result = (Diary) query.getSingleResult();
        return result;
    }

    public List<Diary> findForPerson(Person aPerson) {
        Query query = em.createNamedQuery(QUERY_FIND_BY_PERSON_FK);
        query.setParameter(PARAM_PERSON_FK, aPerson);
        return query.getResultList();
    }

}
// EOF
