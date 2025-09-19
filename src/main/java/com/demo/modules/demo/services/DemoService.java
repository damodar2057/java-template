package com.demo.modules.demo.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.modules.demo.dtos.CreateDemoDto;
import com.demo.modules.demo.dtos.UpdateDemoDto;
import com.demo.modules.demo.entities.DemoEntity;
import com.demo.modules.demo.mapper.DemoMapper;
import com.demo.modules.demo.repository.DemoRepository;
import com.demo.shared.dto.QueryDto;
import com.demo.shared.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DemoService {
    private static final Logger logger = LogManager.getLogger(DemoService.class);

    private final DemoRepository demoRepository;
    private final DemoMapper demoMapper;

    /** Create new demo entity */
    public DemoEntity save(CreateDemoDto dto) {
        DemoEntity entity = demoMapper.toEntity(dto);
        return demoRepository.save(entity);
    }

    /** Paginated fetch */
    public Page<DemoEntity> findAllWithPagination(QueryDto query) {
        Pageable pageable = PageRequest.of(
                query.getPage(),
                query.getPageSize(),
                Sort.by(query.getSortDirection(), query.getSortBy())
        );
        return demoRepository.findAll(pageable);
    }

    /** Fetch by ID */
    public DemoEntity findById(Long id) {
        return demoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Demo entity with id: " + id + " not found!"));
    }

    /** Update existing entity */
    public DemoEntity update(Long id, UpdateDemoDto dto) {
        DemoEntity entity = demoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Demo entity with id: " + id + " not found!"));

        demoMapper.updateFromDto(dto, entity);
        return demoRepository.save(entity);
    }

    /** Delete entity */
    public void delete(Long id) {
        if (!demoRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Demo entity with id: " + id + " not found!");
        }
        demoRepository.deleteById(id);
    }

}
