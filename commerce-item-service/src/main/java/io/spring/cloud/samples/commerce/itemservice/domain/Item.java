package io.spring.cloud.samples.commerce.itemservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item: {");
        sb.append("id = '").append(id).append('\'');
        sb.append(", name = '").append(name).append('\'');
        sb.append(", description = '").append(description).append('\'');
        sb.append(", category = '").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
