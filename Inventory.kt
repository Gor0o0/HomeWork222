package Lesson9

enum class ItemType{
    WEAPON,
    ARMOR,
    CONSUMABLE
}

class Item(
    val name: String,
    val healthAmount: Int = 0,
    val damageBoost: Int,
    val defence: Int,
    val type: ItemType
)

class Inventory {
    private val items = mutableListOf<Item>()

    fun addItem(item: Item) {
        items.add(item)
    }

    fun findItemByName(name: String): Item? {
        return items.find { it.name == name }
    }

    fun removeItem(item: Item) {
        items.remove(item)
    }
}
