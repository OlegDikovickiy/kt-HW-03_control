package ru.netology


fun main(){
    println("Задача 1: ${agoToText(1728010)}")

}

/**
 * Функция принимает количество секунд и возвращает строку с информацией о времени последнего визита.
 */
fun agoToText(secondsAgo: Int): String {
    return when {
        secondsAgo in 0..60 -> "был(а) только что" // Менее 1 минуты
        secondsAgo in 61..3600 -> "был(а) ${formatMinutes(secondsAgo / 60)} назад" // Минуты
        secondsAgo in 3601..86400 -> "был(а) ${formatHours(secondsAgo / 3600)} назад" // Часы
        secondsAgo in 86401..172800 -> "был(а) вчера" // Вчера (1 сутки назад)
        secondsAgo in 172801..259200 -> "был(а) позавчера" // Позавчера (2 суток назад)
        else -> "был(а) давно" // Более 3 дней назад
    }
}

/**
 * Функция форматирует количество минут и подбирает правильное слово.
 */
fun formatMinutes(minutes: Int): String {
    val word = when {
        minutes % 10 == 1 && minutes % 100 != 11 -> "минуту" // 1, 21, 31... минуту
        minutes % 10 in 2..4 && minutes % 100 !in 12..14 -> "минуты" // 2, 3, 4, 22, 23... минуты
        else -> "минут" // 5-20, 25-30, 35-40... минут
    }
    return "$minutes $word"
}

/**
 * Функция форматирует количество часов и подбирает правильное слово.
 */
fun formatHours(hours: Int): String {
    val word = when {
        hours % 10 == 1 && hours % 100 != 11 -> "час" // 1, 21 час
        hours % 10 in 2..4 && hours % 100 !in 12..14 -> "часа" // 2, 3, 4, 22, 23 часа
        else -> "часов" // 5-20, 25-30 часов
    }
    return "$hours $word"
}