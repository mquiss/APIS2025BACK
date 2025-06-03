package com.api.ecommerce.category.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "categories")
// La clase Category representa una categoría en el sistema de ecommerce.
public class Category {
    @Id
    private String id; // Identificador único de la categoría
    private String name; // Nombre de la categoría
    private List<Subcategory> subcategories; // Lista de subcategorías asociadas a esta categoría

    @Data
    // La clase Subcategory representa una subcategoría dentro de una categoría.
    public static class Subcategory {
        private String id; // Identificador único de la subcategoría
        private String name; // Nombre de la subcategoría
    }
}

