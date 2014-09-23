package com.jnotes.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Skylar on 9/13/2014.
 */
@Entity
@Table(name = "notes", schema = "public", catalog = "notes")
public class NoteEntity {
	private int id;
	private String title;
	private String text;
	private Timestamp dateCreated;

    public NoteEntity() {

    }

    public NoteEntity(NoteCreation noteCreation) {
        setTitle(noteCreation.getTitle());
        setText(noteCreation.getText());
    }

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
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
	@Column(name = "date_created")
	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NoteEntity that = (NoteEntity) o;

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
        return builder.toString();
    }
}
