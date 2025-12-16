package Lesson9

import kotlin.random.Random

open class GameObject(
    // open - ключевое слово которе позоляет наследовать класс

    var x: Double, // x - позиционирование объекта по оси х (горизонтально)
    var speed: Double // Скорость перемещения объекта (сколько единиц по х объект пройдет в секунду

){
    open fun update(deltaTimeMillis: Double){
        // open fun метод который можно переопределить в наследниках (override)

        x += speed * deltaTimeMillis

    }
}

// о игроке и енеми
class Player(
    x: Double,      //x и speed свойства мы не указываем тип данных так как мы наследуем свойства у родителя
    speed: Double,
    val name: String,
    var hp: Int = 100,
    val maxHp: Int = 100,
    var attack: Int = 0,
    val inventory: Inventory = Inventory()
) : GameObject (x, speed){
    // : GameObject() - это наследование от родительского класса
    var equippedWeapon: Item? = null
    var equippedArmor: Item? = null

    fun addItem(item: Item){
        inventory.addItem(item)
    }

    fun printPosition(){
        println("$name находится по х = $x")
    }

    // проверка жив ли
    fun isAlive(): Boolean {
        return hp > 0
    }

    // функция атаки енеми
    fun useAttack(enemy: Enemy) {
        var damage = Random.nextInt(10, 40)
        val multiplier = equippedWeapon?.damageBoost ?: 1
        damage *= multiplier

        println("$name атакует врага, нанося $damage урона")
        enemy.takeAttack(damage)
    }


    // функция получения атаки (урона)
    fun takeAttack(damage: Int) {
        val defence = equippedArmor?.defence ?: 0
        val finalDamage = (damage - defence)
        hp -= finalDamage
        println("$name получает $finalDamage урона (hp = $hp)")
    }

}
class Enemy(
    x: Double,
    var id: Int = 0,
    var hp: Int = 0,
    var attack: Int = 0
) : GameObject(x, 0.0){
    fun printPosition(){
        println("Враг находится по х = $x")
    }

    // проверка жив ли
    fun isAlive(): Boolean {
        return hp > 0
    }

    // функция атаки игрока
    fun useAttack(player: Player) {
        var damage = Random.nextInt(5, 21) // случайный урон 5–20
        damage *= if (attack > 0) attack else 1

        println("Враг атакует ${player.name} нанося $damage урона")
        player.takeAttack(damage)
    }

    // функция получения урона
    fun takeAttack(damage: Int) {
        hp -= damage
        println("Враг $id получает $damage урона (HP: $hp)")
    }


}

