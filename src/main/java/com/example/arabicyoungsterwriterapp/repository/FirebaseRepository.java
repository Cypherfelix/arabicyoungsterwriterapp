package com.example.arabicyoungsterwriterapp.repository;

import com.example.arabicyoungsterwriterapp.model.Story;

public interface FirebaseRepository<T, T1> {
    boolean existsById(T1 id);

    T findById(T1 id);

    Iterable<T> findAll();

    void deleteById(T1 id);

    void delete(T item);

    // Use the database instance to perform CRUD operations on the Story class
    T save(T item);
}
