package com.rbc.invoice.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractObject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID", nullable = false, updatable = false)
	protected Long id;
	
	@Version
	@Column(name="VERSION")
	private Long version;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATED")
	private Date dateUpdated;
	
	@Column(name="IS_DELETED", columnDefinition = "boolean DEFAULT false")
	private Boolean deleted;
	
	@PrePersist
	public void prePersisted() {
		if (dateCreated == null) {
			dateCreated = new Date();
		}
		if (dateUpdated == null) {
			dateUpdated = dateCreated;
		}
		if (version == null) {
			version = new Long(0);
		}
	}
	@PreUpdate
	public void preUpdated() {
		dateUpdated = new Date();
	}
	
	@Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        return result;
    }
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getVersion() {
		return version;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	
	public Boolean isDeleted() {
		if (deleted == null) {
			deleted = false;
		}
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
