package alsatang.co.kr.mycounter

fun  main() {

    val dog = Dog("PP", 5)
    println(dog.toString())
    println(dog.copy(age = 8).toString());


    val cat : Cat = BlueCat()
    val result = when(cat) {
        is BlueCat -> "BLUE"
        is WhiteCat -> "WHITE"
        //  else -> "NONE"   필요 없음
    }

    println(result)

}


data class Dog (
    val name: String,
    val age : Int
)
{
    override fun toString(): String {
        return "Direct $name $age"
    }
}
sealed class Cat
class BlueCat : Cat()
class WhiteCat : Cat()