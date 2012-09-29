/*
 * File: Folder.java, created at 28.09.2012
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @todo Klasse Folder dokumentieren.
 *
 * @author Helmut Gehrer <helmut.gehrer@helmchen.ch>
 * @since  28.09.2012
 */
@Entity
@Table(name = "folder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Folder.findAll", query = "SELECT f FROM Folder f"),
    @NamedQuery(name = "Folder.findByIdFolder", query =
    "SELECT f FROM Folder f WHERE f.idFolder = :idFolder"),
    @NamedQuery(name = "Folder.findByContentType", query =
    "SELECT f FROM Folder f WHERE f.contentType = :contentType"),
    @NamedQuery(name = "Folder.findByMimeType", query =
    "SELECT f FROM Folder f WHERE f.mimeType = :mimeType")})
public class Folder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idFolder")
    private Integer idFolder;
    @Size(max = 64)
    @Column(name = "ContentType")
    private String contentType;
    @Size(max = 24)
    @Column(name = "MimeType")
    private String mimeType;
    @Lob
    @Column(name = "Content")
    private byte[] content;
    @JoinColumn(name = "PersonFk", referencedColumnName = "PersonId")
    @ManyToOne(optional = false)
    private Person personFk;

    public Folder() {
    }

    public Folder(Integer idFolder) {
        this.idFolder = idFolder;
    }

    public Integer getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(Integer idFolder) {
        this.idFolder = idFolder;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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
        hash += (idFolder != null ? idFolder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Folder)) {
            return false;
        }
        Folder other = (Folder) object;
        if ((this.idFolder == null && other.idFolder != null) ||
                (this.idFolder != null && !this.idFolder.equals(other.idFolder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.opendata.gemba.server.entities.Folder[ idFolder=" + idFolder + " ]";
    }

}
// EOF
