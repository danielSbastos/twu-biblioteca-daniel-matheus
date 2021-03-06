package com.twu.biblioteca.services;

import com.twu.biblioteca.interfaces.IItem;
import com.twu.biblioteca.models.CurrentUser;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<IItem> items;
    private Finder finder;

    @Deprecated
    public Library(List<IItem> items) {
        this.items = items;
        this.finder = new Finder(items);
    }

    public Library(List<IItem> items, Finder finder) {
        this.items = items;
        this.finder = finder;
    }

    public List<IItem> listItems() {
        return this.items;
    }

    public String checkoutItem(int itemId) {
        IItem item = this.finder.execute(itemId);

        if (item.alreadyBooked()) {
            return "Item already checked out.";
        }

        item.setBookedBy(CurrentUser.get());
        item.setStatus("booked");
        return "Successfully checked out item.";
    }

    public String returnItem(int itemId) {
        IItem item = this.finder.execute(itemId);

        if (!item.alreadyBooked()) {
            return "Item already returned.";
        }

        item.setStatus("available");
        return "Successfully returned item.";
    }
}
