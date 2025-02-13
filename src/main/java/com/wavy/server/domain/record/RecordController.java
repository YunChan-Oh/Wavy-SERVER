package com.wavy.server.domain.record;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordRepository recordRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRecord(@RequestBody RecordDto.Request request) {
        recordRepository.save(request.toEntity());
    }

    @GetMapping("/{id}")
    public RecordDto.Response getRecord(@PathVariable Long id) {
        Optional<Record> record = recordRepository.findById(id);
        return new RecordDto.Response(record.orElse(null));
    }

    @GetMapping
    public List<RecordDto.Response> getAllRecords() {
        List<Record> records = recordRepository.findAll();
        List<RecordDto.Response> responseList = records.stream()
                .map(RecordDto.Response::new)
                .collect(Collectors.toList());
        return responseList;
    }

}
