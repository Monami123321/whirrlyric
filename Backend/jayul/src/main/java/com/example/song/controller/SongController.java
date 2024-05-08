package com.example.song.controller;

import com.example.song.dto.res.SongListResponse;
import com.example.song.dto.res.SongResponse;
import com.example.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSongDetail(@PathVariable int id){
        SongResponse response = songService.getSongDetail(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getSongList(){
        SongListResponse response = songService.getSongList();

        return ResponseEntity.ok(response);
    }

}
