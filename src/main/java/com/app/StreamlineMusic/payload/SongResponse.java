package com.app.StreamlineMusic.payload;

import com.app.StreamlineMusic.entity.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongResponse {
    private List<SongDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;
}
