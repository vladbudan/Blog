package com.budan.springappblog.repository;

import com.budan.springappblog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByTagName(String name);

    List<Tag> findTagCloud();
}
