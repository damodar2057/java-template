package com.demo.modules.demo.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.modules.demo.dtos.CreateDemoDto;
import com.demo.modules.demo.dtos.UpdateDemoDto;
import com.demo.modules.demo.entities.DemoEntity;
import com.demo.modules.demo.repository.DemoRepository;
import com.demo.shared.dto.PaginatedResponse;
import com.demo.shared.dto.QueryDto;
import com.demo.shared.exception.ResourceNotFoundException;

import java.util.Optional;
import org.modelmapper.ModelMapper;

@Service
public class DemoService {
    private static final Logger logger = LogManager.getLogger(DemoService.class);

    @Autowired
    private final DemoRepository demoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public DemoEntity save(CreateDemoDto dto) {
        try {
            logger.info("Creating demo!!!");
            DemoEntity demo = modelMapper.map(dto, DemoEntity.class);
            return demoRepository.save(demo);
        } catch (Exception e) {
            throw e;
        }
    }

    public PaginatedResponse<DemoEntity> findAllWithPagination(QueryDto query) {
        Pageable pageable = PageRequest.of(
                query.getPage(),
                query.getPageSize(),
                Sort.by(query.getSortDirection(), query.getSortBy()));
        Page<DemoEntity> pageResult = demoRepository.findAll(pageable);
        return new PaginatedResponse<>(
                pageResult.getContent(),
                new PaginatedResponse.Pagination(
                        pageResult.getNumber(),
                        pageResult.getSize(),
                        pageResult.getTotalElements(),
                        pageResult.getTotalPages(),
                        pageResult.isLast()));
    }

    public Optional<DemoEntity> findById(Long id) {
        return demoRepository.findById(id);
    }

    public void delete(Long id) {
        final Optional<DemoEntity> existingDemo = demoRepository.findById(id);
        if (existingDemo.isEmpty()) {
            throw new ResourceNotFoundException("Demo entity with id: " + id + " not found!!");
        }
        this.demoRepository.deleteById(id);
    }

    public DemoEntity update(Long id, UpdateDemoDto dto) {
        DemoEntity demo = demoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Demo with record %d not found!!", id)));

        // Map non-null fields from dto to entity
        modelMapper.map(dto, demo);

        return demoRepository.save(demo);
    }

}
