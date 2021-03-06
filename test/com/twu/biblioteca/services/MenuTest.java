package com.twu.biblioteca.services;

import com.twu.biblioteca.interfaces.IItem;
import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.services.menu_options.Item;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

public class MenuTest {

    @Test
    public void getOptionsReturnOptions()
    {
        List<IItem> books = new ArrayList<>();
        Library library = new Library(books);
        Menu subMenu = new Menu(new ArrayList<>());

        IOption bookListOption = new Item(1, "List", library, new InputReaderWrapper(), new OutputWriterWrapper(), subMenu);

        List<IOption> options = new ArrayList<>();
        options.add(bookListOption);

        Menu menu = new Menu(options);
        List<IOption> expectedOptions = menu.getOptions();

        assertEquals(expectedOptions, options);
    }

    @Test
    public void getOptionByIdWithValidIdReturnsChosenOption()
    {
        List<IItem> books = new ArrayList<>();
        Library library = new Library(books);
        Menu subMenu = new Menu(new ArrayList<>());

        IOption option = new Item(1, "List", library, new InputReaderWrapper(), new OutputWriterWrapper(), subMenu);

        List<IOption> options = new ArrayList<>();
        options.add(option);

        Menu menu = new Menu(options);
        int optionId = 1;
        IOption chosenOption = menu.getOptionById(optionId);

        assertEquals(chosenOption, option);
    }

    // TODO: add #message method to defined error
    @Test(expected = IndexOutOfBoundsException.class)
    public void getOptionByIdWithInvalidIdThrowsAnError()
    {
        List<IItem> books = new ArrayList<>();
        Library library = new Library(books);
        Menu subMenu = new Menu(new ArrayList<>());

        IOption option = new Item(1, "List", library, new InputReaderWrapper(), new OutputWriterWrapper(), subMenu);

        List<IOption> options = new ArrayList<>();
        options.add(option);

        Menu menu = new Menu(options);
        int invalidOptionId = -1;
        menu.getOptionById(invalidOptionId);
    }

    @Test
    public void executeChosenOptionReturnsChosen()
    {
        IOption mockedBookListOption = Mockito.mock(Item.class);
        when(mockedBookListOption.getId()).thenReturn(1);

        List<IOption> options = new ArrayList<>();
        options.add(mockedBookListOption);

        Menu menu = new Menu(options);
        menu.executeOption(1);

        verify(mockedBookListOption, times(1)).action();
    }
}
