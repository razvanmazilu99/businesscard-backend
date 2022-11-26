package com.contact.businessCard.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public void addCard(Card card) {
        Optional<Card> optionalCard = cardRepository.findCardByEmail(card.getEmail());
        if (optionalCard.isPresent()) {
            throw new IllegalStateException("Email was already used");
        }
        cardRepository.save(card);
    }

    public void deleteCard(String cardId) {
        if (!cardRepository.existsById(cardId)) {
            throw new IllegalStateException("Card with id " + cardId + " does not exist");
        }
        cardRepository.deleteById(cardId);
    }

    @Transactional
    public void updateCard(String cardId, String email, String firstName, String lastName, String phoneNumber, String address, String color, String title) {
        //card.setId(cardId);
        //cardRepository.save(card);
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new IllegalStateException("Card with id " + cardId + " does not exist"));

        if (email != null && email.length() > 0 && !Objects.equals(card.getEmail(), email)) {
            Optional<Card> optionalCard = cardRepository.findCardByEmail(email);
            if (optionalCard.isPresent()) {
                throw new IllegalStateException("Email was already used");
            }
            card.setEmail(email);
        }

        if (firstName != null && firstName.length() > 0 && !Objects.equals(card.getFirstName(), firstName)) {
            card.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(card.getLastName(), lastName)) {
            card.setLastName(lastName);
        }

        if (phoneNumber != null && phoneNumber.length() > 0 && !Objects.equals(card.getPhoneNumber(), phoneNumber)) {
            card.setPhoneNumber(phoneNumber);
        }

        if (address != null && address.length() > 0 && !Objects.equals(card.getAddress(), address)) {
            card.setAddress(address);
        }

        if (color != null && color.length() > 0 && !Objects.equals(card.getColor(), color)) {
            card.setColor(color);
        }

        if (title != null && title.length() > 0 && !Objects.equals(card.getTitle(), title)) {
            card.setTitle(title);
        }
    }
}
