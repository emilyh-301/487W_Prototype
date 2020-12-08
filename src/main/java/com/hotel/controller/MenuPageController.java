package com.hotel.controller;

import com.hotel.model.item.Allergen;
import com.hotel.model.item.Cart;
import com.hotel.model.item.CartItem;
import com.hotel.model.item.MenuItem;
import com.hotel.service.item.CartService;
import com.hotel.service.item.MenuItemService;
import com.hotel.service.user.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Controller
@RequestMapping("/menu")
public class MenuPageController {
    private final MenuItemService service;

    private final UserService userService;
    private final CartService cartService;
    private final int COLUMNS = 3;

    public MenuPageController(MenuItemService s, UserService u, CartService cartService){
        this.service = s;
        this.userService = u;
        this.cartService = cartService;
    }

    @GetMapping("")
    public ModelAndView getMenu(ModelMap model){
        Collection<MenuItem> items = service.getItems(new Sort(Sort.Direction.DESC, "id"));

        //Add an item with id -1 to indicate the location of the "add new item" button
        items.add(new MenuItem(-1L, null, null, 0D, null, null));

        ArrayList<MenuItem[]> items_split = new ArrayList<>();

        int c = 0;
        int r = 0;
        for(MenuItem i : items) {
            if(c == 0) items_split.add(new MenuItem[COLUMNS]);

            items_split.get(r)[c] = i;

            c++;
            if(c == COLUMNS) {
                c = 0;
                r++;
            }
        }

        model.addAttribute("items", items_split);
        return new ModelAndView("menu", model);
    }

    @PostMapping("/addtocart")
    public RedirectView addToCart(RedirectAttributes attributes, @RequestParam("item_id") long itemId, @RequestParam("item_qty") int qty,
                                  @RequestParam("item_notes") String itemNotes){

        // get user cart
        Cart cart = userService.getActiveCart(userService.getCurrentUser());

        service.find(itemId);

        CartItem cartItem = new CartItem(0, service.find(itemId), qty,itemNotes, cart);
        cartService.addToCart(cart, cartItem);

        attributes.addFlashAttribute("success", "Item successfully added to cart.");

        return new RedirectView("/menu");
        //return new ModelAndView("redirect:/menu", model);
    }

    @GetMapping ("/deleteItem/{itemID}")
    public RedirectView deleteItem(@PathVariable(value="itemID") long itemId) {

        // removing item from all active carts
        cartService.removeItemFromAllCarts(itemId);

        // delete item
        service.remove(itemId);

        return new RedirectView("/menu");
    }

    @PostMapping("/addMenuItem")
    public RedirectView addMenuItem(RedirectAttributes attributes, @RequestParam("ItemName") String itemName,
                                    @RequestParam("ItemDescription") String itemDesc,
                                    @RequestParam("ItemPrice") float itemPrice,
                                    @RequestParam("ItemAllergens") String allergens,
                                    @RequestParam("imageFile") MultipartFile file)throws IOException {

        String imgName = file.getOriginalFilename();

        MenuItem i = new MenuItem();
        i.setName(itemName);
        i.setDescription(itemDesc);
        i.setPrice(itemPrice);
        i.setId(0);
        i.setImage(imgName);

        HashSet<Allergen> allergenSet = new HashSet<>();
        String[] allergen_array = allergens.trim().split(",");
        for(String s : allergen_array) {
            if(s.length() > 0)
                allergenSet.add(new Allergen(s));
        }
        i.setAllergens(allergenSet);

        // only uploading to build static folder
        FileUploadUtil.saveFile("build/resources/main/static/images" , imgName, file);


        service.add(i);

        attributes.addFlashAttribute("success", "Item successfully added to the menu.");

        return new RedirectView("/menu");
    }

}
