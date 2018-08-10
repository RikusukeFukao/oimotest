package com.example.baseball.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.baseball.domain.Player;
import com.example.baseball.service.PlayerService;

import javax.validation.constraints.*;
import javax.validation.*;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/players") // ①
public class PlayerController {
    @Autowired
    private PlayerService playerService;


    @GetMapping
    public String index(Model model) { // ②
        List<Player> players = playerService.findAll();
        model.addAttribute("players", players); // ③ ミドリハViewの方では。
        return "players/index"; // ④
    }

    @GetMapping("new")
    public String newPlayer(Model model) {
        // ①
        Player player = new Player();
        model.addAttribute("player", player);
        return "players/new";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable Long id, Model model) { // ⑤ @PathVariableを設定するとURL上の値を取得することができる
        Player player = playerService.findOne(id);
        model.addAttribute("player", player);
        return "players/edit";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Player player = playerService.findOne(id);
        model.addAttribute("player", player);
        return "players/show";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Player player, BindingResult bindingResult) { // ⑥ @ModelAttributeをつけると送信されたリクエストのbodyの情報を取得できる ,playerに@Validをつけることでvalidationチェック対象となる
        if(bindingResult.hasErrors()) return "players/new"; // ③:エラーがあるとbindingResult.hasErrors()がtrueを返すのでその場合はもとの画面に返している
        playerService.save(player);
        return "redirect:/players";
    }

    @PutMapping("{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Player player, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "users/edit";
        player.setId(id);
        playerService.save(player);
        return "redirect:/players";
    }

    @DeleteMapping("{id}")
    public String destroy(@ModelAttribute Player player) {
        playerService.delete(player);
        return "redirect:/players";
    }
}
