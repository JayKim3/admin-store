package com.study.adminstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String parentType;

    private String type;

    private String title;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
                joinColumns = @JoinColumn(name = "CATEGORY_ID"),
                inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )

    private List<Item> items = new ArrayList<>();

    @Override
    public boolean equals(final Object o) {
        if(this == o) return true;
        if( o == null || getClass() != o.getClass()) return false;
        final Category category = (Category) o;
        return type.equals(category.type) && title.equals(category.title);
    }
}
