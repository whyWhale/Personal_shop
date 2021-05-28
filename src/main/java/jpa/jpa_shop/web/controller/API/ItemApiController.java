package jpa.jpa_shop.web.controller.API;

import jpa.jpa_shop.service.IFS.ItemServiceIFS;
import jpa.jpa_shop.web.controller.dto.request.item.AlbumSaveRequestDto;
import jpa.jpa_shop.web.controller.dto.request.item.BookSaveRequestDto;
import jpa.jpa_shop.web.controller.dto.request.item.MovieSaveRequestDto;
import jpa.jpa_shop.web.controller.dto.response.ListResponse;
import jpa.jpa_shop.web.controller.dto.response.item.AlbumUpdateResponseDto;
import jpa.jpa_shop.web.controller.dto.response.item.BookUpdateResponseDto;
import jpa.jpa_shop.web.controller.dto.response.item.ItemListResponseDto;
import jpa.jpa_shop.web.controller.dto.response.item.MovieUpdateResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/item")
@RestController
public class ItemApiController {
    private final ItemServiceIFS itemService;

    @PostMapping("/book")
    public void saveBook(@Valid @RequestBody BookSaveRequestDto bookSaveRequestDto)
    {
        itemService.saveItem(bookSaveRequestDto.toEntity());
    }

    @PostMapping("/album")
    public void saveAlbum(@Valid @RequestBody AlbumSaveRequestDto albumSaveRequestDto)
    {
        itemService.saveItem(albumSaveRequestDto.toEntity());
    }

    @PostMapping("/movie")
    public void saveAlbum(@Valid @RequestBody MovieSaveRequestDto movieSaveRequestDto)
    {
        itemService.saveItem(movieSaveRequestDto.toEntity());
    }


    @GetMapping("")
    public ListResponse<ItemListResponseDto> read()
    {
        List<ItemListResponseDto> items = itemService.findItems();
        return new ListResponse (items.size(), items);
    }

    @PutMapping("/book")
    public void updateBook(@Valid @RequestBody BookUpdateResponseDto bookUpdateResponseDto)
    {
        itemService.updateItem(bookUpdateResponseDto.toEntity());
    }

    @PutMapping("/album")
    public void updateAlbum(@Valid @RequestBody AlbumUpdateResponseDto albumUpdateResponseDto)
    {
        itemService.updateItem(albumUpdateResponseDto.toEntity());
    }

    @PutMapping("/movie")
    public void updateMovie(@Valid @RequestBody MovieUpdateResponseDto movieUpdateResponseDto)
    {
        itemService.updateItem(movieUpdateResponseDto.toEntity());
    }


    @DeleteMapping("{itemId}")
    public void deleteItem(@PathVariable("itemId") Long id)
    {
        itemService.delete(id);
        return;
    }

}
