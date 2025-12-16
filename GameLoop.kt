package Lesson9

import kotlin.random.Random
import kotlin.random.Random.Default.nextDouble
import kotlin.random.Random.Default.nextInt

fun main(){
    // базовые переменные
    val gameTime = GameTime()
    //Создание игрового времени которое будет считать всё время и delta
    val enemyCount = Random.nextInt(2, 4)
    val enemyList = mutableListOf<Enemy>()
    var i = 1
    var battle = false

    // игрок
    val player = Player(
        x = 2.0,
        speed = 2.0,
        name = "TrippiTropppa"
    )


    // создание предметов
    val sword = Item(
        "Желейный меч",
        0,
        2,
        0,
        ItemType.WEAPON
    )

    val helmet = Item(
        "Желейный шлем",
        0,
        0,
        10,
        ItemType.ARMOR
    )

    val potion = Item(
        "Слайм зелье",
        20,
        0,
        0,
        ItemType.CONSUMABLE
    )
    // создание енеми
    while (i <= enemyCount){
        val enemy = Enemy(
            x = Random.nextDouble(25.0, 100.0),
            id = i
        )
        enemyList.add(enemy)
        i++
    }
    println("==========ВРАГОВ В ОБЩЕМ $enemyCount")



    // ход игры
    player.printPosition()
    println("Запуск игрового уровня")
    while (player.x <= 110.0){
        gameTime.update()

        val dt = gameTime.deltaTimeSeconds

        // перемещение игрока
        if (!battle){
            player.update(dt)
            player.printPosition()
        }

        // проверка на столковление
        for (enemy in enemyList){
            if (player.x >= enemy.x){
                println("Ваш покорный ${player.name} наткнулся на энеми")
                battle = true
                break
            }
        }

        Thread.sleep(200)

    }
    println("=== The End ===")
}