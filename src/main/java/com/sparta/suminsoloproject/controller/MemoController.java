package com.sparta.suminsoloproject.controller;

import com.sparta.suminsoloproject.Service.MemoService;
import com.sparta.suminsoloproject.domain.Memo;
import com.sparta.suminsoloproject.domain.MemoRepository;
import com.sparta.suminsoloproject.domain.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping ("/api/memos/{id}")
    public Optional<Memo> modal(@PathVariable Long id) {
        return memoRepository.findById(id);

    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return id;
    }
    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id , @RequestBody MemoRequestDto requestDto) {
            memoService.update(id, requestDto);
            return id;
    }




}