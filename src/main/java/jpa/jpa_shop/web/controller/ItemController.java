package jpa.jpa_shop.web.controller;

import jpa.jpa_shop.domain.item.Book;
import jpa.jpa_shop.domain.item.Item;
import jpa.jpa_shop.exception.NotSearchId;
import jpa.jpa_shop.service.IFS.ItemServiceIFS;
import jpa.jpa_shop.web.controller.dto.request.AlbumSaveRequestDto;
import jpa.jpa_shop.web.controller.dto.request.BookSaveRequestDto;
import jpa.jpa_shop.web.controller.dto.request.MovieSaveRequestDto;
import jpa.jpa_shop.web.controller.dto.response.BookUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/item")
@Controller
public class ItemController {

    private final ItemServiceIFS itemService;
    // Get
    @GetMapping("")
    public String createForm(Model model)
    {
        return "item/create";
    }

    @GetMapping("/createBook")
    public String CreateBook(Model model)
    {
        model.addAttribute("bookSaveRequestDto", new BookSaveRequestDto());
        return "item/createBook";
    }

    @GetMapping("/createAlbum")
    public String CreateAlbum(Model model)
    {
        model.addAttribute("albumSaveRequestDto", new AlbumSaveRequestDto());
        return "item/createAlbum";
    }

    @GetMapping("/createMovie")
    public String CreateMovie(Model model)
    {
        model.addAttribute("movieSaveRequestDto", new MovieSaveRequestDto());
        return "item/createMovie";
    }

    @GetMapping("/list")
    public String list(Model model)
    {
        model.addAttribute("items", itemService.findItems());
        return "item/itemList";
    }

    @GetMapping("/book/{itemId}")
    public String updateItem(@PathVariable("itemId") Long itemId,Model model){
        Book book=(Book) itemService.findById(itemId);
        if(book==null)
        {
            throw new NotSearchId("존재하지 않는 상품입니다.");
        }
        model.addAttribute("updateBook",book.toEntity());
        return "item/update/updateBook";
    }

    // POST
    @PostMapping("/book")
    public String save(@Valid BookSaveRequestDto bookSaveRequestDto, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "item/createBook";
        }
        itemService.saveItem(bookSaveRequestDto.toEntity());
        return "redirect:/";
    }

    @PostMapping("/album")
    public String save(@Valid AlbumSaveRequestDto albumSaveRequestDto, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "item/createAlbum";
        }
        itemService.saveItem(albumSaveRequestDto.toEntity());
        return "redirect:/";
    }

    @PostMapping("/movie")
    public String save(@Valid MovieSaveRequestDto movieSaveRequestDto, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "item/createMovie";
        }
        itemService.saveItem(movieSaveRequestDto.toEntity());
        return "redirect:/";
    }





}
