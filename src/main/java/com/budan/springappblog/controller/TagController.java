package com.budan.springappblog.controller;

import com.budan.springappblog.dto.tag.DtoAddTag;
import com.budan.springappblog.dto.tag.DtoEditTag;
import com.budan.springappblog.dto.tag.DtoShowTag;
import com.budan.springappblog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/get")
    public List<DtoShowTag> getAll() {
        return tagService.getAll();
    }

    @PostMapping("/get/{tag_name}")
    public List<DtoShowTag> getTagByName(@PathVariable("tag_name") String tagName) {
        return tagService.searchByName(tagName);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('USER')")
    public void add(@RequestBody DtoAddTag dtoAddTag) {
        tagService.add(dtoAddTag);
    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('USER')")
    public void edit(@RequestBody DtoEditTag dtoEditTag) {
        tagService.update(dtoEditTag);
    }

}
