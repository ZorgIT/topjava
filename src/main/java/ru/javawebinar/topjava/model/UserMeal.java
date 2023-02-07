package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMeal {
    private final LocalDateTime dateTime;//Дата время употребления

    private final String description; //Наименование описание

    private final int calories; // Каллории в порции

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }
}
