package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.SystemWrapper;

public class Quit implements IOption {

    private SystemWrapper systemWrapper;

    public Quit(SystemWrapper systemWrapper)
    {
        this.systemWrapper = systemWrapper;
    }

    public String getTitle()
    {
        return "Quit system";
    }

    public int getId()
    {
        return 3;
    }

    public String action() {
        this.systemWrapper.exit();
        return "Exiting system...";
    }
}
