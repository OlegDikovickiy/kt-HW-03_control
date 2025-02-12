package ru.netology

fun main() {
    println(transfer("Mastercard", 74000, 20000)) // Пример: Mastercard, 74k переведено, 20k новый перевод
    println(transfer("Visa", 500000, 200000)) // Пример: превышение лимита за месяц
    println(transfer("Мир", 0, 10000)) // Перевод с карты Мир без комиссии
    println(transfer("Mastercard", 0, 80000)) // Превышение лимита без комиссии, расчет комиссии на остаток
}

/**
 * Функция рассчитывает комиссию за перевод и проверяет лимиты.
 */
fun transfer(cardType: String = "Мир", previousTransfers: Int = 0, amount: Int): String {
    val dailyLimit = 150_000
    val monthlyLimit = 600_000

    // Проверяем превышение лимитов
    if (amount > dailyLimit) return "Ошибка: превышен суточный лимит в 150 000 руб."
    if (previousTransfers + amount > monthlyLimit) return "Ошибка: превышен месячный лимит в 600 000 руб."

    // Рассчитываем комиссию
    val commission = when (cardType) {
        "Mastercard" -> {
            if (previousTransfers + amount <= 75_000) 0.0
            else ((previousTransfers + amount - 75_000) * 0.006 + 20).coerceAtLeast(0.0)
        }
        "Visa" -> (amount * 0.0075).coerceAtLeast(35.0)
        "Мир" -> 0.0
        else -> return "Ошибка: неизвестный тип карты"
    }

    return "Перевод: $amount руб. Комиссия: ${commission.toInt()} руб."
}