package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;
import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.isBetweenHalfOpen;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
                new UserMeal(LocalDateTime.of(2023, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2023, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2023, Month.JANUARY, 30, 14, 0), "Ужин", 300),
                new UserMeal(LocalDateTime.of(2023, Month.FEBRUARY, 2, 16, 0), "Вода", 15),
                new UserMeal(LocalDateTime.of(2023, Month.FEBRUARY, 2, 17, 0), "Помидоры", 1000),
                new UserMeal(LocalDateTime.of(2023, Month.FEBRUARY, 2, 21, 0), "Рыба", 1005)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(0, 0), LocalTime.of(22, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        //Предполагается отсортированный по дате список как входящий

        //Накопительная переменная для подсчета калорий
        LocalDate localDate = meals.get(0).getDateTime().toLocalDate();
        int calories = 0;
        HashMap<LocalDate, Integer> dailyMealCaloriesMap = new HashMap<>();
        ;
        //Подсчитываем суммарное количество калорий для каждого дня
        for (UserMeal meal: meals
        ) {
            LocalDate checkDate = meal.getDateTime().toLocalDate();
            if (localDate.equals(checkDate))
                calories+=meal.getCalories();
            else {
                dailyMealCaloriesMap.put(localDate,calories);
                calories=meal.getCalories();
                localDate=checkDate;
            }
        }
        dailyMealCaloriesMap.put(localDate,calories);
        //Установка значений excess для всех элементов и формирование списка для выдачи результата
        List<UserMealWithExcess> resultList = new ArrayList<>();
        for (UserMeal meal: meals
             ) {
            //подходит по времени (попадает в диапазон)
            if (isBetweenHalfOpen(meal.getDateTime().toLocalTime(),startTime,endTime)) {
                //Калораж превышен
                Boolean isExcess = dailyMealCaloriesMap
                        .get(meal.getDateTime().toLocalDate()) > caloriesPerDay;
                resultList.add(new UserMealWithExcess(meal.getDateTime(),meal.getDescription(),meal.getCalories(),isExcess));
            }
        }



        return resultList;
    }

    public static List<UserMealWithExcess> filteredByStreams(
            List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));

        return meals.stream().filter(um->TimeUtil.isBetweenHalfOpen(um.getDateTime().toLocalTime(),startTime,endTime))
                .map(um->new UserMealWithExcess(um.getDateTime(),um.getDescription(),um.getCalories(),
                        caloriesSumByDate.get(um.getDateTime().toLocalDate())>caloriesPerDay)).
                collect(Collectors.toList());
    }
}
