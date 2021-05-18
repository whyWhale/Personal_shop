package jpa.jpa_shop.web.controller;

import jpa.jpa_shop.domain.item.Album;
import jpa.jpa_shop.domain.item.Book;
import jpa.jpa_shop.domain.item.Movie;
import jpa.jpa_shop.exception.NotSearchId;
import jpa.jpa_shop.service.IFS.ItemServiceIFS;
import jpa.jpa_shop.web.controller.dto.request.AlbumSaveRequestDto;
import jpa.jpa_shop.web.controller.dto.request.BookSaveRequestDto;
import jpa.jpa_shop.web.controller.dto.request.MovieSaveRequestDto;
import jpa.jpa_shop.web.controller.dto.response.AlbumUpdateResponseDto;
import jpa.jpa_shop.web.controller.dto.response.BookUpdateResponseDto;
import jpa.jpa_shop.web.controller.dto.response.MovieUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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


    // saveForm

    @GetMapping("/createBook")
    public String CreateBook(Model model)
    {
        model.addAttribute("bookSaveRequestDto", new BookSaveRequestDto());
        return "item/create/createBook";
    }

    @GetMapping("/createAlbum")
    public String CreateAlbum(Model model)
    {
        model.addAttribute("albumSaveRequestDto", new AlbumSaveRequestDto());
        return "item/create/createAlbum";
    }

    @GetMapping("/createMovie")
    public String CreateMovie(Model model)
    {
        model.addAttribute("movieSaveRequestDto", new MovieSaveRequestDto());
        return "item/create/createMovie";
    }


    // list
    @GetMapping("/list")
    public String list(Model model)
    {
        model.addAttribute("items", itemService.findItems());
        return "item/itemList";
    }

    // updateForm
    @GetMapping("/book/{itemId}")
    public String updateFormBook(@PathVariable("itemId") Long itemId,Model model){
        Book book=(Book) itemService.findById(itemId);
        if(book==null)
        {
            throw new NotSearchId("존재하지 않는 상품입니다.");
        }
        model.addAttribute("updateBook",book.toEntity());
        return "item/update/updateBook";
    }

    @GetMapping("/movie/{itemId}")
    public String updateFormMovie(@PathVariable("itemId") Long itemId,Model model){
        Movie movie=(Movie) itemService.findById(itemId);
        if(movie==null)
        {
            throw new NotSearchId("존재하지 않는 상품입니다.");
        }
        model.addAttribute("updateMovie",movie.toEntity());
        return "item/update/updateMovie";
    }

    @GetMapping("/album/{itemId}")
    public String updateFormAlbum(@PathVariable("itemId") Long itemId,Model model){
        Album album=(Album) itemService.findById(itemId);
        if(album==null)
        {
            throw new NotSearchId("존재하지 않는 상품입니다.");
        }
        model.addAttribute("updateAlbum",album.toEntity());
        return "item/update/updateAlbum";
    }


    // Create POST
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

    // Update Post
    @PostMapping("/book/{itemId}")
    public String updateBook(@ModelAttribute("updateBook") BookUpdateResponseDto bookUpdateResponseDto){
        itemService.saveItem(bookUpdateResponseDto.toEntity());
        return "redirect:/item/list";
    }

    @PostMapping("/movie/{itemId}")
    public String updateMovie(@ModelAttribute(value = "updateMovie") MovieUpdateResponseDto movieUpdateResponseDto){
        itemService.saveItem(movieUpdateResponseDto.toEntity());
        return "redirect:/item/list";
    }

    @PostMapping("/album/{itemId}")
    public String updateAlbum(@ModelAttribute(value = "updateAlbum")AlbumUpdateResponseDto albumUpdateResponseDto){
        itemService.saveItem(albumUpdateResponseDto.toEntity());
        return "redirect:/item/list";
    }





}
