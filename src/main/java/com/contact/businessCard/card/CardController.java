package com.contact.businessCard.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/card")
@CrossOrigin(origins = "*")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getCards() {
        return cardService.getCards();
    }

    @PostMapping
    public void addCard(@RequestBody Card card) {
        cardService.addCard(card);
    }

    @DeleteMapping(path = "{cardId}")
    public void deleteCard(@PathVariable("cardId") Long cardId) {
        cardService.deleteCard(cardId);
    }

    @PutMapping(path = "{cardId}")
    public void updateCard(
            @PathVariable("cardId") Long cardId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String title) {
        cardService.updateCard(cardId, email, firstName, lastName, phoneNumber, address, color, title);
    }
}
