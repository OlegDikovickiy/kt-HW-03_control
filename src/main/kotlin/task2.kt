package ru.netology

fun main() {
    println(transfer("Mastercard", 74000, 20000)) // Льготный порог превышается, комиссия с 19 000
    println(transfer("Mastercard", 80000, 20000)) // Льготный порог превышен ранее, комиссия с 20 000
    println(transfer("Mastercard", 0, 50000)) // Льготный порог не превышен, комиссия 0
    println(transfer("Visa", 500000, 200000)) // Превышен месячный лимит
    println(transfer("Мир", 0, 10000)) // Перевод с Мир без комиссии
}

/**
 * Функция рассчитывает комиссию за перевод и проверяет лимиты.
 */
fun transfer(cardType: String = "Мир", previousTransfers: Int = 0, amount: Int): String {
    val dailyLimit = 150_000
    val monthlyLimit = 600_000
    val mastercardFreeLimit = 75_000
    val mastercardRate = 0.006
    val mastercardFixedFee = 20

    // Проверяем превышение лимитов
    if (amount > dailyLimit) return "Ошибка: превышен суточный лимит в 150 000 руб."
    if (previousTransfers + amount > monthlyLimit) return "Ошибка: превышен месячный лимит в 600 000 руб."

    // Рассчитываем комиссию
    val commission = when (cardType) {
        "Mastercard" -> {
            when {
                previousTransfers >= mastercardFreeLimit -> (amount * mastercardRate + mastercardFixedFee).toInt() // Льготный порог превышен ранее
                previousTransfers + amount > mastercardFreeLimit -> {
                    val taxableAmount = previousTransfers + amount - mastercardFreeLimit
                    (taxableAmount * mastercardRate + mastercardFixedFee).toInt() // Льготный порог превышается текущим платежом
                }
                else -> 0 // Льготный порог не превышен
            }
        }
        "Visa" -> (amount * 0.0075).coerceAtLeast(35.0).toInt()
        "Мир" -> 0
        else -> return "Ошибка: неизвестный тип карты"
    }

    return "Перевод: $amount руб. Комиссия: $commission руб."
}