package com.jnotes.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Skylar on 9/13/2014.
 */
@Entity
@Table(name = "notes", schema = "public", catalog = "notes")
public class Note {
	private int id;
	private String title;
	private String text;
	private Date dateCreated;
    private Date dateUpdated;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Basic
	@Column(name = "text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Basic
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = false)
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_updated", nullable = false)
    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @PrePersist
    protected void onCreate() {
        setDateCreated(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        setDateUpdated(new Date());
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Note that = (Note) o;

		if (id != that.id) return false;
		if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
		if (text != null ? !text.equals(that.text) : that.text != null) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (text != null ? text.hashCode() : 0);
		result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
		return result;
	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nNote id: " + getId());
        builder.append("\nNote title: " + getTitle());
        builder.append("\nNote text: " + getText());
        builder.append("\nNote creation date: " + getDateCreated());
        builder.append("\nNote last update: " + getDateUpdated());
        return builder.toString();
    }
}
